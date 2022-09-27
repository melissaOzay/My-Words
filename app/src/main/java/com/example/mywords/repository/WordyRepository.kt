package com.example.mywords.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mywords.db.WordyAppDb
import com.example.mywords.db.WordyDAO
import com.example.mywords.db.WordyEntity

class WordyRepository(application: Application) {

    private val wordyDAO by lazy {
        WordyAppDb.getAppDatabase(application)?.wordyDAO()
    }

    fun addFavoriteWord(entity: WordyEntity) =
        wordyDAO!!.updateEntity(entity)

    fun deleteWordy(entity: WordyEntity) =
        wordyDAO!!.deleteFavorite(entity)

    fun getAllWordList() =
        wordyDAO!!.getAllWordy()

    fun getAllFavorite() =
        wordyDAO!!.getAllFavorites()

    fun deleteFavoriteInfo(entity: WordyEntity) =
        wordyDAO!!.updateEntity(entity)



}