package com.example.smallfootprintproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class TodoAdvice : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_advice)

        val back = findViewById<ImageButton>(R.id.imageButton)
        back.setOnClickListener {
            onBackPressed()
        }

    }
}