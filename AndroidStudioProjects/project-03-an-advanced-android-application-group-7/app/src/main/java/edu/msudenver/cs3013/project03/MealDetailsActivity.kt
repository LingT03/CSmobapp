package edu.msudenver.cs3013.project03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/** Activity to hold the chosen meal from the RecyclerView.
 * */
class MealDetailsActivity : AppCompatActivity() {
    private val instruction_button: Button by lazy {
        findViewById(R.id.instruction_button)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_details)

        //Set up code for DataPersistance
        val preferenceWrapper = (application as PreferenceApplication).favoritesStore
        val preferenceViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return FavoritesViewModel(preferenceWrapper) as T
            }
        })[FavoritesViewModel::class.java]
        preferenceViewModel.textLiveData.observe(this) {
            var item = it
            if (item == "") {
                item = getString(R.string.first_time_meal_detail_instructions)
            }
            findViewById<TextView>(R.id.favorites_text_fragment).text = item
        }
        //code for button
        instruction_button.setOnClickListener {
            val pickIntent = Intent(
                this,
                MainActivity::class.java
            )
            startActivity(pickIntent)//starts new activity
        }

    }



}