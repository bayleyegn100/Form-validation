package com.yedebkid.loginformvalidaton

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yedebkid.loginformvalidaton.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
private  val binding by lazy {
    ActivityMainBinding.inflate(layoutInflater)
}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.createAccBtnId.setOnClickListener{
            Intent(baseContext, MainActivity2::class.java).apply {
                startActivity(this)// this refers the intent
            }
        }
    }
}