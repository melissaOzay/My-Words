package com.example.mywords.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mvvmapp.repository.project_in_repository.UserListInterface
import com.example.mywords.db.*
import com.example.mywords.repository.WordyRepository
import com.example.mywords.repository.project_in_repository.DeleteWordsInterface

class HomeViewModel(app: Application) : AndroidViewModel(app) {

    val fail = MutableLiveData<String>()

    private val repository by lazy {
        WordyRepository(getApplication())
    }
    private val wordyDAO by lazy {
        WordyAppDb.getAppDatabase(getApplication())?.wordyDAO()
    }
    var wordyListInfo: MutableLiveData<List<WordyEntity>> = MutableLiveData()

    fun loadData() {
        repository.loadData(object : UserListInterface {
            override fun success(userList: List<WordyEntity>) {
                wordyListInfo.postValue(userList)
            }

            override fun fail(message: String) {
                fail.postValue(message)
            }

        })
    }

    fun addFavoriteWord(entity: WordyEntity) {
        entity.isLiked = true
        wordyDAO?.addWordy(entity)
    }

    fun deleteWordy(id: Int) {
        repository.deleteWords(id, object : DeleteWordsInterface {
            override fun onSuccess(entity: List<WordyEntity>) {
                wordyListInfo.postValue(entity)

            }

            override fun onFail(message: String) {
                fail.postValue(message)
            }

        })
    }
fun del(){

}

}