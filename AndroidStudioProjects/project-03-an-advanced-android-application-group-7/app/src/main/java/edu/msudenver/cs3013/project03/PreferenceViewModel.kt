package edu.msudenver.cs3013.project03

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
/** ViewModel class, uses preferenceWrapper. Has two methods, saving data and getting data.
 * */
class PreferenceViewModel(private val preferenceWrapper: PreferenceWrapper) : ViewModel() {

    //method for saving text as live data
    fun saveText(text: String) {
        preferenceWrapper.saveText(text)
    }

    //method for getting the text as live data
    fun getText(): LiveData<String> {
        return preferenceWrapper.getText()
    }
}