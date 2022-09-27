package com.example.mywords.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmapp.repository.project_in_repository.CreateNewUserInterface
import com.example.mywords.db.WordyEntity
import com.example.mywords.repository.WordyRepository

class SaveViewModel(app: Application) : AndroidViewModel(app){
    private val repository by lazy {
        WordyRepository(getApplication())
    }
    var failure = MutableLiveData<String>()
    val wordyListInfo = MutableLiveData<List<WordyEntity>>()

    fun createNewUser(data: WordyEntity){
        repository.createNewUser(data,object: CreateNewUserInterface {
            override fun onSuccess(data: List<WordyEntity>) {
                wordyListInfo.postValue(data)
            }

            override fun onFail(message: String) {
                failure.postValue(message)
            }

        }    )
    }

}
