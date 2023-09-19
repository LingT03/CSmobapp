package edu.msudenver.cs3013.project03

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation


class LunchIngredientsFragment : Fragment() {
    /** Fragment to display the ingredients for the lunch selection.
     * Receives info from previous fragment and displays it.
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lunch_ingredients, container, false)
        view.findViewById<Button>(R.id.button_next)?.setOnClickListener(//sets onclick listener
            Navigation.createNavigateOnClickListener(R.id.action_lunchIngredientsFragment_to_mapsFragment, null) //references action
        )

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val ingredientsLunch = it?.getString("meal_choice") ?: "meal"//gets ingredients or a default.
            view.findViewById<TextView>(R.id.ingredientsList).text = getString(R.string.list_of_ingredients, ingredientsLunch)//sets ingredients string
        }
    }

    companion object {//creates companion object
        @JvmStatic
        fun newInstance() =
            LunchIngredientsFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}