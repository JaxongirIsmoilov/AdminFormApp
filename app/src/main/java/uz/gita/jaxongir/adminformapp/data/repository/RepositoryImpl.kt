package uz.gita.jaxongir.adminformapp.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.jaxongir.adminformapp.data.enums.ComponentEnum
import uz.gita.jaxongir.adminformapp.data.enums.TextFieldType
import uz.gita.jaxongir.adminformapp.data.model.ComponentData
import uz.gita.jaxongir.adminformapp.data.model.Conditions
import uz.gita.jaxongir.adminformapp.data.model.UserData
import uz.gita.jaxongir.adminformapp.data.request.UserRequest
import uz.gita.jaxongir.adminformapp.data.source.database.dao.Dao
import uz.gita.jaxongir.adminformapp.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dao: Dao,
    private val firestore: FirebaseFirestore,
) : Repository {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    override fun addComponent(componentData: ComponentData, id: Int): Flow<Result<String>> =
        callbackFlow {
            firestore.collection("Components")
                .add(componentData.copy(locId = id).toRequest())
                .addOnSuccessListener {
                    coroutineScope.launch {
                        dao.insertData(componentData.toEntity().copy(id = it.id))
                        trySend(Result.success("Component qoshildi"))
                    }
                }
                .addOnFailureListener {
                    trySend(Result.failure(it))
                }

            awaitClose()
        }
    override fun deleteComponent(componentData: ComponentData): Flow<Result<String>> =
        callbackFlow {
            firestore.collection("Components")
                .document(componentData.id)
                .delete()
                .addOnSuccessListener {
                    coroutineScope.launch {
                        dao.deleteData(componentData.toEntity())
                    }
                    trySend(Result.success("Data o'chirildi"))
                }
            awaitClose()
        }
    override fun editComponent(componentData: ComponentData): Flow<Result<String>> = callbackFlow {
        firestore.collection("Components")
            .document(componentData.id)
            .set(componentData)
            .addOnSuccessListener {
                coroutineScope.launch {
                    dao.insertData(componentData.toEntity())
                }
                trySend(Result.success("Component o'zgartirildi"))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose()
    }
    override fun addUser(request: UserRequest): Flow<Result<String>> = callbackFlow {
        firestore.collection("Users")
            .add(request)
            .addOnSuccessListener {
                coroutineScope.launch {
                    dao.insertUser(request.fromRequestToEntity().copy(id = it.id))
                }
                trySend(Result.success(it.id))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }

        awaitClose()
    }
    override fun deleteUser(userData: UserData): Flow<Result<String>> = callbackFlow {
        firestore.collection("Users")
            .document(userData.userId)
            .delete()
            .addOnSuccessListener {
                coroutineScope.launch {
                    dao.deleteUser(userData.toEntity())
                }
                trySend(Result.success("User muvvaffiqiyatli o'chirildi"))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }

        awaitClose()
    }
    private fun getComponents(): Flow<Result<Unit>> = callbackFlow {
        val resultList = arrayListOf<ComponentData>()
        val converter = Gson()
        firestore.collection("Components")
            .get()
            .addOnSuccessListener {
                it.documents.forEach {
                    val state = resultList.add(
                        ComponentData(
                            id = it.id,
                            userId = it.data?.getOrDefault("userId", "null").toString(),
                            locId = Integer.parseInt(
                                it.data?.getOrDefault("locId", "0").toString()
                            ),
                            idEnteredByUser = it.data?.getOrDefault("idEnteredByUser", "null")
                                .toString(),
                            content = it.data?.getOrDefault("idEnteredByUser", "null").toString(),
                            textFieldType = converter.fromJson(
                                it.data?.getOrDefault(
                                    "textFieldType",
                                    "null"
                                ).toString(), TextFieldType::class.java
                            ),
                            maxLines = Integer.parseInt(
                                it.data?.getOrDefault("maxLines", "0").toString()
                            ),
                            maxLength = Integer.parseInt(
                                it.data?.getOrDefault("maxLength", "0").toString()
                            ),
                            minLength = Integer.parseInt(
                                it.data?.getOrDefault("minLength", "0").toString()
                            ),
                            maxValue = Integer.parseInt(
                                it.data?.getOrDefault("maxValue", "0").toString()
                            ),
                            minValue = Integer.parseInt(
                                it.data?.getOrDefault("minValue", "0").toString()
                            ),
                            isMulti = it.data?.getOrDefault("isMulti", "false")
                                .toString() == "true",
                            variants = converter.fromJson(
                                it.data?.getOrDefault("variants", "[]").toString(),
                                Array<String>::class.java
                            ).asList(),
                            selected = converter.fromJson(
                                it.data?.getOrDefault("selected", "[]").toString(),
                                Array<Boolean>::class.java
                            ).asList(),
                            conditions = converter.fromJson(
                                it.data?.getOrDefault("conditions", "[]").toString(),
                                Array<Conditions>::class.java
                            ).asList(),
                            type = converter.fromJson(
                                it.data?.getOrDefault("type", "").toString(),
                                ComponentEnum::class.java
                            )
                        )
                    )

                }
                coroutineScope.launch {
                    dao.insertDatas(resultList.map { it.toEntity() })
                }
                trySend(Result.success(Unit))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose()

    }
    private fun getUser(): Flow<Unit> = callbackFlow {
        val resultList = arrayListOf<UserData>()
        firestore.collection("Users")
            .get()
            .addOnSuccessListener {
                it.documents.forEach {
                    resultList.add(
                        UserData(
                            userId = it.id,
                            userName = it.data?.getOrDefault("userName", "").toString(),
                            password = it.data?.getOrDefault("password", "").toString()
                        )
                    )
                }

                coroutineScope.launch { dao.insertUsers(resultList.map { it.toEntity() }) }
                trySend(Unit)
            }
            .addOnFailureListener {
                trySend(Unit)
            }

        awaitClose()
    }
    override fun getComponentsByUserId(userID: String): Flow<Result<List<ComponentData>>> = flow {
        getComponents()
            .onEach {
                it.onSuccess {
                    dao.getByUser(userID)
                        .onEach {
                            emit(Result.success(it.map {
                                it.toData()
                            }))
                        }
                        .collect()
                }
                    .onFailure { }
            }
            .collect()


    }.flowOn(Dispatchers.IO)
        .catch { emit(Result.failure(Exception("adsf"))) }

    override fun getUsers(): Flow<Result<List<UserData>>> = flow {
        dao.deleteUsers()
        getUser().onEach {
            dao.getUsers().onEach {
                emit(Result.success(it.map { it.toData() }))
            }.collect()
        }.collect()
    }.flowOn(Dispatchers.IO)
}