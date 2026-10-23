package com.example.guessthenumberk

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GuesserActivity : AppCompatActivity() {

    private var begin = 0
    private var end = 100
    private var candidate = 0
    private var attempts = 0

    private lateinit var guessedNumber: TextView
    private lateinit var yesBtn: Button
    private lateinit var greaterBtn: Button
    private lateinit var lesserBtn: Button
    private lateinit var restartBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        begin = intent.getIntExtra("begin", 0)
        end = intent.getIntExtra("end", 100)

        guessedNumber = findViewById(R.id.GuessedNumber)
        yesBtn = findViewById(R.id.yesBtn)
        greaterBtn = findViewById(R.id.GreaterBtn)
        lesserBtn = findViewById(R.id.LesserBtn)
        restartBtn = findViewById(R.id.restart)

        restartBtn.setOnClickListener { finish() }

        yesBtn.setOnClickListener { showWin() }

        greaterBtn.setOnClickListener {
            begin = candidate + 1
            if (begin > end) showLose() else nextGuess()
        }

        lesserBtn.setOnClickListener {
            end = candidate - 1
            if (begin > end) showLose() else nextGuess()
        }

        nextGuess()
    }

    /** Середина текущего диапазона — гарантирует ≤ log2(N) попыток. */
    private fun nextGuess() {
        attempts++
        candidate = begin + (end - begin) / 2
        guessedNumber.text = candidate.toString()

        val forced = begin == end
        greaterBtn.isEnabled = !forced
        lesserBtn.isEnabled = !forced
    }

    private fun showWin() {
        guessedNumber.text = getString(R.string.result_win, candidate, attempts)
        endGame()
    }

    private fun showLose() {
        guessedNumber.text = getString(R.string.result_not_found)
        endGame()
    }

    private fun endGame() {
        yesBtn.isEnabled = false
        greaterBtn.isEnabled = false
        lesserBtn.isEnabled = false
        restartBtn.visibility = View.VISIBLE
    }
}