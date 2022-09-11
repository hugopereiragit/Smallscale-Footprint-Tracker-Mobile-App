package com.example.smallfootprintproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class GeneralAdvice : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_general_advice)




        //buttons
        val bagbutton = findViewById<ImageButton>(R.id.bagbutton)
        val heatbutton = findViewById<ImageButton>(R.id.heaterbutton)
        val plugbutton = findViewById<ImageButton>(R.id.plugbutton)
        val coolingbutton = findViewById<ImageButton>(R.id.coolingbutton)
        val fastfashionbutton = findViewById<ImageButton>(R.id.fastfashionbutton)
        val foodwastebutton = findViewById<ImageButton>(R.id.foodwastebutton)
        val ledbutton = findViewById<ImageButton>(R.id.ledbutton)
        val waterbutton = findViewById<ImageButton>(R.id.waterbutton)
        val localbutton = findViewById<ImageButton>(R.id.localbutton)
        val returnbutton = findViewById<Button>(R.id.RETURN)

        bagbutton.setOnClickListener {
            val intent = Intent(this, AdviceBags::class.java)
            startActivity(intent)
        }

        heatbutton.setOnClickListener {
            val intent = Intent(this, AdviceHeat::class.java)
            startActivity(intent)

        }

        plugbutton.setOnClickListener{
            val intent = Intent(this, TodoAdvice::class.java)
            startActivity(intent)
        }
        coolingbutton.setOnClickListener{
            val intent = Intent(this, TodoAdvice::class.java)
            startActivity(intent)

        }
        fastfashionbutton.setOnClickListener{
            val intent = Intent(this, TodoAdvice::class.java)
            startActivity(intent)

        }
        foodwastebutton.setOnClickListener{
            val intent = Intent(this, TodoAdvice::class.java)
            startActivity(intent)

        }
        ledbutton.setOnClickListener{
            val intent = Intent(this, TodoAdvice::class.java)
            startActivity(intent)

        }
        waterbutton.setOnClickListener{
            val intent = Intent(this, TodoAdvice::class.java)
            startActivity(intent)

        }
        localbutton.setOnClickListener{
            val intent = Intent(this, TodoAdvice::class.java)
            startActivity(intent)

        }
        returnbutton.setOnClickListener{
            onBackPressed()
        }










    }
}