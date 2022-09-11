package com.example.smallfootprintproject

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Constraints.TAG
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query


class MyEmissions : AppCompatActivity() {
    lateinit var  barList:ArrayList<BarEntry>
    lateinit var lineDataSet: BarDataSet
    lateinit var barData: BarData
    lateinit var percentagem: TextView
    lateinit var lastscore: TextView
    lateinit var imagem: ImageView
    lateinit var layout: LinearLayout
    lateinit var bottomNav : BottomNavigationView

    var db = FirebaseFirestore.getInstance()
    var user = FirebaseAuth.getInstance().currentUser
    var test :Long = 0
    var test2:Float = 0f
    var total : Long = 0
    var totalCount: Long = 0
    var Latest : Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_emissions)
    val barChart = findViewById<BarChart>(R.id.barChart)

        percentagem = findViewById(R.id.percentage)
        lastscore = findViewById(R.id.latestemissions)
        imagem = findViewById(R.id.imagem)
        layout = findViewById(R.id.layout)
        // #70c836 verde
        // #c73737 vermelho

        db.collection("tracking")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    total += document.getLong("emissions")!!
                    totalCount += 1
                }


                db.collection("tracking").whereEqualTo("user",user?.uid)
                    .orderBy("data", Query.Direction.DESCENDING).limit(1)
                    .get()
                    .addOnSuccessListener { documents ->
                        for (document in documents) {
                            Latest += document.getLong("emissions")!!
                        }

                        if(Latest > total/totalCount){
                            lastscore.setText(Latest.toString() + "/Kg of CO2")
                            imagem.setImageResource(R.drawable.dislike)
                            percentagem.setText(((Latest*100)/(total/totalCount)).toString() + "% worse than your average")
                            layout.setBackgroundColor(Color.parseColor("#c73737"))
                        }else{
                            lastscore.setText(Latest.toString() + "/Kg of CO2")
                            imagem.setImageResource(R.drawable.like)
                            percentagem.setText(((Latest*100)/(total/totalCount)).toString() + "% better than your average")
                            layout.setBackgroundColor(Color.parseColor("#70c836"))
                        }

                    }
                    .addOnFailureListener { exception ->
                        Log.w(TAG, "Error getting documents: ", exception)
                    }

            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }










                // barras
        barList = ArrayList()
        db.collection("tracking").whereEqualTo("user",user?.uid)
            .orderBy("data", Query.Direction.DESCENDING).limit(5)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    test = document.getLong("emissions")!!
                    Log.d(TAG,test.toString())
                    test2 += 1f
                    barList.add(BarEntry(test2, test.toFloat()))
                }


                lineDataSet = BarDataSet(barList,"Population")
                barData = BarData(lineDataSet)
                lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS,250)
                barChart.data = barData
                lineDataSet.valueTextColor= Color.BLACK
                lineDataSet.valueTextSize = 15f


            }
            .addOnFailureListener { exception ->
            Log.w(TAG, "Error getting documents: ", exception)
            }



        bottomNav = findViewById(R.id.bottomNavigationView) as BottomNavigationView
        bottomNav.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.home -> {
                    val intent = Intent(this, MainMenu::class.java)
                    startActivity(intent)
                }
                R.id.info -> {
                    val intent = Intent(this, MyEmissions::class.java)
                    startActivity(intent)
                }
                R.id.track -> {
                    val intent = Intent(this, QuizActivity::class.java)
                    startActivity(intent)
                }
            }
        }


    }



}