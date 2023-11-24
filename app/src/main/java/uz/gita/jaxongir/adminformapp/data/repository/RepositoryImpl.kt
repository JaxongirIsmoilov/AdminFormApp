package uz.gita.jaxongir.adminformapp.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import uz.gita.jaxongir.adminformapp.data.model.ComponentData
import uz.gita.jaxongir.adminformapp.data.source.database.dao.Dao
import uz.gita.jaxongir.adminformapp.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dao: Dao,
    private val firestore: FirebaseFirestore
): Repository{
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

    override fun deleteComponent(componentData: ComponentData): Flow<Result<String>> = callbackFlow {
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
}