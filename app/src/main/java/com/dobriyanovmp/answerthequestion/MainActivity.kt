package com.dobriyanovmp.answerthequestion

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dobriyanovmp.answerthequestion.databinding.ActivityMainBinding
import com.dobriyanovmp.answerthequestion.databinding.AnswerAlertBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val questionsList = arrayListOf(
        mapOf("Text" to "question1", "right" to "right", "answers" to "right wrong wrong wrong"),
        mapOf("Text" to "question2", "right" to "right", "answers" to "right wrong wrong wrong"),
        mapOf("Text" to "question3", "right" to "right", "answers" to "right wrong wrong wrong"),
        mapOf("Text" to "question4", "right" to "right", "answers" to "right wrong wrong wrong"),
        mapOf("Text" to "question5", "right" to "right", "answers" to "right wrong wrong wrong"),
        mapOf("Text" to "question6", "right" to "right", "answers" to "right wrong wrong wrong"),
        mapOf("Text" to "question7", "right" to "right", "answers" to "right wrong wrong wrong"),
        mapOf("Text" to "question8", "right" to "right", "answers" to "right wrong wrong wrong"),
        mapOf("Text" to "question9", "right" to "right", "answers" to "right wrong wrong wrong"),
        mapOf("Text" to "question10", "right" to "right", "answers" to "right wrong wrong wrong"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        questionsList.shuffled()

        binding.answerButton.setOnClickListener { showAnswerDialog() }
    }

    private fun showAnswerDialog() {
        val answerAlertBinding = AnswerAlertBinding.inflate(layoutInflater)
        val answerDialog = AlertDialog.Builder(this)
            .setTitle("Answer question")
            .setView(answerAlertBinding.root)
            // .setPositiveButton("Answer")

    }
}