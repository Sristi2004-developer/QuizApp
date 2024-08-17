package com.example.authentication.activities.otheractivities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.authentication.R
import com.example.authentication.activities.adapters.QuizAdapter
import com.example.authentication.activities.models.Question
import com.example.authentication.activities.models.Quiz
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var adapter: QuizAdapter
    private var quizList = mutableListOf<Quiz>()
//    lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        populateDummyData()
        setUpViews()


    }
    private fun populateDummyData(){

        quizList.add(Quiz("1", "Science", mutableMapOf(
            "question1" to Question("What is the chemical symbol for water?",
                "H2O", "CO2", "O2", "NaCl","H2O"),
            "question2" to Question("What is the smallest planet in our solar system?", "Mercury", "Venus", "Mars", "Jupiter","Mercury"),
            "question3" to Question("What is the largest planet in our solar system?", "Jupiter", "Saturn", "Neptune", "Uranus","Jupiter")
        )))
        quizList.add(Quiz("2", "Programming", mutableMapOf(
            "question1" to Question("Which programming language is known for its use in Android development?", "Java", "Kotlin", "Python", "Swift", "Kotlin"),
            "question2" to Question("What does HTML stand for?", "Hyper Text Markup Language", "High Technology Modern Language", "Hyperlink and Text Markup Language", "Home Tool Markup Language", "Hyper Text Markup Language"),
            "question3" to Question("What programmimg language is known for its use in Flutter development?", "Java", "Kotlin", "Python", "Dart", "Dart")
        )
        ))
        quizList.add(Quiz("3", "Maths", mutableMapOf(
            "question1" to Question("What is the square root of 144?", "12", "14", "16", "18", "12"),
            "question2" to Question("What is the value of pi (π) to two decimal places?", "3.14", "3.16", "3.12", "3.18", "3.14"),
            "question3" to Question("What is the smallest prime number?", "2", "3", "1", "5", "2")
        )
        ))
        quizList.add(Quiz("4", "Geography", mutableMapOf(
            "question1" to Question("What is the highest mountain in the world?", "Mount Everest", "K2", "Kangchenjunga", "Lhotse", "Mount Everest"),
            "question2" to Question("Which country is known as the 'Land of the Rising Sun'?", "Japan", "China", "South Korea", "Vietnam", "Japan"),
            "question3" to Question("What is the capital city of Australia?", "Sydney", "Melbourne", "Canberra", "Perth", "Canberra")
        )
        ))
        quizList.add(Quiz("5", "GK", mutableMapOf(
            "question1" to Question("Who was the first President of the United States?", "George Washington", "Thomas Jefferson", "Abraham Lincoln", "Benjamin Franklin", "George Washington"),
            "question2" to Question("What is the capital city of Japan?", "Tokyo", "Beijing", "Seoul", "Bangkok", "Tokyo"),
            "question3" to Question("Which country won the FIFA World Cup in 2018?", "Brazil", "Germany", "France", "Argentina", "Germany")
        )))
        quizList.add(Quiz("6","Biology", mutableMapOf(
            "question1" to Question("What is the largest organ in the human body?", "Liver", "Skin", "Heart", "Brain", "Skin"),
            "question2" to Question("Which planet is known as the 'Red Planet'?", "Mars", "Venus", "Jupiter", "Saturn", "Mars"),
            "question3" to Question("What is the chemical symbol for oxygen?", "O2", "CO2", "H2O", "NaCl", "O2")
        )))
        quizList.add(Quiz("7","History",mutableMapOf(
            "question1" to Question("Who founded the Indian National Army ?", "Bhagat Singh", "Indira Gandhi", "Jawaharlal Nehru", "Subhash Chandra Bose", "Subhash Chandra Bose"),
            "question2" to Question("What is the capital city of India?", "New Delhi", "Mumbai", "Kolkata", "Chennai", "New Delhi"),
            "question3" to Question("Who is the Iron Man of India?", "Lal Bahadur Shastri", "Subhash Chandra Bose", "Mahatma Gandhi", "Sardar Vallabhbhai Patel", "Sardar Vallabhbhai Patel")

        )))
        quizList.add(Quiz("8","Physics",mutableMapOf(
            "question1" to Question("Rainbow is due to which of the following phenomena?","Dispersion","Refraction","Internal Reflection","Radiation","Dispersion"),
            "question2" to Question("Who presented the theory of relativity?","Isaac Newton","Albert Einstein","Galileo Galilei","Stephen Hawking","Albert Einstein"),
            "question3" to Question("What is the unit of electric current?","Ohm","Volt","Ampere","Watt","Ampere")
        )))




//        val listQuestionModel= mutableListOf<Quiz>()



//                quizList.add(Quiz("1","Science"))
//                quizList.add(Quiz("2","Programming"))
//                quizList.add(Quiz("3","Maths"))
//                quizList.add(Quiz("4","Geography"))

            }
            fun setUpViews(){
//        setUpFireStore()
                setUpDrawerLayout()
                setUpRecyclerView()
            }

//    private fun setUpFireStore() {
//       firestore = FirebaseFirestore.getInstance()
//        val collectionReference = firestore.collection("quizzes")
//        collectionReference.addSnapshotListener { value, error ->
//            if(value==null || error!= null){
//                Toast.makeText(this,"Error fetching data", Toast.LENGTH_SHORT).show()
//                return@addSnapshotListener
//            }
//            Log.d("DATA",value.toObjects(Quiz::class.java).toString())
//            quizList.clear()
//            quizList.addAll(value.toObjects(Quiz::class.java))
//            adapter.notifyDataSetChanged()
//
//        }
//    }

            private fun setUpRecyclerView() {
                adapter = QuizAdapter(this,quizList)
                val quizRecyclerView = findViewById<RecyclerView>(R.id.quizRecyclerView)
                quizRecyclerView.layoutManager = GridLayoutManager(this,2)
                quizRecyclerView.adapter = adapter
            }
            fun setUpDrawerLayout(){
                setSupportActionBar(findViewById(R.id.appBar))
                actionBarDrawerToggle = ActionBarDrawerToggle(this,findViewById(R.id.drawer_layout),
                    R.string.app_name,
                    R.string.app_name
                )
                actionBarDrawerToggle.syncState()

            }

            override fun onOptionsItemSelected(item: MenuItem): Boolean {
                if(actionBarDrawerToggle.onOptionsItemSelected(item)){
                    return true
                }
                return super.onOptionsItemSelected(item)
            }
        }








//    private fun setUpFireStore() {
//       firestore = FirebaseFirestore.getInstance()
//        val collectionReference = firestore.collection("quizzes")
//        collectionReference.addSnapshotListener { value, error ->
//            if(value==null || error!= null){
//                Toast.makeText(this,"Error fetching data", Toast.LENGTH_SHORT).show()
//                return@addSnapshotListener
//            }
//            Log.d("DATA",value.toObjects(Quiz::class.java).toString())
//            quizList.clear()
//            quizList.addAll(value.toObjects(Quiz::class.java))
//            adapter.notifyDataSetChanged()
//
//        }
//    }

