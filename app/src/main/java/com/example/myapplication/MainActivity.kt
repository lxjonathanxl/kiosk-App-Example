package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

private lateinit var button_kiosk_on: Button
private lateinit var button_kiosk_off: Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_kiosk_on = findViewById(R.id.button_kiosk_on)
        button_kiosk_off = findViewById(R.id.button_kiosk_off)

        button_kiosk_on.setOnClickListener {
            Toast.makeText(this, R.string.button_kiosk_on, Toast.LENGTH_SHORT).show()
            startLockTask()
         }

        button_kiosk_off.setOnClickListener {
            Toast.makeText(this, R.string.button_kiosk_off, Toast.LENGTH_SHORT).show()
            stopLockTask()
        }

    }

    override fun onResume() {
        super.onResume()
        startLockTask()
    }
}