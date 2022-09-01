package com.yedebkid.greenflag

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doOnTextChanged
import com.yedebkid.greenflag.databinding.ActivityMainBinding
import com.yedebkid.greenflag.databinding.ActivityMainForLoginBinding

class MainActivityForLogin : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainForLoginBinding.inflate(layoutInflater)
    }
    private val sharedPreferences: SharedPreferences by lazy { //object
        baseContext.getSharedPreferences("My_Share_prefs", MODE_PRIVATE)
    }

    val emailText = binding.emailEditText.text.toString().trim()
    private val emailComponents = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+"
    val passwordText = binding.passwordEditText.text.toString().trim()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        focusedEmailListener()
//        focusedPasswordListener()
//        focusedPasswordConfirmationListener()
//        binding.nextBtn.setOnClickListener { submit()}

        binding.passwordEditText.doOnTextChanged { text, start, before, count -> }
        if (passwordText.length<8 || passwordText.length > 16 ||
            !passwordText.matches(".*[A-Z].*".toRegex()) ||
            !passwordText.matches(".*[a-z].*".toRegex()) ||
            !passwordText.matches(".*[@#\$%^&+=].*".toRegex()) ||
            !passwordText.matches(".*[0-9].*".toRegex())   ){
            binding.passwordTextView.error = "Password you entered is not complete. Refer the criteria below."
        } else {
            binding.passwordTextView.error = null
            }

//using sharedPreferences to save preemptive data locally.
            val mString = binding.emailEditText.text.toString() //this is the data that I am going to save
            sharedPreferences.edit().apply {
                putString("My_Email", mString)
                apply()
            }

    }
    private fun validConfirmedPassword(): String?{
        val confirmText = binding.confirmEdittext.text.toString()
        if (passwordText != confirmText){
            return "Password does not match."
        }
        return null
    }


//    private fun submit(){
//        val validateEmail = binding.emailTextView.helperText
//        val validatePassword = binding.passwordTextView.helperText
//        val validateConfirmedPassword = binding.confirmTextview.helperText
//
//        if (validateEmail && validatePassword && validateConfirmedPassword){
//            resetForm()
//        } else {
//            invalidForm()
//        }
//
//    }
//    private fun invalidForm(){
//        var message = ""
//        if(binding.emailTextView.helperText == null){
//            message += "\n\nEmail: " + binding.emailTextView.helperText
//        }
//        if(binding.passwordTextView.helperText == null){
//            message += "\n\nPassword: " + binding.passwordTextView.
//        }
//        AlertDialog.Builder(this)
//            .setTitle("Invalid Form")
//            .setMessage(message)
//            .setPositiveButton("Ok"){_,_ ->
//            }.show()
//    }
//
//    private fun resetForm(){
//        var message = "Email: " + binding.emailEditText.text
//            message += "\n\nPassword: " + binding.passwordEditText.text
//        AlertDialog.Builder(this)
//            .setTitle("Form Submitted")
//            .setMessage(message)
//            .setPositiveButton("Ok"){_,_ ->
//                binding.emailEditText.text == null
//                binding.passwordEditText.text == null
//                binding.emailTextView.helperText = getString(R.string.required)
//                binding.passwordTextView.helperText = getString(R.string.required)
//            }.show()
//    }

//    private fun focusedEmailListener(){
//        binding.emailEditText.setOnFocusChangeListener { _, focused ->
//            if (!focused){
//                binding.emailTextView.helperText = validEmail()
//            }
//        }
//    }
//    private fun validEmail(): String?{
//        val emailText = binding.emailEditText.text.toString()
//        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
//            return "Invalid Email Address"
//        }
//        return null
//    }
//    private fun focusedPasswordListener(){
//        binding.passwordEditText.setOnFocusChangeListener { _, focused ->
//            if (!focused){
//                binding.passwordTextView.helperText = validPassword()
//            }
//        }
//    }
//    private fun validPassword(): String?{
//
//        if (passwordText.length <8){
//            return "Minimum password character is 8"
//        }
//        if (!passwordText.matches(".*[A-Z].*".toRegex())){
//            return "Password must contain at least one upper case character"
//        }
//        if (!passwordText.matches(".*[a-z].*".toRegex())){
//            return "Password must contain at least one lower case character"
//        }
//        if (!passwordText.matches(".*[@#\$%^&+=].*".toRegex())){
//            return "Password must contain at least one special character"
//        }
//        if (!passwordText.matches(".*[0-9].*".toRegex())){
//            return "Password must contain at least one number"
//        }
//        return null
//    }
//    private fun focusedPasswordConfirmationListener(){
//        binding.confirmEdittext.setOnFocusChangeListener { _, focused ->
//            if (!focused){
//                binding.confirmTextview.helperText = validConfirmedPassword()
//            }
//        }
//    }

}