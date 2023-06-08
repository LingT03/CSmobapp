package msudenver.edu.activityresults


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible

// Key to return rainbow color name in intent
const val RAINBOW_COLOR_NAME = "RAINBOW_COLOR_NAME"

// Key to return rainbow color in intent
const val RAINBOW_COLOR = "RAINBOW_COLOR"
const val DEFAULT_COLOR = "#FFFFFF" // White

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        private val startForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { activityResult ->
                val data = activityResult.data
                val backgroundColor = data?.getIntExtra(
                    RAINBOW_COLOR, Color.parseColor(DEFAULT_COLOR))
                    ?: Color.parseColor(DEFAULT_COLOR) // Elvis Operator
                val colorName = data?.getStringExtra(RAINBOW_COLOR_NAME) ?: ""
                val colorMessage = getString(R.string.color_chosen_message, colorName)
                val rainbowColor = findViewById<TextView>(R.id.rainbow_color)
                rainbowColor.setBackgroundColor(ContextCompat.getColor(this, backgroundColor))
                rainbowColor.text = colorMessage
                rainbowColor.isVisible = true
            }
    }
}