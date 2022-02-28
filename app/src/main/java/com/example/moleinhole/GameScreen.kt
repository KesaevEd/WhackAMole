package com.example.moleinhole

import android.content.Intent
import android.media.Image
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.app.NotificationCompatSideChannelService
import androidx.core.view.isVisible
import java.util.*
import kotlin.random.Random
import kotlinx.coroutines.*
import kotlin.collections.ArrayList
import kotlin.random.Random.Default.nextInt

class GameScreen : AppCompatActivity() {

    companion object {
        val POOL = newFixedThreadPoolContext(50, "MainJobPool")
    }

    var time_in_milli_seconds = 0L
    var time_for_restart_random = 0L
    lateinit var countdown_timer: CountDownTimer
    lateinit var countdown_timer_start: CountDownTimer
    private var mainJob: Job = Job()
    var isRuning: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)

        val timer: TextView = findViewById(R.id.timer)
        val timerStart: TextView = findViewById(R.id.startTimer)
        val counter: TextView = findViewById(R.id.score)
        var score = counter.text.toString().toInt()

        val im1: ImageButton = findViewById(R.id.im1)
        val im2: ImageButton = findViewById(R.id.im2)
        val im3: ImageButton = findViewById(R.id.im3)
        val im4: ImageButton = findViewById(R.id.im4)
        val im5: ImageButton = findViewById(R.id.im5)
        val im6: ImageButton = findViewById(R.id.im6)
        val im7: ImageButton = findViewById(R.id.im7)
        val im8: ImageButton = findViewById(R.id.im8)
        val im9: ImageButton = findViewById(R.id.im9)

        val listIm = arrayListOf(im1, im2, im3, im4, im5, im6, im7, im8, im9)

        countdown_timer_start = object : CountDownTimer(4000, 1000) {
            override fun onTick(p0: Long) {
                timerStart.isVisible = true
                time_in_milli_seconds = p0
                updateTextUI(timerStart)
            }

            override fun onFinish() {
                counter.isVisible = true
                timer.isVisible = true
                timerStart.setText("GO")
                timerStart.isVisible = false
                start(counter, timer, listIm)
            }

        }
        countdown_timer_start.start()


        /*while (isRuning) {
            val randomIndex = Random.nextInt(listIm.size);
            val randomElement = listIm[randomIndex]
            randomHole(randomElement, score, counter, System.currentTimeMillis())
        }*/

    }

    fun start(counter: TextView, timer: TextView, listIm: ArrayList<ImageButton>) {
        countdown_timer = object : CountDownTimer(30000, 500) {
            override fun onFinish() {
                isRuning = false
                Thread.sleep(400)
                val intent = Intent(this@GameScreen, Result::class.java)
                intent.putExtra("Score", counter.text)
                startActivity(intent)
            }

            override fun onTick(p0: Long) {
                time_in_milli_seconds = p0
                time_for_restart_random = p0 * 2
                updateTextUI(timer)
                if(time_for_restart_random % 2L == 0L) {
                    randomHole(counter, listIm)
                }
            }
        }
        countdown_timer.start()
    }


    fun randomHole(counter: TextView, listIm: ArrayList<ImageButton>) {
        var count = counter.text.toString().toInt()
        for (i in listIm){
            i.setImageResource(R.drawable.hole)
        }
        val randomIndex = Random.nextInt(listIm.size)
        val im = listIm[randomIndex]
        im.setImageResource(R.drawable.whole_in_hole1)
        im.isClickable = true
        im.setOnClickListener {
            im.isClickable = false
            count++
            counter.text = count.toString()
            im.setImageResource(R.drawable.hole)
        }
    }

    private fun updateTextUI(timer: TextView) {
        val seconds = (time_in_milli_seconds / 1000) % 60

        timer.text = "$seconds"
    }

    private fun restartRandom(p0: Long): Boolean {
        if (p0 % 2L == 0L) {
            return true
        }
        return false
    }

}