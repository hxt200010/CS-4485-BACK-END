package com.example.back_end_discard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import android.graphics.LinearGradient
import android.view.MotionEvent
import android.view.animation.AnimationUtils
import android.graphics.Shader
import android.view.animation.Animation
import android.widget.TextView
import com.example.back_end_discard.GlitchTextView
import com.example.back_end_discard.R

import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val glitchTextView: GlitchTextView = findViewById(R.id.glitchTextView)
        glitchTextView.setOnClickListener {
            glitchTextView.triggerGlitch()
        }
        val singleButton: Button = findViewById(R.id.singleButton)
        val multiplayerButton: Button = findViewById(R.id.multiplayerButton)

        val scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up)
        val scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down)

        singleButton.setOnTouchListener { _, event ->
            handleTouchEvent(event, singleButton, scaleUp, scaleDown)
        }

        multiplayerButton.setOnTouchListener { _, event ->
            handleTouchEvent(event, multiplayerButton, scaleUp, scaleDown)
        }

        singleButton.setOnClickListener {
            // Code to start the single player mode
            startSinglePlayerMode()
        }

        multiplayerButton.setOnClickListener {
            // Code to start the multiplayer mode
            startMultiplayerMode()
        }

    }

    private fun startSinglePlayerMode() {
        // Implement the logic for single player mode here
    }

    private fun startMultiplayerMode() {
        // Implement the logic for multiplayer mode here
    }

    private fun handleTouchEvent(event: MotionEvent, button: Button, scaleUp: Animation, scaleDown: Animation): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> button.startAnimation(scaleUp)
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> button.startAnimation(scaleDown)
        }
        return false
    }
}