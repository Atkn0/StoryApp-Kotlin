package com.example.storyapp_kotlin.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.storyapp_kotlin.models.StoryModel
import com.example.storyapp_kotlin.models.UserModel
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FirebaseRepository @Inject constructor(){

    private val db = Firebase.firestore
    private val auth = Firebase.auth
    private val completeTheStory_ref = db.collection("completeTheStory")

    var isJoinStorySuccess = MutableLiveData<Boolean>()



    suspend fun getAllUsers() : ArrayList<UserModel>{
        val userCollection = db.collection("Users").get().await()

        val userList = arrayListOf<UserModel>()
        for (document in userCollection){
            val userModel = UserModel(
                userId = document.getString("userId")!!,
                email = document.getString("email")!!,
                storyAddCredit = document.getLong("storyAddCredit")!!.toInt(),
                storyCreationCredit = document.getLong("storyCreationCredit")!!.toInt()
            )
            userList.add(userModel)
        }
        return userList
    }
    suspend fun getCompleteStories() : ArrayList<StoryModel>{
        val completeTheStoryCollection = db.collection("completeTheStory").get().await()

        val completeTheStoryList = arrayListOf<StoryModel>()
        for (document in completeTheStoryCollection){
            val storyModel = StoryModel(
                storyId = document.get("storyId").toString(),
                storyContent = document.get("storyContent") as HashMap<String,String>,
                contributions = document.get("contributions") as ArrayList<String>,
                numberOfReader = document.getLong("numberOfReader")!!.toInt(),
                numberOfLikes = document.getLong("numberOfLikes")!!.toInt(),
                isFinished = document.get("finished") as Boolean?,
                createdDate = document.getTimestamp("createdDate")!!
            )
            completeTheStoryList.add(storyModel)
        }
        return completeTheStoryList
    }

    // getCompleteStories fonksiyonu ile tekrarlanıyor gibi sadece bir yer değişik.
    // Daha güzel yazılabilir mi diye tekrardan bak
    suspend fun getCompleteStoryByID(storyID : String) : StoryModel?{
        return try {
            val documentSnapshot = db.collection("completeTheStory").document(storyID).get().await()
            val documentSnapshotData = documentSnapshot.data
            StoryModel(
                storyId = documentSnapshotData?.get("storyId").toString(),
                storyContent = documentSnapshotData?.get("storyContent") as HashMap<String,String>,
                contributions = documentSnapshotData?.get("contributions") as ArrayList<String>,
                numberOfReader = (documentSnapshotData.get("numberOfReader") as Long).toInt(),
                numberOfLikes = (documentSnapshotData.get("numberOfLikes") as Long).toInt(),
                isFinished = documentSnapshotData.get("finished") as Boolean,
                createdDate = documentSnapshotData.get("createdDate") as Timestamp
            )
        }
        catch (e : Exception){
            println("Error in getCompleteStoryByID function")
            println(e.localizedMessage)
            null
        }
    }
    suspend fun joinTheCurrentStory(storyModel: StoryModel, newStoryContent: String) : Boolean{
        val storyID = storyModel.storyId
        val userUID = auth.currentUser?.uid

        if (userUID == null) {
            println("Kullanıcı kimliği bulunamadı.")
            return false
        }

        val contributions = storyModel.contributions

        // Eğer kullanıcı zaten katılmışsa, mesajı yazdır ve fonksiyondan çık
        if (contributions?.contains(userUID) == true) {
            println("Zaten bu hikayeye katıldınız.")
            return false
        }

        contributions?.add(userUID)

        val updatedStoryHashMap = updateStoryContent(storyModel,userUID,newStoryContent)

        // Belirli alanlarda güncelleme yapmak için bir `HashMap` oluştur
        val updates = hashMapOf(
            "contributions" to contributions as Any,
            "storyContent" to updatedStoryHashMap as Any
        )


        return try {
            completeTheStory_ref
                .document(storyID!!)
                .update(updates)
                .await()
            true

        } catch (e : Exception){
            println("Error in joinTheCurrentStory function")
            println(e.localizedMessage)
            false
        }
    }

    fun updateStoryContent(storyModel : StoryModel,userID : String, newStoryContent :String) : HashMap<String,String>{
        val currentStoryContent = storyModel.storyContent

        currentStoryContent?.put(userID,newStoryContent)


        return currentStoryContent!!
    }
    suspend fun getUserByID(userID : String) : UserModel?{
        return try {
            val documentSnapshot = db.collection("Users").document(userID).get().await()
            UserModel(
                userId = documentSnapshot.getString("userId")!!,
                email = documentSnapshot.getString("email")!!,
                storyAddCredit = documentSnapshot.getLong("storyAddCredit")!!.toInt(),
                storyCreationCredit = documentSnapshot.getLong("storyCreationCredit")!!.toInt()
            )
        }
        catch (e : Exception){
            println("Error in getUserByID function")
            null
        }
    }
    fun addCreatedStoryFirestore(storyContent: String, userUID: String) : Boolean {

        val storyID = UUID.randomUUID().toString()
        val storyContentMap = hashMapOf(
            userUID to storyContent
        )


        val storyModel = StoryModel(
            storyId = storyID,
            storyContent = storyContentMap,
            contributions = arrayListOf(userUID),
            numberOfReader = 0,
            numberOfLikes = 0,
            isFinished = false,
            createdDate = Timestamp.now()
        )

        var isSuccess : Boolean = false

        completeTheStory_ref
            .document(storyID)
            .set(storyModel)
            .addOnSuccessListener {
                isSuccess = true
            }
            .addOnFailureListener {
                isSuccess = false
            }

        return isSuccess
    }




}