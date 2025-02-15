package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonKioskOn.setOnClickListener {
            Toast.makeText(this, R.string.button_kiosk_on, Toast.LENGTH_SHORT).show()
            startLockTask()
         }

        binding.buttonKioskOff.setOnClickListener {
            Toast.makeText(this, R.string.button_kiosk_off, Toast.LENGTH_SHORT).show()
            stopLockTask()
        }

    }

    override fun onResume() {
        super.onResume()
        startLockTask()
    }
}