package com.yedebkid.greenflag

import android.content.ContentValues
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doOnTextChanged
import com.yedebkid.greenflag.databinding.ActivityMainBinding
import com.yedebkid.greenflag.databinding.ActivityMainForLoginBinding

class MainActivityForLogin : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainForLoginBinding.inflate(layoutInflater)
    }
    val emailText = binding.emailEditText.text.toString().lowercase().trim()
    val passwordText = binding.passwordEditText.text.toString().trim()
    val passwordConfirm = binding.confirmEdittext.text.toString().trim()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        focusedEmailListener()
//        focusedPasswordListener()
//        focusedPasswordConfirmationListener()
//        binding.nextBtn.setOnClickListener { submit()}

        binding.emailEditText.doOnTextChanged { text, start, before, count -> }
        if (!emailText.contains(".com") && !emailText.contains("@")) {
            binding.emailTextView.error =
                "Invalid email, make sure you included @ and .com with in your email"
        } else {
            binding.emailTextView.error = null

            binding.passwordEditText.doOnTextChanged { text, start, before, count -> }
            if (passwordText.length < 8 || passwordText.length > 16 ||
                !passwordText.matches(".*[A-Z].*".toRegex()) ||
                !passwordText.matches(".*[a-z].*".toRegex()) ||
                !passwordText.matches(".*[@#\$%^&+=].*".toRegex()) ||
                !passwordText.matches(".*[0-9].*".toRegex())
            ) {
                binding.passwordTextView.error =
                    "Password you entered is not complete. Refer the criteria below."
            } else {
                binding.passwordTextView.error = null
            }

            binding.confirmEdittext.doOnTextChanged { text, start, before, count ->  }
            if(passwordText != passwordConfirm){
                binding.confirmTextview.error = "Password does not match. Re-Enter again."
            } else {
                binding.passwordTextView.error = null
            }
        }

    }

    private val sharedPreferences: SharedPreferences by lazy { //object
        baseContext.getSharedPreferences("My_Share_prefs", MODE_PRIVATE)
    }
    override fun onResume() {
        super.onResume()
//using sharedPreferences to save primitive data locally.
        val mString =
            binding.emailEditText.text.toString() //this is the data that I am going to save
        sharedPreferences.edit().apply {
            putString("My_Email", mString)
            apply()
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {//bundle object help us to put a data
        super.onSaveInstanceState(outState)
        outState.putInt("Age", 33)
//        Log.d(ContentValues.TAG, "onSaveInstanceState: Rotation of screen" + 12)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val data = savedInstanceState.getInt("Age")
//        Log.d(ContentValues.TAG, "onRestoreInstanceState: Restoring rotation ${data}")


    }


}