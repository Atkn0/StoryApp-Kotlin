package com.example.storyapp_kotlin.ViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class AuthViewModel : ViewModel(){

    private lateinit var auth : FirebaseAuth
    var isUserSignedIn = MutableLiveData<Boolean>(false)

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

    fun updateUserSignInStatus(){

        isUserSignedIn.value = auth.currentUser != null

    }


}