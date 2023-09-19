package edu.msudenver.cs3013.project03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.FragmentContainerView
import edu.msudenver.cs3013.project03.R

/** This activity receives the user input and if it doesn't receive any it uses a default.
 * */
class PickNewMeal : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pick_new_meal)
        if (savedInstanceState == null) {
            //launches the fragment that uses dual pane layout to display the necessary response.
            findViewById<FragmentContainerView>(R.id.fragment_container_pickNewMeal)?.let { frameLayout ->
                val pickNewMealFragment = PickNewMealFragment()
                supportFragmentManager.beginTransaction().add(frameLayout.id, pickNewMealFragment).commit()
            }
        }
    }
}
