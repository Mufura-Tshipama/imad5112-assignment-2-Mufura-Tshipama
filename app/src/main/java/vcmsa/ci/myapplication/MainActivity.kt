package vcmsa.ci.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val questions = arrayOf(
        "The Wright brothers invented the airplane..",
        "The Great Wall of China was built in a single year.",
        "Christopher Columbus discovered America in 1492.",
        "The Roman Empire was ruled by Julius Caesar when it fell.",
        "The first Olympic Games were held in ancient Greece."
    )

    private val answers = arrayOf(true, false, false, false, true)

    private val explanations = arrayOf(
        "TRUE! They successfully flew the first powered aircraft in 1903.",
        "FALSE! It was built over many centuries by different dynasties.",
        "FALSE! people already lived there, and other explorers had visited before him .",
        "Yes! Julius Caesar was assassinated long before the empire fell in 476 AD.",
        "Nope! They began in Olympia around 776 BC."
    )

    private var currentIndex = -1
    private var score = 0

    private lateinit var HeadingText: TextView
    private lateinit var InformationText: TextView
    private lateinit var questionText: TextView
    private lateinit var explanation :TextView
    private lateinit var HighscoreText: TextView
    private lateinit var trueBtn: Button
    private lateinit var falseBtn: Button
    private lateinit var StartBtn: Button
    private lateinit var ExitBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        HeadingText= findViewById(R.id.HeadingText)
        InformationText = findViewById(R.id.InformationText)
        questionText = findViewById(R.id.questionText)
        explanation = findViewById(R.id.explanation)
        HighscoreText = findViewById(R.id.HighscoreText)
        trueBtn = findViewById(R.id.trueBtn)
        falseBtn = findViewById(R.id.falseBtn)
        StartBtn = findViewById(R.id.StartBtn)
        ExitBtn = findViewById(R.id.ExitBtn)

        StartBtn.setOnClickListener {
            if (currentIndex == -1 || currentIndex >= questions.size) {
                startQuiz()
            } else {
                nextQuestion()
            }
        }

        trueBtn.setOnClickListener { handleAnswer(true) }
        falseBtn.setOnClickListener { handleAnswer(false) }

        ExitBtn.setOnClickListener {
            finishAffinity() // Exit the app cleanly
        }
    }

    private fun startQuiz() {
        currentIndex = 0
        score = 0
        InformationText.text = "Question 1 of ${questions.size}"
        ExitBtn.visibility = Button.GONE
        showQuestion()
    }

    private fun nextQuestion() {
        currentIndex++
        if (currentIndex < questions.size) {
            InformationText.text = "Question ${currentIndex + 1} of ${questions.size}"
            showQuestion()
        } else {
            showScore()
        }
    }

    private fun showQuestion() {
        questionText.text = questions[currentIndex]
        questionText.visibility = TextView.VISIBLE
        InformationText.visibility = TextView.GONE
        HighscoreText.visibility = TextView.GONE
        trueBtn.visibility = Button.VISIBLE
        falseBtn.visibility = Button.VISIBLE
        trueBtn.isEnabled = true
        falseBtn.isEnabled = true
        StartBtn.visibility = Button.GONE
    }

    private fun handleAnswer(selected: Boolean) {
        val isCorrect = selected == answers[currentIndex]
        if (isCorrect) score++

        InformationText.text = explanations[currentIndex]
        InformationText.visibility = TextView.VISIBLE

        trueBtn.isEnabled = false
        falseBtn.isEnabled = false

        StartBtn.text = if (currentIndex < questions.size - 1) "Next Question" else "See Results"
        StartBtn.visibility = Button.VISIBLE
    }

    private fun showScore() {
        questionText.visibility = TextView.GONE
        InformationText.visibility = TextView.VISIBLE
        InformationText.text = if (score >= 3) "Great job!" else "Keep practicing!"
        HighscoreText.text = "You scored $score out of ${questions.size}"
        HighscoreText.visibility = TextView.VISIBLE

        StartBtn.text = "Try Again"
            StartBtn.visibility = Button.VISIBLE
    }
}




