package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "onCreate")

        binding.buttonKioskOn.setOnClickListener {
            Toast.makeText(this, R.string.button_kiosk_on, Toast.LENGTH_SHORT).show()
            startLockTask()
            Log.d(TAG, "startLockTask by button click")
         }

        binding.buttonKioskOff.setOnClickListener {
            Toast.makeText(this, R.string.button_kiosk_off, Toast.LENGTH_SHORT).show()
            stopLockTask()
            Log.d(TAG, "stopLockTask by button click")
        }

    }

    override fun onResume() {
        super.onResume()
        startLockTask()
        Log.d(TAG, "startLockTask by onResume")

    }
}