package com.example.mywords.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface WordyDAO {

    @Query("SELECT * FROM wordies ORDER BY id DESC")
    fun getAllWordy(): List<WordyEntity>

    @Insert
    fun addWordy(wordyEntity: WordyEntity?)

    @Update
    fun updateEntity(wordyEntity: WordyEntity?)

    @Delete
    fun deleteFavorite(wordyEntity: WordyEntity?)

    @Query("SELECT * FROM wordies WHERE isLiked=1")
    fun getAllFavorites() : List<WordyEntity>
}