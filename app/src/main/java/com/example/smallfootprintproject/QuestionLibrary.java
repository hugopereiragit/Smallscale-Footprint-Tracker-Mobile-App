package com.example.smallfootprintproject;

public class QuestionLibrary {
    //pergunta
    private String mQuestions [] = {
            "Last time you went shopping did you: ",
            "Have you changed your household heater's temperature",
            "Do you unplug your devices when they are not being used?",
            "Do you use LED lights on your household?",
            "Where do you purchase your produce? (vegetables,meat,dairy,etc)",
            "Are you careful when you buy your food as not to produce food waste?",
            "When you purchase clothes do you buy them from fast fashion industry (ZARA,H&M or other big chains) or do you buy used clothes",
            "When it comes to cooling do you: ",
            "Do you follow the following advice to save water: \n Turn off the tap while brushing teeth; \n Take showers instead of baths; \n Turn off the tap while using soap when washing my hands; \n Fill the sink when washing dishes or use an energy efficient dish washer"

    };

    //escolhas
    private String mChoices [][] = {
            {"I brought my own bags from home", "I bought plastic bags", "I bought reusable bags"},
            {"I never changed my heater's temperature", "Yes I increased it beyond 60ºc", "Yes I lowered to or close to 48ºc"},
            {"I always unplug my devices", "Sometimes I unplug my devices", "I never unplug my devices"},
            {"All of the lights in my house are LED", "Half of the lights on my house are LED", "All of my households lights are incandescent"},
            {"I purchase my produce from local providers","I buy the cheapest produce supermarket chains","Sometime I buy locally sometimes I buy from supermarket chains"},
            {"I am careful with the amount of food I purchase and produce little food waste","I produce some food waste and could improve","I don't take into account how much food will be wasted when I make my purchases"},
            {"I always buy my clothes from fast fashion ","Sometimes I buy used clothes","I only buy used clothes"},
            {"Use an AC targeted at 18ºc or lower","Use an AC targeted around 24ºc","Use only cooling fans"},
            {"I follow all of the above advice","I follow the shower advice","I don't follow most of the advices"}
    };

    //peso co2 da escolha
    private double mPeso [][] = {
            {0, 1, 11},
            {56, 60, 51},
            {0,0.001,0.002},
            {1, 3, 5},
            {0.21,0.25,0.23},
            {0.2,7.5,15},
            {14,7,0},
            {38,27,1},
            {0.267,0.525,0.61}
    };


    //escolha com peso maix baixo
    private String mCorrectAnswers[] = {"I brought my own bags from home", "Yes I lowered to or close to 48ºc", "I always unplug my devices",
            "All of the lights in my house are LED","I purchase my produce from local providers","I am careful with the amount of food I purchase and produce little food waste",
            "I only buy used clothes","Use only cooling fans","I follow all of the above advice"
    };




    public String getQuestion(int a) {
        String question = mQuestions[a];
        return question;
    }


    public String getChoice1(int a) {
        String choice0 = mChoices[a][0];
        return choice0;
    }

    public String getChoice2(int a) {
        String choice1 = mChoices[a][1];
        return choice1;
    }

    public String getChoice3(int a) {
        String choice2 = mChoices[a][2];
        return choice2;
    }


    public double getPeso1(int a) {
        double peso0 = mPeso[a][0];
        return peso0;
    }
    public double getPeso2(int a) {
        double peso1 = mPeso[a][1];
        return peso1;
    }
    public double getPeso3(int a) {
        double peso2 = mPeso[a][2];
        return peso2;
    }


    public String getCorrectAnswer(int a) {
        String answer = mCorrectAnswers[a];
        return answer;
    }

}

