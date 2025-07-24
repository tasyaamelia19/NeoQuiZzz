package com.example.neoquizzz

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.neoquiz.QuestionModel
import com.example.neoquizzz.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        var questionModelList: List<QuestionModel> = listOf()
        var timer: String = ""
    }

    private lateinit var binding: ActivityQuizBinding
    private var currentQuestionIndex = 0
    private var selectedButton: Button? = null
    private lateinit var userAnswers: MutableList<String?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (questionModelList.isEmpty()) {
            finish()
            return
        }

        binding = ActivityQuizBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        userAnswers = MutableList(questionModelList.size) { null }

        binding.apply {
            btn1.setOnClickListener(this@QuizActivity)
            btn2.setOnClickListener(this@QuizActivity)
            btn3.setOnClickListener(this@QuizActivity)
            btn4.setOnClickListener(this@QuizActivity)
            btnNext.setOnClickListener(this@QuizActivity)
            btnBack.setOnClickListener(this@QuizActivity)
        }

        loadQuestion()
        startTimer()
    }

    private fun startTimer() {
        val totalTimeMillis = timer.toIntOrNull()?.times(60_000L) ?: 0L
        object : CountDownTimer(totalTimeMillis, 1000L) {
            override fun onFinish() {
                showScoreDialog()
            }

            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                val minutes = seconds / 60
                val remainingSeconds = seconds % 60
                binding.timerIndicatorTextview.text = String.format("%02d:%02d", minutes, remainingSeconds)
            }
        }.start()
    }

    private fun loadQuestion() {
        val question = questionModelList[currentQuestionIndex]
        binding.apply {
            questionIndicatorText.text = "Question ${currentQuestionIndex + 1}/${questionModelList.size}"
            questionProgressIndicator.progress = ((currentQuestionIndex.toFloat() / questionModelList.size) * 100).toInt()

            questionTextview.text = question.question
            btn1.text = question.options[0]
            btn2.text = question.options[1]
            btn3.text = question.options[2]
            btn4.text = question.options[3]

            btnBack.isEnabled = currentQuestionIndex > 0
            btnNext.text = if (currentQuestionIndex == questionModelList.size - 1) "Selesai" else "Next"

            resetOptionButtonsColor()
            selectedButton = null

            userAnswers[currentQuestionIndex]?.let { answer ->
                when (answer) {
                    btn1.text.toString() -> highlightSelectedButton(btn1)
                    btn2.text.toString() -> highlightSelectedButton(btn2)
                    btn3.text.toString() -> highlightSelectedButton(btn3)
                    btn4.text.toString() -> highlightSelectedButton(btn4)
                }
            }
        }
    }

    private fun resetOptionButtonsColor() {
        val defaultColor = Color.parseColor("#6B6565")
        binding.btn1.setBackgroundColor(defaultColor)
        binding.btn2.setBackgroundColor(defaultColor)
        binding.btn3.setBackgroundColor(defaultColor)
        binding.btn4.setBackgroundColor(defaultColor)
    }

    private fun highlightSelectedButton(button: Button) {
        resetOptionButtonsColor()
        button.setBackgroundColor(Color.parseColor("#E91E63"))
        selectedButton = button
    }

    override fun onClick(view: View?) {
        val clickedBtn = view as Button

        when (clickedBtn.id) {
            R.id.btn_next -> {
                if (currentQuestionIndex < questionModelList.size - 1) {
                    currentQuestionIndex++
                    loadQuestion()
                } else {
                    showConfirmationDialogBeforeScore()
                }
            }

            R.id.btn_back -> {
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex--
                    loadQuestion()
                }
            }

            R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4 -> {
                highlightSelectedButton(clickedBtn)
                userAnswers[currentQuestionIndex] = clickedBtn.text.toString()
            }
        }
    }

    private fun showConfirmationDialogBeforeScore() {
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Penyelesaian")
            .setMessage("Apakah Anda yakin telah menyelesaikan semua soal? Nilai akan segera ditampilkan.")
            .setPositiveButton("Tampilkan Nilai") { dialog, _ ->
                dialog.dismiss()
                showScoreDialog()
            }
            .setNegativeButton("Periksa Kembali") { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }


    private fun showScoreDialog() {
        val dialogView = layoutInflater.inflate(R.layout.score_dialog, null)

        val correctAnswers = questionModelList.zip(userAnswers).count {
            it.first.correct == it.second
        }

        val percentage = ((correctAnswers.toFloat() / questionModelList.size) * 100).toInt()

        val scoreTitle = dialogView.findViewById<TextView>(R.id.score_title)
        val scoreSubtitle = dialogView.findViewById<TextView>(R.id.score_subtitle)
        val finishButton = dialogView.findViewById<Button>(R.id.finish_btn)
        val tryAgainButton = dialogView.findViewById<Button>(R.id.try_again_btn)
        val progressIndicator = dialogView.findViewById<com.google.android.material.progressindicator.CircularProgressIndicator>(R.id.score_progres_indicator)
        val progressText = dialogView.findViewById<TextView>(R.id.score_progress_text)

        progressIndicator.setProgress(percentage, true)
        progressText.text = "$percentage%"

        scoreSubtitle.text = "$correctAnswers dari ${questionModelList.size} soal dijawab benar"

        when {
            correctAnswers == questionModelList.size -> {
                scoreTitle.text = "Selamat! Kamu menguasai semua materi 🎉"
            }
            percentage >= 70 -> {
                scoreTitle.text = "Bagus! Kamu menguasai sebagian besar materi"
            }
            percentage >= 40 -> {
                scoreTitle.text = "Lumayan! Masih ada yang perlu dipelajari"
            }
            else -> {
                scoreTitle.text = "Ayo belajar lagi, kamu pasti bisa!"
            }
        }

        tryAgainButton.visibility = if (percentage < 70) View.VISIBLE else View.GONE

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        finishButton.setOnClickListener {
            dialog.dismiss()
            finish()
        }

        tryAgainButton.setOnClickListener {
            dialog.dismiss()
            currentQuestionIndex = 0
            userAnswers = MutableList(questionModelList.size) { null }
            loadQuestion()
        }

        dialog.show()
    }
}
