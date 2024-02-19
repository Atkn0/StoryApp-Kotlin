package com.example.storyapp_kotlin.ui.login
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storyapp_kotlin.models.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val firestore : FirebaseFirestore,
    private var auth : FirebaseAuth
): ViewModel(){


    var isUserSignedIn = MutableLiveData<Boolean>()


    fun userLogin(email : String, password : String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    updateUserSignInStatus()
                } else {
                    println("Login Failed")
                }
            }
    }


    fun createUser(email : String, password : String){
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


    fun logout(){
        auth.signOut()
        updateUserSignInStatus()
    }

    fun updateUserSignInStatus(){
        isUserSignedIn.value = auth.currentUser != null
    }

    fun checkUserSignStatus() : Boolean{
        return auth.currentUser != null
    }

    fun addUserToDatabase(user : UserModel){

        //checks that if the value return null for auth.currentUser
        val uniqeUserId = user.userId

        firestore.collection("Users").document(uniqeUserId.toString()).set(user)
            .addOnSuccessListener {
                println("User Added")
            }
            .addOnFailureListener {
                println("failed" + it.message)
                println("User Not Added")
            }


    }
}