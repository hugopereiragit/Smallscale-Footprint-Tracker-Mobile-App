package com.example.smallfootprintproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class AdviceBags : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advice_bags)

        val back = findViewById<ImageButton>(R.id.imageButton)
        back.setOnClickListener {
            onBackPressed()
        }


    }
}