package uz.gita.jaxongir.adminformapp.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import uz.gita.jaxongir.adminformapp.data.enums.TextFieldType
import uz.gita.jaxongir.adminformapp.data.model.ComponentData
import uz.gita.jaxongir.adminformapp.data.model.UserData
import uz.gita.jaxongir.adminformapp.data.request.UserRequest
import uz.gita.jaxongir.adminformapp.data.source.database.dao.Dao
import uz.gita.jaxongir.adminformapp.domain.repository.Repository
import uz.gita.jaxongir.adminformapp.utils.getAll
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dao: Dao,
    private val firestore: FirebaseFirestore,
) : Repository {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    override fun addComponent(componentData: ComponentData): Flow<Result<String>> = callbackFlow {
        firestore.collection("Components")
            .add(componentData)
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

    override fun getComponentsByUserId(userID: String): Flow<Result<List<ComponentData>>> =
        firestore.collection("Components")
            .get()
            .getAll {
                val converter = Gson()
                return@getAll ComponentData(
                    id = it.id,
                    userId = it.data?.getOrDefault("userID", "null").toString(),
                    locId = Integer.parseInt(it.data?.getOrDefault("locId", "0").toString()),
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
                    isMulti = it.data?.getOrDefault("isMulti", "false").toString() == "true",
                    listOf(""), listOf(false), listOf()
                )
            }

    override fun getUsers(): Flow<Result<List<UserData>>> = flow{

    }

}





/*
* dao.getByUser(userID).onEach {
            emit(Result.success(it.map { it.toData() }))
        }.collect()
        *
        * dao.getUsers().onEach {
            emit(Result.success(it.map { it.toData() }))
        }.collect()
* */