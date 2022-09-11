package com.example.smallfootprintproject;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class QuizActivity extends AppCompatActivity {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private QuestionLibrary mQuestionLibrary = new QuestionLibrary();

    private TextView mScoreView;
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button quitbutton;
    private int mworstchoice = 0;
    private double mworstscore = 0;

    private String mAnswer;
    private double mPeso1 = 0;
    private double mPeso2 = 0;
    private double mPeso3 = 0;
    private double mScore = 0;
    private int mQuestionNumber = 0;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mScoreView = (TextView)findViewById(R.id.score);
        mQuestionView = (TextView)findViewById(R.id.question);
        mButtonChoice1 = (Button)findViewById(R.id.choice1);
        mButtonChoice2 = (Button)findViewById(R.id.choice2);
        mButtonChoice3 = (Button)findViewById(R.id.choice3);
        quitbutton = (Button)findViewById(R.id.quit);

        updateQuestion();

        //Start of Button Listener for Button1
        mButtonChoice1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){


                if (mButtonChoice1.getText() == mAnswer){
                    mScore = mScore + mPeso1;
                    updateScore(mScore);
                    updateQuestion();
                    //This line of code is optiona
                    Toast.makeText(QuizActivity.this, "Best choice", Toast.LENGTH_SHORT).show();

                }else {
                 //   Toast.makeText(QuizActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                    if(mworstscore < mPeso1) {
                    mworstchoice = mQuestionNumber;
                    mworstscore = mPeso1;
                    }

                    mScore = mScore + mPeso1;
                    updateScore(mScore);
                    updateQuestion();



                }
            }
        });

        mButtonChoice2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){


                if (mButtonChoice2.getText() == mAnswer){
                    mScore = mScore + mPeso2;
                    updateScore(mScore);
                    updateQuestion();
                    //This line of code is optiona
                    Toast.makeText(QuizActivity.this, "Best choice", Toast.LENGTH_SHORT).show();

                }else {
                  //  Toast.makeText(QuizActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                    if(mworstscore < mPeso2) {
                        mworstchoice = mQuestionNumber;
                        mworstscore = mPeso2;
                        Log.d("Message tag",String.valueOf(mworstscore));
                        Log.d("Message tag",String.valueOf(mworstchoice));
                    }
                    mScore = mScore + mPeso2;
                    updateScore(mScore);
                    updateQuestion();
                }
            }
        });


        mButtonChoice3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                if (mButtonChoice3.getText() == mAnswer){
                    mScore = mScore + mPeso3;
                    updateScore(mScore);
                    updateQuestion();
                    //This line of code is optiona
                    Toast.makeText(QuizActivity.this, "Best choice", Toast.LENGTH_SHORT).show();
                    Log.d("Message tag",String.valueOf(mPeso3));
                }else {
                   // Toast.makeText(QuizActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                   // Log.d("Message tag",String.valueOf(mPeso3));
                    if(mworstscore < mPeso3) {
                        mworstchoice = mQuestionNumber;
                        mworstscore = mPeso3;
                    }


                    mScore = mScore + mPeso3;
                    updateScore(mScore);
                    updateQuestion();


                }
            }
        });

        //End of Button Listener for Button3

        quitbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
              quit();
                //Adicionar o melhor scor possivel restante ao carregar no botão
               /* if (mQuestionNumber==0){

                }
                if (mQuestionNumber==1){

                }
                if (mQuestionNumber==2){

                }
                if (mQuestionNumber==3){

                }
                if (mQuestionNumber==4){

                }
                if (mQuestionNumber==5){

                }
                if (mQuestionNumber==6){

                }
                if (mQuestionNumber==7){

                }
                if (mQuestionNumber==8){

                }
                if (mQuestionNumber==9){

                }    */
            }
        });



    }




    private void updateQuestion(){
        if(mQuestionNumber<9) {
            mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
            mButtonChoice1.setText(mQuestionLibrary.getChoice1(mQuestionNumber));
            mButtonChoice2.setText(mQuestionLibrary.getChoice2(mQuestionNumber));
            mButtonChoice3.setText(mQuestionLibrary.getChoice3(mQuestionNumber));
            mPeso1 = mQuestionLibrary.getPeso1(mQuestionNumber);
            mPeso2 = mQuestionLibrary.getPeso2(mQuestionNumber);
            mPeso3 = mQuestionLibrary.getPeso3(mQuestionNumber);

            mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
            mQuestionNumber++;
        }

        else{
            Toast.makeText(QuizActivity.this, "Final Emission number is :" + mScore + " kg of CO2", Toast.LENGTH_LONG).show();




            // Create a new user with a first and last name
            Map<String, Object> emissao = new HashMap<>();
            emissao.put("emissions", mScore);
            emissao.put("data", FieldValue.serverTimestamp());
            emissao.put("user",user.getUid());
            emissao.put("worstanswer",mworstchoice);


            // Add a new document with a generated ID
            db.collection("tracking")
                    .add(emissao)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });

            Intent intent = new Intent(this, MainMenu.class);
            startActivity(intent);
        }

    }

    private void quit(){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
    private void updateScore(double point) {
        mScoreView.setText("" + mScore);
    }


}