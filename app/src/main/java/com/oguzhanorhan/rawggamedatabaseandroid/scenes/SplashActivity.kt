package com.oguzhanorhan.rawggamedatabaseandroid.scenes

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.oguzhanorhan.rawggamedatabaseandroid.MainActivity
import com.oguzhanorhan.rawggamedatabaseandroid.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val context = this

        val actionBar = supportActionBar
        actionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed(
            {
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
                finish()
            },
            2500
        )
    }
}
