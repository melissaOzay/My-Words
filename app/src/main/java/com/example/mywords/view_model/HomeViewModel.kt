package com.example.mywords.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mywords.db.*
import com.example.mywords.repository.WordyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(app: Application) : AndroidViewModel(app) {

    private val repository by lazy {
        WordyRepository(getApplication())
    }

    var wordList: MutableLiveData<List<WordyEntity>> = MutableLiveData()

    fun getAllWordList() {
        wordList.postValue(repository.getAllWordList())
    }

    fun addFavoriteWord(entity: WordyEntity) {
        entity.isLiked = true
        repository.addFavoriteWord(entity)
    }

    fun deleteWordy(entity: WordyEntity) {
        repository.deleteWordy(entity)
        getAllWordList()
    }

}