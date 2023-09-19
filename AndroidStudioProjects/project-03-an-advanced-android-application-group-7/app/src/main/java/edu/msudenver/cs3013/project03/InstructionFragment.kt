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



class InstructionFragment : Fragment() {
/** Fragment that gives the user instruction to choose which meal they want to plan*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_instruction, container, false) //inflates the fragment
        view.findViewById<Button>(R.id.button_breakfast)?.setOnClickListener{//sets click listener for breakfast button
            //sets a navigation action to the click
            Navigation.findNavController(it).navigate(R.id.action_instruction_fragment_to_breakfastOptionsFragment, null)
        }
        view.findViewById<Button>(R.id.button_lunch)?.setOnClickListener {//sets click listener for lunch button
            //sets a navigation action to the click
            Navigation.findNavController(it).navigate(R.id.action_instruction_fragment_to_lunchOptionsFragment, null)
        }
        view.findViewById<Button>(R.id.button_dinner)?.setOnClickListener {//sets click listener for dinner button
            //sets a navigation action to the click
            Navigation.findNavController(it).navigate(R.id.action_instruction_fragment_to_dinnerOptionsFragment, null)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { //gets the information from the bundle about the user name
        super.onViewCreated(view, savedInstanceState)
        val preferenceWrapper = (activity?.application as PreferenceApplication).preferenceWrapper
        val preferenceViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PreferenceViewModel(preferenceWrapper) as T
            }
        }).get(PreferenceViewModel::class.java)
        preferenceViewModel.getText().observe(viewLifecycleOwner, Observer {
            view.findViewById<TextView>(R.id.text_welcome).text = getString(R.string.plan_instruction, it)
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InstructionFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}