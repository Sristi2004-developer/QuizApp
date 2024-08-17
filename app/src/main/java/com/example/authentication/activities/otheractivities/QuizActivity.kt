package com.example.authentication.activities.otheractivities

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.authentication.R
import com.example.authentication.activities.models.Question
import com.example.authentication.databinding.ActivityQuizBinding
import com.example.authentication.databinding.ScoreDialogBinding

class QuizActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        var questionModelList : List<Question> = listOf()
        var time = 60
    }
    lateinit var binding: ActivityQuizBinding
    var currentQuestionIndex = 0
    var selectedAnswer=""
    var score=0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply{
            btn1.setOnClickListener(this@QuizActivity)
            btn2.setOnClickListener(this@QuizActivity)
            btn3.setOnClickListener(this@QuizActivity)
            btn4.setOnClickListener(this@QuizActivity)
            nextBtn.setOnClickListener(this@QuizActivity)
        }
        loadQuestions()
        startTimer()
    }

    private fun startTimer() {
        val totalTimeInMs = 5 * 60 * 1000L
        object : CountDownTimer(totalTimeInMs, 1000L){
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished/1000
                val minutes =seconds/60
                val remainingSeconds = seconds % 60
                binding.timer.text = String.format("%02d:%02d",minutes,remainingSeconds)

            }

            override fun onFinish() {

            }
        }.start()
    }

    private fun loadQuestions(){

        binding.apply{
            btn1.setBackgroundColor(ContextCompat.getColor(this@QuizActivity, R.color.unselected_color))
            btn2.setBackgroundColor(ContextCompat.getColor(this@QuizActivity, R.color.unselected_color))
            btn3.setBackgroundColor(ContextCompat.getColor(this@QuizActivity, R.color.unselected_color))
            btn4.setBackgroundColor(ContextCompat.getColor(this@QuizActivity, R.color.unselected_color))
        }
        selectedAnswer=""
        if(currentQuestionIndex==questionModelList.size){
            endQuiz()
            return
        }
        binding.apply{
            questionIndicator.text = "Question ${currentQuestionIndex+1}/${questionModelList.size}"
            questionProgressIndicator.progress = (currentQuestionIndex.toFloat()/questionModelList.size.toFloat()*100).toInt()
            questionTextview.text = questionModelList[currentQuestionIndex].description
            btn1.text = questionModelList[currentQuestionIndex].option1
            btn2.text = questionModelList[currentQuestionIndex].option2
            btn3.text = questionModelList[currentQuestionIndex].option3
            btn4.text = questionModelList[currentQuestionIndex].option4
        }
    }

    override fun onClick(view: View?) {


        val clickedBtn = view as Button
        if(clickedBtn.id== R.id.next_btn){

            //if next btn is clicked
            if(selectedAnswer.isNotEmpty()){
                if(selectedAnswer==questionModelList[currentQuestionIndex].answer){
                    score++
                }
            }
            currentQuestionIndex++
            loadQuestions()
        }else{
            //if options button is clicked

            binding.btn1.setBackgroundColor(ContextCompat.getColor(this, R.color.unselected_color))
            binding.btn2.setBackgroundColor(ContextCompat.getColor(this, R.color.unselected_color))
            binding.btn3.setBackgroundColor(ContextCompat.getColor(this, R.color.unselected_color))
            binding.btn4.setBackgroundColor(ContextCompat.getColor(this, R.color.unselected_color))

            clickedBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.selected_color))
            selectedAnswer = clickedBtn.text.toString()


        }


    }


    private fun endQuiz(){
        val totalQuestions= questionModelList.size
        val percentage=((score.toFloat()/totalQuestions.toFloat())*100).toInt()

        val dialogBinding= ScoreDialogBinding.inflate(layoutInflater)
        dialogBinding.apply {
            scoreIndicator.progress=percentage
            scorePercentage.text="$percentage%"
            if(percentage>50){
                scoreTitle.text="Congratulations! You won the quiz"
                scoreSubtitle.text="You scored $score out of $totalQuestions"
                scoreTitle.setTextColor(Color.GREEN)
            }else{
                scoreTitle.text="Oops! You lost the quiz"
                scoreSubtitle.text="You scored $score out of $totalQuestions"
                scoreTitle.setTextColor(Color.RED)
            }

            val alertDialog= AlertDialog.Builder(this@QuizActivity)
                .setView(dialogBinding.root)
                .setCancelable(false)
                .show()
            finishBtn.setOnClickListener {
                currentQuestionIndex=0
                score=0
                loadQuestions()
                alertDialog.dismiss()

            }
        }

    }
}