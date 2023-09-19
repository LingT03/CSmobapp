package edu.msudenver.cs3013.project03

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation

class LunchOptionsFragment : Fragment() {
    /** Fragment to display the options for lunch.
     * Sends the ingredients of the correct meal to the next fragment
     * */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lunch_options, container, false)
        view.findViewById<Button>(R.id.button_salad)?.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("meal_choice", getString(R.string.salad_ingredients))//sets ingredients string
            //navigates to the next fragment according to the correct action
            Navigation.findNavController(it).navigate(R.id.action_lunchOptionsFragment_to_lunchIngredientsFragment, bundle)
        }
        view.findViewById<Button>(R.id.button_sandwhich)?.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("meal_choice", getString(R.string.sandwich_ingredients))//sets ingredients string
            //navigates to the next fragment according to the correct action
            Navigation.findNavController(it).navigate(R.id.action_lunchOptionsFragment_to_lunchIngredientsFragment, bundle)
        }
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val preferenceWrapper = (activity?.application as PreferenceApplication).preferenceWrapper
        val preferenceViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PreferenceViewModel(preferenceWrapper) as T
            }
        }).get(PreferenceViewModel::class.java)
        preferenceViewModel.getText().observe(viewLifecycleOwner, Observer {
            view.findViewById<TextView>(R.id.text_lunch_options).text = getString(R.string.meal_instruction, it)
        })
    }

    companion object {//create companion object
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LunchOptionsFragment().apply {
                arguments = Bundle()
            }
    }
}