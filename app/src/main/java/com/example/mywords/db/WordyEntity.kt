package com.example.mywords.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "wordies")
class WordyEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var wordyId: Int = 0,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "isLiked")
    var isLiked: Boolean = false,

    @SerializedName("surname")
    var surname: String="",
    @SerializedName("email")
    var email: String="",
    @SerializedName("password")
    var password: String=""
)
