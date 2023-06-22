package msudenver.edu.dualpanelayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

interface StarSignListener {
    fun onStarSignSelected(id: Int)
}
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSelected(id: Int) {
        TODO("Not yet implemented")
    }
}