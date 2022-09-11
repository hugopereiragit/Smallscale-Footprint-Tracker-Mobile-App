package com.example.smallfootprintproject

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.utils.Easing
import androidx.constraintlayout.widget.Constraints
import com.github.mikephil.charting.animation.Easing.EaseInOutQuad
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query


class MainMenu : AppCompatActivity() {
    lateinit var pieChart: PieChart
    lateinit var bottomNav : BottomNavigationView
    var db = FirebaseFirestore.getInstance()
    var user = FirebaseAuth.getInstance().currentUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)


        pieChart = findViewById(R.id.pieChart);
        val dataEntries = ArrayList<PieEntry>()
        val color: ArrayList<Int> = ArrayList()
        color.add(Color.parseColor("#c73737"))
        color.add(Color.parseColor("#70c836"))



        var recommendedAmmount: Long = 54
        var Latest : Long = 0
        var totalCount: Long = 0
        var Overlap : Long = 0
        var worstanswer: Long = 0

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
        val worstbutton = findViewById<ImageButton>(R.id.previoushigh)


        //textview
        val textview = findViewById<TextView>(R.id.advicetext)
        val gratstext = findViewById<TextView>(R.id.goodtext)





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








        db.collection("tracking")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    totalCount += 1
                }


                db.collection("tracking").whereEqualTo("user",user?.uid)
                    .orderBy("data", Query.Direction.DESCENDING).limit(1)
                    .get()
                    .addOnSuccessListener { documents ->
                        for (document in documents) {
                            Latest += document.getLong("emissions")!!
                            worstanswer = document.getLong("worstanswer")!!
                        }


                        if(worstanswer==0.toLong()){
                            worstbutton.visibility = View.INVISIBLE
                            textview.visibility = View.INVISIBLE
                        }else{
                            if(worstanswer==1.toLong()) {
                                worstbutton.setImageResource(R.drawable.bagicon)
                            }
                            if(worstanswer==2.toLong()) {
                                worstbutton.setImageResource(R.drawable.heater)
                            }
                            if(worstanswer==3.toLong()) {
                                worstbutton.setImageResource(R.drawable.unplug)
                            }
                            if(worstanswer==4.toLong()) {
                                worstbutton.setImageResource(R.drawable.led)
                            }
                            if(worstanswer==5.toLong()) {
                                worstbutton.setImageResource(R.drawable.localfood)
                            }
                            if(worstanswer==6.toLong()) {
                                worstbutton.setImageResource(R.drawable.foodwaste)
                            }
                            if(worstanswer==7.toLong()) {
                                worstbutton.setImageResource(R.drawable.fastfashion)
                            }
                            if(worstanswer==8.toLong()) {
                                worstbutton.setImageResource(R.drawable.cooling)
                            }
                            if(worstanswer==9.toLong()) {
                                worstbutton.setImageResource(R.drawable.water)
                            }
                        }



                        if(Latest > recommendedAmmount){
                            Overlap = Latest-recommendedAmmount
                            dataEntries.add(PieEntry(Overlap.toFloat(), "Your Excess"))
                            dataEntries.add(PieEntry(recommendedAmmount.toFloat(), "Recommended"))
                            val dataSet = PieDataSet(dataEntries, "")
                            dataSet.colors = color
                            val data = PieData(dataSet)


                            pieChart.data = data
                            data.setValueTextSize(15f)


                            //create hole in center
                            pieChart.holeRadius = 58f
                            pieChart.transparentCircleRadius = 61f
                            pieChart.isDrawHoleEnabled = true
                            pieChart.setHoleColor(Color.parseColor("#0f470b"))
                            pieChart.getLegend().setTextColor(Color.WHITE);

                            if(worstanswer!=0.toLong()){
                                gratstext.visibility = View.INVISIBLE
                            }

                        }else{
                            dataEntries.add(PieEntry(Latest.toFloat(), "Latest Emission"))
                            val dataSet = PieDataSet(dataEntries, "")
                            dataSet.setColor(Color.parseColor("#70c836"))
                            val data = PieData(dataSet)


                            pieChart.data = data
                            data.setValueTextSize(15f)

                            //create hole in center
                            pieChart.holeRadius = 58f
                            pieChart.transparentCircleRadius = 61f
                            pieChart.isDrawHoleEnabled = true
                            pieChart.setHoleColor(Color.parseColor("#0f470b"))

                            if(worstanswer==0.toLong()){
                                gratstext.visibility = View.VISIBLE
                            }

                        }


                    }
                    .addOnFailureListener { exception ->
                        Log.w(Constraints.TAG, "Error getting documents: ", exception)
                    }

            }
            .addOnFailureListener { exception ->
                Log.w(Constraints.TAG, "Error getting documents: ", exception)
            }



        bottomNav = findViewById(R.id.bottomNavigationView) as BottomNavigationView
        bottomNav.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.home -> {
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