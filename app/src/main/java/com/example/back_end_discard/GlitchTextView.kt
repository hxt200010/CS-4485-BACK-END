package com.example.back_end_discard
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import kotlin.random.Random
class GlitchTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private val random = Random
    private val maxGlitchOffset = 20  // Maximum offset in pixels for the glitch effect

    // Define a flag to trigger the glitch effect
    private var isGlitching = true

     override fun onDraw(canvas: Canvas) {
        if (isGlitching) {
            for (i in 0 until height step 10) {  // Slice height is 10 pixels, but you can adjust as needed
                val offsetY = random.nextInt(maxGlitchOffset) - maxGlitchOffset / 2
                canvas?.save()
                canvas?.clipRect(0, i, width, i + 10)
                canvas?.translate(offsetY.toFloat(), 0f)
                super.onDraw(canvas)
                canvas?.restore()
            }
        } else {
            super.onDraw(canvas)
        }
    }

    fun triggerGlitch() {
        isGlitching = true
        invalidate()
        postDelayed({
            isGlitching = false
            invalidate()
        }, 150)  // Glitch duration is 150ms, but you can adjust as needed
    }
}
