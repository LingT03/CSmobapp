package edu.msudenver.cs3013.project03

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp

import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

const val DINNER_CHOICE = "DINNER_CHOICE"
const val BREAKFAST_CHOICE = "BREAKFAST_CHOICE"
const val LUNCH_CHOICE = "LUNCH_CHOICE"
/** This is the activity that uses jetpack navigation, bottom navigation, and drawer navigation.
 * There are 8 different fragments that are connected via jetpack nav and based on this activity.
 * */
interface AnswerListener {
    fun onSelected()
}

class MainActivity : AppCompatActivity(), AnswerListener {
    private lateinit var appBarConfiguration: AppBarConfiguration;
    private lateinit var appBarConfiguration1: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
//code block for bottom drawer
        val navHostFragment1 =  supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController1 = navHostFragment1.navController
        appBarConfiguration1 = AppBarConfiguration(setOf(R.id.welcomeFragment, R.id.pickNewMeal))
        setupActionBarWithNavController(navController1, appBarConfiguration1) //needed to show label for current place
        findViewById<BottomNavigationView>(R.id.nav_view)?.setupWithNavController(navController1)
//code block for navigation drawer
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)//connects to fragment container view. Only have one active fragment at a time
                    as NavHostFragment;
        val navController = navHostFragment.navController;
        appBarConfiguration = AppBarConfiguration(//settings items to display in navigation drawer.
            setOf( //includes the following set.
                R.id.welcomeFragment, R.id.lunchOptionsFragment, R.id.dinnerOptionsFragment,
                R.id.breakfastOptionsFragment), findViewById(R.id.drawer_layout));
        setupActionBarWithNavController(navController, appBarConfiguration); //wires layout together.
        findViewById<NavigationView>(R.id.nav_view2)?.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean { //handles pressing upButton. allows for navigation back from a secondary function.
        val navController = findNavController(R.id.nav_host_fragment);
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()//checks if where we are can navigate up or if parent can
    }//back button
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu) //inflates the menu
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {//called when item on menu is selected
        return item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment))
    }//settings menu


    override fun onSelected() { //interface for fragment to be able to be used dynamically

        }
    }
