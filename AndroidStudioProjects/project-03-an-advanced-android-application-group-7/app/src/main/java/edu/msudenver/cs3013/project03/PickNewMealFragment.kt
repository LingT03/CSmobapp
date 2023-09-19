package edu.msudenver.cs3013.project03
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.msudenver.cs3013.project03.R


/**
 *Fragment to display the user feedback about the app.
 *  Has two layouts, one for regular phone and one for larger screen size.
 */
class PickNewMealFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pick_new_meal, container, false)
    }

    companion object { //create companion object
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PickNewMealFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}