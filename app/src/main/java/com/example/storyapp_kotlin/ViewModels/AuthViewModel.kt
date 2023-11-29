package com.example.storyapp_kotlin.ViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storyapp_kotlin.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class AuthViewModel : ViewModel(){

    private lateinit var auth : FirebaseAuth
    var isUserSignedIn = MutableLiveData<Boolean>(false)
    val db = Firebase.firestore

    fun userLogin(email : String, password : String){
        auth = Firebase.auth
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    println("Login Success")

                    updateUserSignInStatus()
                } else {
                    println("Login Failed")
                }
            }
    }

    fun createUser(email : String, password : String){
        auth = Firebase.auth
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    updateUserSignInStatus()
                    val testUserModel = UserModel(0,"a")
                    addUserToDatabase(testUserModel)
                } else {
                    println("Create User Failed")
                }
            }
    }

    fun updateUserSignInStatus(){
        isUserSignedIn.value = auth.currentUser != null
    }

    fun checkUserSıgnStatus() : Boolean{
        auth = Firebase.auth
        return auth.currentUser != null
    }

    fun addUserToDatabase(user : UserModel){

        //checks that if the value return null for auth.currentUser
        val uniqeUserId = auth.currentUser?.uid
        println("User Id : $uniqeUserId")

        db.collection("Users").document(uniqeUserId!!).set(user)
            .addOnSuccessListener {
                println("User Added")
            }
            .addOnFailureListener {
                println("User Not Added")
            }


    }
}