package com.oguzhanorhan.rawggamedatabaseandroid.scenes

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.oguzhanorhan.rawggamedatabaseandroid.R
import com.oguzhanorhan.rawggamedatabaseandroid.common.log.AppLog
import com.oguzhanorhan.rawggamedatabaseandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        supportActionBar?.hide()
        val navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNavigationBar.setupWithNavController(navController)

        AppLog.initFirebase(this)
    }
}
