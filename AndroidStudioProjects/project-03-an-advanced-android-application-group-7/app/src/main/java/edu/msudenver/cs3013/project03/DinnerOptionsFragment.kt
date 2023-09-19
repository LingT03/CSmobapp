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

class DinnerOptionsFragment : Fragment() {
    /** Fragment to display the options for dinner.
     * Sends the ingredients of the correct meal to the next fragment
     * */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dinner_options, container, false)
        view.findViewById<Button>(R.id.button_chicken)?.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("meal_choice", getString(R.string.chicken_ingredients))//sets ingredients string
            //navigates to the next fragment according to the correct action
            Navigation.findNavController(it).navigate(R.id.action_dinnerOptionsFragment_to_dinnerIngredientsFragment, bundle)
        }
        view.findViewById<Button>(R.id.button_pho)?.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("meal_choice",getString(R.string.pho_ingredients))//sets ingredients string
            //navigates to the next fragment according to the correct action
            Navigation.findNavController(it).navigate(R.id.action_dinnerOptionsFragment_to_dinnerIngredientsFragment, bundle)
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
            view.findViewById<TextView>(R.id.text_dinner_options).text = getString(R.string.meal_instruction, it)
        })
    }

    companion object {//create companion object
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DinnerOptionsFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}