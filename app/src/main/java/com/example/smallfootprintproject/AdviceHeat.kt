package com.example.smallfootprintproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class AdviceHeat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advice_heat)

        val back = findViewById<ImageButton>(R.id.imageButton)
        back.setOnClickListener {
            onBackPressed()
        }



    }




}