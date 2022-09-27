package com.example.mvvmapp.repository.project_in_repository

import com.example.mywords.db.WordyEntity


interface CreateNewUserInterface {
    fun onSuccess(data: List<WordyEntity>)
    fun onFail(message: String)
}