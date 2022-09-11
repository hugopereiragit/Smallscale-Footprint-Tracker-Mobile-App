# Smallscale-Footprint-Tracker-Mobile-App

VII. Developed App

 
Fig.10 Greeting Screen
     When opening the app, we are greeted with the main screen which allows us to login, create an account or view general advice on how we can reduce our footprint. The user authentication and creation are made using firebase, which will also be the database used for the rest of the project.

 
Fig. 11 Interactive Screens

     Upon completing the authentication process successfully, we are sent to the 2nd screen in Fig.10, this screen will search the database for the latest emission report made by our account and show an interactive pie chart (movable and clickable) with data according to the report. If we are under the recommended number of emissions based on the questionnaire (54Kg) we are greeted with a fully green pie chart and a text view congratulating our efforts (see Fig.14). If however we are over the imposed limit we will be met by a pie chart showing us how much of excess Co2 emissions we are producing, followed by the most influential decision made on the bottom of the pie chart (see Fig.13). If none of the previous apply and we have no data on our account, no chart will be displayed, and no advice will be displayed.

     The previously mentioned pie charts were created using a powerful Android chart view / graph view library, supporting line- bar- pie- radar- bubble- and candlestick charts as well as scaling, panning and animations named MPAndroidChart. The documentation is written in java and is complicated which hindered progress quite a substantial amount. The Charts are filled with data pulled from firestore and connected to the current user via the authenticated user id.

     Still on the 2nd screen we have an advice horizontal scroll bar with image buttons each referring to one of the questions asked on the questionnaire who on click send the user to a specific page (see Fig.11) that holds advice and details on a given area. Afterwards we have a bottom search bar that can be used to navigate between the screens and start a new questionnaire.

     On the 1st screen we have a questionnaire with 9 questions based on research made on chapter VI with a question for each of the areas studied, the user input is converted into Kg of Co2 and added into a variable named score which is also displayed on the screen. Upon reaching the last question the final score is saved and a new document is created in the database with the information collected in the questionnaire such as the worst offending question, the date of finish the final score and the user identification. The user is allowed to quit the process at any point, not saving the questions selected.

     On the third screen we an area reserved for user data which will get our last emissions data and show its number is kg and use it to compare it to our overall emissions telling us if we are improving or worsening based on the average of all our recorded emissions, the top image turning from red thumbs down to green thumbs up accordingly, we are also provided with a % of how higher or lower our emissions are compared to our average. Afterwards we have another interactive chart, this time a bar chart, with data pulled from firestore connected to the current user’s identification. Depending on the number of data the chart will increase or decrease in size up to a maximum of 5 bars, we are also able to zoom in or out of the chart. The data is displayed from newest emissions to oldest from left to right which is indicated in the bottom. If there is no data available, the chart will not appear (If there is supposed to be data and the graph shows no data available clicking on the graph will update it and show the missing data). Along with the previous screen we have a bottom navigation bar to go to specific areas of the app.


 
Fig. 12 Advice Screens

     The previous 3 screens show general advice and detailed advice which is given upon clicking an image button, the advice is based on the investigation/research made on chapter VI, the weight in kg of average emissions per week is also provided. The advice is divided in 4 areas named: Co2 Weight, General Advice, Detailed Advice and finally References.


 
Fig. 13 Targeted Advice

     During the questionnaire process questions have an optimal answer assigned to them, when the user selects an answer other than the best one the weight in kilogram is saved and compared to the previous highest weight saved from non-optimal questions (If it’s the first then this value is 0) if the weight of the new non optimal question is higher than the previous then the question number is saved as the biggest offending question or replaced if it already exists. This number will later be used to show the user where he should target his focus first if he whishes to improve his footprint.

 
Fig. 14 Positive Main menu

     The previous screen shows the success case of the main menu where the emissions are kept qual/below the recommended amount for weekly emissions.

 	 
Fig. 15 To-do Advice

     Not all the detailed advice has been ported over from the article to the app and a placeholder template is used instead for the unfinished areas as to allow the app to be completely usable regardless of missing content. 

     The app can be downloaded from the following link: https://www.mediafire.com/file/lg7iz5qq5jsdis0/1_2AppFootprint.apk/file

     Please keep in mind that night mode design isn’t completed, and app was made to be ran in normal light mode.

     A video showcasing the application can be found on the following link: https://youtu.be/cdawWk6Cuoc