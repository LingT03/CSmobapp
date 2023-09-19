package edu.msudenver.cs3013.project03

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.Navigation


class WelcomeFragment : Fragment() {
/** Opening screen that launches into instruction fragment. Takes user input and sets it as a default if not provided. */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val preferenceWrapper = (activity?.application as PreferenceApplication).preferenceWrapper
        val preferenceViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PreferenceViewModel(preferenceWrapper) as T
            }
        }).get(PreferenceViewModel::class.java)

        val view = inflater.inflate(R.layout.fragment_welcome, container, false) // Inflate fragment welcome

        // Incorporate the provided Java code here
        val constraintLayout: ConstraintLayout = view.findViewById(R.id.welcomeFragment)
        val animationDrawable = constraintLayout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()

        view.findViewById<Button>(R.id.button_start)?.setOnClickListener {//sets on click listener for button
            var user_name = view.findViewById<EditText>(R.id.enter_name_button).text.toString()
            if (user_name == ""){//set default user name.
                user_name = "Friend"
            }

            preferenceViewModel.saveText(user_name)
            Navigation.findNavController(it).navigate(R.id.action_welcomeFragment_to_instruction_fragment, null) //navigation to next fragment

        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            WelcomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}