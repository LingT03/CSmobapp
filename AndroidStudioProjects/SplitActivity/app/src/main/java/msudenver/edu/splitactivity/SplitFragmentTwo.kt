package msudenver.edu.splitactivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass. Use the [SplitFragmentTwo.newInstance]
 * factory method to create an instance of this fragment.
 */
class SplitFragmentTwo : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container:
        ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_split_two, container,
            false
        )
    }

    override fun onViewCreated(
        view: View, savedInstanceState:
        Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        val totalsViewModel =
            ViewModelProvider(requireActivity()).get(TotalsViewModel::class.java)
        updateText(totalsViewModel.total)
    }

    private fun updateText(total: Int) {
        view?.findViewById<TextView>(R.id.fragment_split_two_text_view)?.text =
            getString(R.string.total, total)
    }
}