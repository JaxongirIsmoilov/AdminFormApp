package uz.gita.jaxongir.adminformapp.data.repository

import com.google.firebase.firestore.FirebaseFirestore
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
import uz.gita.jaxongir.adminformapp.data.model.ComponentData
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
            .document(userData.id)
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

    override fun getComponentsByUserId(userID: String): Flow<Result<List<ComponentData>>> = flow {
        dao.getByUser(userID).onEach {
            emit(Result.success(it.map { it.toData() }))
        }.collect()
    }.flowOn(Dispatchers.IO)
        .catch { emit(Result.failure(Exception("Nimadir g'alati narsa sodir bo'ldi"))) }

    override fun getUsers(): Flow<Result<List<UserData>>> = flow {
        dao.getUsers().onEach {
            emit(Result.success(it.map { it.toData() }))
        }.collect()
    }.flowOn(Dispatchers.IO)
        .catch { emit(Result.failure(Exception("Nimadir g'alati narsa sodir bo'ldi"))) }
}