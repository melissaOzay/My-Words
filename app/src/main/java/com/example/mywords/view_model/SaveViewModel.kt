package com.example.mywords.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.mywords.db.WordyAppDb
import com.example.mywords.db.WordyEntity

class SaveViewModel (app: Application): AndroidViewModel(app){

    private val wordyDAO by lazy {
        WordyAppDb.getAppDatabase(getApplication())?.wordyDAO()
    }

    fun addWordy(entity: WordyEntity){
        wordyDAO?.addWordy(entity)
    }

}