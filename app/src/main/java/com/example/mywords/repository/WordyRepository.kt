package com.example.mywords.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmapp.api.RestApi
import com.example.mvvmapp.repository.project_in_repository.CreateNewUserInterface
import com.example.mvvmapp.repository.project_in_repository.UserListInterface
import com.example.mvvmapp.service.ServiceBuilder
import com.example.mywords.db.WordyAppDb
import com.example.mywords.db.WordyDAO
import com.example.mywords.db.WordyEntity
import com.example.mywords.repository.project_in_repository.DeleteWordsInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WordyRepository(application: Application) {

    private val wordyDAO by lazy {
        WordyAppDb.getAppDatabase(application)?.wordyDAO()
    }

    fun deleteFavoriteWordy(entity: WordyEntity) =
        wordyDAO!!.updateEntity(entity)

    fun deleteWordy(entity: WordyEntity) =
        wordyDAO!!.deleteFavorite(entity)

    fun getAllFavoriteWordList() =
        wordyDAO!!.getAllFavorites()

    fun addFavoriteWord(entity: WordyEntity) =
        wordyDAO!!.updateEntity(entity)

    fun createNewUser(wordyData: WordyEntity, createNewUserInterface: CreateNewUserInterface) {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.addWordy(wordyData).enqueue(object : Callback<List<WordyEntity>> {
            override fun onResponse(
                call: Call<List<WordyEntity>>,
                response: Response<List<WordyEntity>>
            ) {
                if (response.isSuccessful) {
                    response.body()
                    createNewUserInterface.onSuccess(response.body()!!)

                } else {
                    createNewUserInterface.onFail(response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<List<WordyEntity>>, t: Throwable) {
                createNewUserInterface.onFail(t.message!!)
            }
        })
    }


    fun loadData(userListInterface: UserListInterface) {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.getWordy().enqueue(object : Callback<List<WordyEntity>> {
            override fun onResponse(
                call: Call<List<WordyEntity>>,
                response: Response<List<WordyEntity>>
            ) {
                if (response.isSuccessful) {
                    userListInterface.success(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<WordyEntity>>, t: Throwable) {
                userListInterface.fail(t.localizedMessage!!)
            }
        })
    }
    fun deleteWords(id:Int,deleteInterface: DeleteWordsInterface) {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.deleteWordy(id).enqueue(object : Callback<List<WordyEntity>> {
            override fun onResponse(
                call: Call<List<WordyEntity>>,
                response: Response<List<WordyEntity>>
            ) {
                if (response.isSuccessful) {
                    deleteInterface.onSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<WordyEntity>>, t: Throwable) {
                deleteInterface.onFail(t.localizedMessage!!)
            }
        })
    }

}