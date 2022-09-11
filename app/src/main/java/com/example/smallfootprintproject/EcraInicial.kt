package com.example.smallfootprintproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class EcraInicial : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecra_inicial)





        val RegistarClick = findViewById<Button>(R.id.Registar)
        val LoginClick = findViewById<Button>(R.id.Login)
        val AdviceClick = findViewById<Button>(R.id.Advice)
        RegistarClick.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        LoginClick.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        AdviceClick.setOnClickListener {
            val intent = Intent(this, GeneralAdvice::class.java)
            startActivity(intent)
        }

    }


}