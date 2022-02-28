package com.example.moleinhole

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.moleinhole.DB.DbManager

class Result : AppCompatActivity() {
    val dbManager = DbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)


        dbManager.openDb()

        val newGame: Button = findViewById(R.id.newGame)
        val actualScore: TextView = findViewById(R.id.actualScore)
        val record: TextView = findViewById(R.id.record)

        val score = intent.getStringExtra("Score")

        actualScore.setText("RESULT: $score")

        if (dbManager.checkDbOnFilling()) {
            if (dbManager.isNewRecord(score!!.toInt())) {
                record.text = "NEW RECORD: $score"
                dbManager.updateItem(score, 0)
            } else {
                record.text = "RECORD: ${dbManager.readDbData()}"
            }
        } else {
            record.text = "NEW RECORD: $score"
            dbManager.insertToDb(score!!)
        }


        newGame.setOnClickListener {
            val intent = Intent(this, GameScreen::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dbManager.closeDb()
    }

    override fun onResume() {
        super.onResume()
        dbManager.openDb()
    }
}