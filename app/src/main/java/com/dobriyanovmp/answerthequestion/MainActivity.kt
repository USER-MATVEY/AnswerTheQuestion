package com.dobriyanovmp.answerthequestion

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dobriyanovmp.answerthequestion.databinding.ActivityMainBinding
import com.dobriyanovmp.answerthequestion.databinding.AnswerAlertBinding
import com.dobriyanovmp.answerthequestion.databinding.StatisticsAlertBinding

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
        mapOf("Text" to "question11", "right" to "right", "answers" to "right wrong wrong wrong"),
        mapOf("Text" to "question12", "right" to "right", "answers" to "right wrong wrong wrong"),
        mapOf("Text" to "question13", "right" to "right", "answers" to "right wrong wrong wrong"),
        mapOf("Text" to "question14", "right" to "right", "answers" to "right wrong wrong wrong"),
        mapOf("Text" to "question15", "right" to "right", "answers" to "right wrong wrong wrong")
    )

    private var rightAnswersCount = 0
    private var wrongAnswersCount = 0
    private var allAnswersCount = 0

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

        questionsList.shuffle()

        showQuestion(true)

        binding.answerButton.setOnClickListener { showAnswerDialog() }
        binding.nextButton.setOnClickListener { showQuestion(false) }
        binding.statisticButton.setOnClickListener { showStatistics() }
    }

    private fun showStatistics() {
        val statisticsAlertBinding = StatisticsAlertBinding.inflate(layoutInflater)

        statisticsAlertBinding.allCount.text = (15 - questionsList.size).toString()
        statisticsAlertBinding.rightCount.text = rightAnswersCount.toString()
        statisticsAlertBinding.wrongCount.text = wrongAnswersCount.toString()

        val statisticsAlertDialog = AlertDialog.Builder(this)
            .setView(statisticsAlertBinding.root)
            .setNeutralButton("close") {
                _, _ ->
            }
            .create()
        statisticsAlertDialog.show()
    }

    private fun showAnswerDialog() {
        val answerAlertBinding = AnswerAlertBinding.inflate(layoutInflater)

        val question = questionsList[0]
        val answers = question["answers"]!!.split(" ").shuffled()

        var rightIndex: Int = -1

        for (a in answers.indices) {
            if (answers[a] == question["right"]) rightIndex = a
        }

        answerAlertBinding.radio1.text = answers[0]
        answerAlertBinding.radio2.text = answers[1]
        answerAlertBinding.radio3.text = answers[2]
        answerAlertBinding.radio4.text = answers[3]


        val rightRadio = when (rightIndex) {
            0 -> answerAlertBinding.radio1.id
            1 -> answerAlertBinding.radio2.id
            2 -> answerAlertBinding.radio3.id
            3 -> answerAlertBinding.radio4.id
            else -> -1
        }

        val answerDialog = AlertDialog.Builder(this)
            .setView(answerAlertBinding.root)
            .setCancelable(false)
            .setPositiveButton("Answer") {
                _, _ ->
                run {
                    if (answerAlertBinding.answersGroup.checkedRadioButtonId == rightRadio) {
                        binding.result.setBackgroundColor(getColor(R.color.green))
                        rightAnswersCount++
                    }
                    else {
                        binding.result.setBackgroundColor(getColor(R.color.red))
                        wrongAnswersCount++
                    }
                    binding.answerButton.isEnabled = false
                    binding.nextButton.isEnabled = true
                    allAnswersCount++
                }
            }
            .create()
        answerDialog.show()
    }

    private fun showQuestion(isStart: Boolean) {
        val showingQuesion: Map<String, String>
        if (isStart)
            showingQuesion = questionsList[0]
        else {
            questionsList.removeAt(0)
            showingQuesion = questionsList[0]
        }

        binding.questionText.text = showingQuesion["Text"]
        binding.answerButton.isEnabled = true
        binding.nextButton.isEnabled = false
        binding.answeredCount.text = allAnswersCount.toString()

        if (questionsList.size <= 5) binding.statisticButton.isEnabled = true
        if (questionsList.size <= 1){
            binding.nextButton.isEnabled = false
            binding.answerButton.isEnabled = false
        }
    }
}