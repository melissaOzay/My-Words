package com.example.mywords.repository.project_in_repository

import com.example.mywords.db.WordyEntity

interface DeleteWordsInterface {
    fun onSuccess(data: List<WordyEntity>)
    fun onFail(message: String)
}