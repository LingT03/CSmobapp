package edu.msudenver.cs3013.project03

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
/** ViewModel class for persistence.
 * */
class FavoritesViewModel (private val favoritesStore: FavoritesStore) : ViewModel() {
    private val _textLiveData = MutableLiveData<String>()
    val textLiveData: LiveData<String> = _textLiveData
    init {
        viewModelScope.launch {
            favoritesStore.text.collect { _textLiveData.value = it } //collect notifies that a new value is available
        }
    }
    fun saveText(text: String) { //both operate ina coroutine context.
        viewModelScope.launch { favoritesStore.saveText(text);

        favoritesStore.saveText(text)}
    }

}