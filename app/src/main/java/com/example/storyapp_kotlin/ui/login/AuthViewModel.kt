package com.example.storyapp_kotlin.ui.login
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storyapp_kotlin.models.UserModel
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
                    updateUserSignInStatus()
                } else {
                    println("Login Failed")
                }
            }
    }

    fun userSignOut(){
        auth = Firebase.auth
        auth.signOut()
        updateUserSignInStatus()
    }

    fun createUser(email : String, password : String){
        auth = Firebase.auth
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    updateUserSignInStatus()
                    val userModel = UserModel(auth.currentUser?.uid.toString(), email)
                    addUserToDatabase(userModel)
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
        val uniqeUserId = user.userId

        db.collection("Users").document(uniqeUserId.toString()).set(user)
            .addOnSuccessListener {
                println("User Added")
            }
            .addOnFailureListener {
                println("failed" + it.message)
                println("User Not Added")
            }


    }
}