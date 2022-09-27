package com.example.mywords.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mywords.db.WordyEntity
import com.example.mywords.db.WordyAppDb
import com.example.mywords.repository.WordyRepository

class FavoriteViewModel(app: Application) : AndroidViewModel(app) {

    private val repository by lazy {
        WordyRepository(getApplication())
    }

    var allFavorite: MutableLiveData<List<WordyEntity>> = MutableLiveData()


    fun getAllFavorite() {
        allFavorite.postValue(repository.getAllFavorite())
    }

    fun deleteFavoriInfo(entity: WordyEntity) {
        entity.isLiked = false
        repository.deleteFavoriteInfo(entity)
        getAllFavorite()

    }
}