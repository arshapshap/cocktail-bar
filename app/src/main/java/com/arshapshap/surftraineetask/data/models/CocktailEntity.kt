package com.arshapshap.surftraineetask.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.arshapshap.surftraineetask.data.mappers.StringListConverter

@Entity(tableName = "Cocktail")
@TypeConverters(StringListConverter::class)
data class CocktailEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cocktail_id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "recipe") val recipe: String,
    @ColumnInfo(name = "ingredients") val ingredients: List<String>
)