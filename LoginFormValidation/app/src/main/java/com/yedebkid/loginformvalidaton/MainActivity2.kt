package com.yedebkid.loginformvalidaton

import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.yedebkid.loginformvalidaton.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private val binding by lazy {
        ActivityMain2Binding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        inputFocusListener()



    }
    private val sharedPreferences: SharedPreferences by lazy { //object
        baseContext.getSharedPreferences("My_Share_prefs", MODE_PRIVATE)
    }

    override fun onResume() {
        super.onResume()
        binding.button.setOnClickListener {
        binding.button.setBackgroundColor(Color.parseColor("#06AA0B"))
            val mString =
                binding.emailEditView.text.toString() //this is the data that I am going to save
            sharedPreferences.edit().apply {
                putString("My_Email", mString)
                apply()
            }
            Toast.makeText(baseContext, "You Successfully Created Your Account.", Toast.LENGTH_LONG).show()
            finish()
        }
    }
    //To save data during screen rotation
    override fun onSaveInstanceState(outState: Bundle) {//bundle object help us to put a data
        super.onSaveInstanceState(outState)
        outState.putInt("Age", 33)
//        Log.d(ContentValues.TAG, "onSaveInstanceState: Rotation of screen" + 12)

    }
    //To restore data during screen rotation
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val data = savedInstanceState.getInt("Age")
//        Log.d(ContentValues.TAG, "onRestoreInstanceState: Restoring rotation ${data}")


    }

    private fun inputFocusListener(){
        var validEmail = false; var validPassword = false
        binding.emailEditView.setOnFocusChangeListener { _, focused ->
            if(!focused) {
                validEmail = validateEmail()
            }
        }
        binding.passwordeditview.setOnFocusChangeListener { _, focused ->
            if(!focused) {
                validPassword = validatePassword()
            }
        }
        binding.confirmEditText.setOnFocusChangeListener { _, focused ->
            if(!focused) {
                validPassword = validatePassword()
            }
        }
    }

    private fun validateEmail (): Boolean {
        val emailText = binding.emailEditView.text.toString()
        return if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
 //           binding.emailEditView.background = ContextCompat.getDrawable(baseContext, R.drawable.error_box)
            resources.getString(R.string.invalid_email).apply {
                binding.emailEditView.error = this
//                binding.tvEmailError.text = this
            }
            false
        } else {
 //           binding.etEmail.background = ContextCompat.getDrawable(baseContext, R.drawable.accepted_box)
//            "".apply { binding.tvEmailERROR.text = this }
            true
        }
    }

    private fun validatePassword (): Boolean {
        val passText = binding.passwordeditview.text.toString()
        val confText = binding.confirmEditText.text.toString()
        return if (!passText.matches("^(?=.{8,})(?=.*[a-z])(?=.*[A-Z]).*\$".toRegex())) {
 //           binding.etPassword.background = ContextCompat.getDrawable(baseContext, R.drawable.error_box)
//            binding.etConfirm.background = ContextCompat.getDrawable(baseContext, R.drawable.error_box)
            resources.getString(R.string.invalid_password).apply {
                binding.passwordeditview.error = this
//                binding.tvPasswordERROR.text = this
            }
            false
        } else if (passText != confText) {
//            binding.etPassword.background = ContextCompat.getDrawable(baseContext, R.drawable.error_box)
//            binding.etConfirm.background = ContextCompat.getDrawable(baseContext, R.drawable.error_box)
            resources.getString(R.string.invalid_password).apply {
                binding.confirmEditText.error = this
//                binding.tvPasswordERROR.text = this
            }
            false
        } else {
//            binding.etPassword.background = ContextCompat.getDrawable(baseContext, R.drawable.accepted_box)
//            binding.etConfirm.background = ContextCompat.getDrawable(baseContext, R.drawable.accepted_box)
            binding.passwordeditview.error = null
            binding.confirmEditText.error = null
//            "".apply { binding.tvPasswordERROR.text = this }
            true
        }
    }
}

//Storing data locally
//val emailText = binding.emailEditView.text.toString().lowercase().trim()
//val passwordText = binding.passwordeditview.text.toString().trim()
//val passwordConfirm = binding.confirmEditText.text.toString().trim()
//
////Validating email address
//
//binding.emailEditView.doOnTextChanged { text, start, before, count ->  }
//if (!emailText.contains(".com") && !emailText.contains("@")) {
//    binding.emailTextView.error =
//        "Invalid email, make sure you included @ and .com with in your email"
//} else {
//    binding.emailTextView.error = null
//}
////Validating the password
//
//binding.passwordeditview.doOnTextChanged { text, start, before, count -> }
//if (passwordText.length < 8 ||
//!passwordText.matches(".*[A-Z].*".toRegex()) ||
//!passwordText.matches(".*[a-z].*".toRegex()) ||
//!passwordText.matches(".*[0-9].*".toRegex())
//) {
//    binding.textInputLayout.error =
//        "The password you entered is not complete. Refer the criteria below."
//} else {
//    binding.textInputLayout.error = null
//}
//// Checking the password for the second time
//binding.confirmEditText.doOnTextChanged { text, start, before, count ->  }
//if(passwordText != passwordConfirm){
//    binding.textInputLayout2.error = "Password does not match. Re-Enter again."
//} else {
//    binding.textInputLayout2.error = null
//}