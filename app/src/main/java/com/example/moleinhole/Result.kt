package com.example.moleinhole

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Result : AppCompatActivity() {

    lateinit var prefs: Prefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val newGame: Button = findViewById(R.id.newGame)
        val actualScore: TextView = findViewById(R.id.actualScore)
        val record: TextView = findViewById(R.id.record)

        prefs = Prefs(this)

        val score = intent.getStringExtra("Score")

        actualScore.setText("RESULT: $score")

        if (prefs.isPrefsEmpty()) {
            if (prefs.isNewRecord(score!!.toInt())) {
                record.text = "NEW RECORD: $score"
                prefs.saveResult(score.toInt())
            } else {
                record.text = "RECORD: ${prefs.getResult()}"
            }
        } else {
            record.text = "NEW RECORD: $score"
            prefs.saveResult(score!!.toInt())
        }


        newGame.setOnClickListener {
            val intent = Intent(this, GameScreen::class.java)
            startActivity(intent)
        }
    }
}