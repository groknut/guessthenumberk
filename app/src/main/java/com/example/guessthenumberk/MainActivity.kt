package com.example.guessthenumberk

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etBegin = findViewById<EditText>(R.id.begin)
        val etEnd = findViewById<EditText>(R.id.end)
        val toGuesser = findViewById<Button>(R.id.toGuesserBtn)

        toGuesser.setOnClickListener {
            val strBegin = etBegin.text.toString().trim()
            val strEnd = etEnd.text.toString().trim()

            if (strBegin.isEmpty() || strEnd.isEmpty()) {
                Toast.makeText(this, R.string.error_empty, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val begin = strBegin.toIntOrNull()
            val end = strEnd.toIntOrNull()
            if (begin == null || end == null) {
                Toast.makeText(this, R.string.error_not_number, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (begin >= end) {
                Toast.makeText(this, R.string.error_range, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, GuesserActivity::class.java)
            intent.putExtra("begin", begin)
            intent.putExtra("end", end)
            startActivity(intent)
        }
    }
}