package com.yedebkid.loginformvalidaton

import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.yedebkid.loginformvalidaton.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private val binding by lazy {
        ActivityMain2Binding.inflate(layoutInflater)
    }
    private val sharedPreferences: SharedPreferences by lazy {
        baseContext.getSharedPreferences("SavedEmail", MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        emailFocusListener()
        passwordFocusedListener()
        password2FocusedListener()

        binding.button.setOnClickListener { submitForm() }
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

    private fun submitForm() {
        binding.emailTextView.helperText = validEmail()
        binding.textInputLayout.helperText = validPassword()
        binding.textInputLayout2.helperText = validPassword2()

        val validEmail = binding.emailTextView.helperText == null
        val validPassword = binding.textInputLayout.helperText == null
        val validConfrirmPassword = binding.textInputLayout2.helperText == null

        if (validEmail && validPassword && validConfrirmPassword) {
            //this is the data that I am going to save
            resetForm()
            sharedPreferences.edit().apply {
                putString(binding.emailEditView.text.toString(), binding.emailEditView.text.toString())
                apply()
            }
        } else
            invalidForm()
    }

    private fun invalidForm() {

        var message = " "
        if (binding.emailTextView.helperText != null)
            message += "\n\nEmail: " + binding.emailTextView.helperText
        if (binding.textInputLayout.helperText != null)
            message += "\n\nPassword: " + binding.textInputLayout.helperText
        if (binding.textInputLayout2.helperText != null)
            message += "\n\nPassword Matches: " + binding.textInputLayout2.helperText
        AlertDialog.Builder(this)
            .setTitle("Invalid Form")
            .setMessage(message)
            .setPositiveButton("Ok") { _, _ ->

            }
            .show()
    }

    private fun resetForm() {
        var message = "Thanks for creating your account. \nEmail: " + binding.emailEditView.text
        AlertDialog.Builder(this)
            .setTitle("Form Submited")
            .setMessage(message)
            .setPositiveButton("Ok") { _, _ ->
                binding.emailEditView.text = null
                binding.passwordeditview.text = null
                binding.confirmEditText.text = null

                binding.emailTextView.helperText = getString(R.string.Required)
                binding.textInputLayout.helperText = getString(R.string.Required)
                binding.textInputLayout2.helperText = getString(R.string.Required)
            }.show()
    }

    private fun emailFocusListener() {
        binding.emailEditView.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.emailTextView.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String? {
        val emailText = binding.emailEditView.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return " The Email you entered is not valid, try again."
        }
        return null
    }

    private fun passwordFocusedListener() {
        binding.passwordeditview.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.textInputLayout.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String? {
        val passwordText = binding.passwordeditview.text.toString()
        if (passwordText.length < 8) {
            return "Password length must be more than 8 character."
        }
        if (!passwordText.matches("^(?=.{8,})(?=.*[a-z])(?=.*[A-Z]).*\$".toRegex())) {
            return "Password does not full fill minimum requirements. Refer the criteria below."
        }

        return null
    }

    private fun password2FocusedListener() {
        binding.confirmEditText.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.textInputLayout2.helperText = validPassword()
            }
        }
    }

    private fun validPassword2(): String? {
        val password1Text = binding.passwordeditview.text.toString()
        val password2Text = binding.confirmEditText.text.toString()
        if (password2Text != password1Text) {
            return "Password does not match."
        }
        return null
    }


}



