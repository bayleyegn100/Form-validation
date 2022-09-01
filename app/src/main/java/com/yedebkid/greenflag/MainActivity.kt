package com.yedebkid.greenflag

import android.content.ContentValues
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.yedebkid.greenflag.databinding.ActivityMainBinding

//private const val TAG = MainActivity.class
class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)

//        var binding.createAccBtnId: Button? = null


        binding.createAccBtnId?.setOnClickListener{
            Intent(baseContext, MainActivityForLogin::class.java).apply {
                startActivity(this)// this refers the intent
            }
        }
//    fun moveToNextActivity(view: View) {
//        Intent(baseContext, MainActivityForLogin::class.java).apply {
//            startActivity(this)// this refers the intent
////            Log.d(TAG, "MainActivityForLogin: Button clicked")
//
//
//        }
//    }
    }

    override fun onResume() {
        super.onResume()
    }
}