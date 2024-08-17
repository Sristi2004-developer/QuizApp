package com.example.authentication.activities.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.authentication.R
import com.example.authentication.activities.models.Quiz
import com.example.authentication.activities.otheractivities.QuizActivity
import com.example.authentication.activities.utils.ColorPicker
import com.example.authentication.activities.utils.IconPicker

class QuizAdapter (val context: Context, val quizzes: List<Quiz>): RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.quiz_item,parent,false)
        return QuizViewHolder(view)
    }

    override fun getItemCount(): Int {
       return quizzes.size
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
       holder.textViewTitle.text = quizzes[position].title
        holder.cardContainer.setCardBackgroundColor(Color.parseColor(ColorPicker.getColor()))
        holder.iconView.setImageResource(IconPicker.getIcon())
//        holder.itemView.setOnClickListener{
//            Toast.makeText(context,quizzes[position].title,Toast.LENGTH_SHORT).show()
//        }
        holder.itemView.setOnClickListener {
            val intent = Intent(context, QuizActivity::class.java)

            QuizActivity.questionModelList = quizzes[position].questions.values.toList()
           QuizActivity.time= 60
            intent.putExtra("QUIZ_TITLE", quizzes[position].title) // Pass quiz title as extra
            context.startActivity(intent)
        }

    }
    inner class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var textViewTitle: TextView = itemView.findViewById(R.id.quizTitle)
        var iconView: ImageView= itemView.findViewById(R.id.quizIcon)
        var cardContainer: CardView = itemView.findViewById(R.id.cardContainer)
    }
}