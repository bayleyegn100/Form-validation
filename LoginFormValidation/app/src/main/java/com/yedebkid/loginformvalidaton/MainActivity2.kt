package com.yedebkid.loginformvalidaton

import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
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
        btnEnabler()
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

    }
    //To restore data during screen rotation
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val data = savedInstanceState.getInt("Age")

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
            "This field is required and, your email should include @ and .com, try again".apply {
                binding.emailEditView.error = this
            }
            false
        } else {
            true
        }
    }

    private fun validatePassword (): Boolean {
        val passText = binding.passwordeditview.text.toString()
        val confText = binding.confirmEditText.text.toString()
        return if (!passText.matches("^(?=.{8,})(?=.*[a-z])(?=.*[A-Z]).*\$".toRegex())) {
            "Try again, see the criteria below".apply {
                binding.passwordeditview.error = this
            }
            false
        } else if (passText != confText) {
            "Password does not match, try again".apply {
                binding.confirmEditText.error = this
            }
            false
        } else {
            btnEnabler()
            binding.passwordeditview.error = null
            binding.confirmEditText.error = null
            true
        }
    }
    fun btnEnabler (){
        if(validateEmail() && validatePassword()){
            binding.button.isEnabled = true
        }
    }
}

