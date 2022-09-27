package com.example.mvvmapp.repository.project_in_repository

import com.example.mywords.db.WordyEntity


interface UserListInterface {
    fun success(userList: List<WordyEntity>)
    fun fail(message: String)
}
