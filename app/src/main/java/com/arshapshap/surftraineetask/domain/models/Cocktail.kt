package com.arshapshap.surftraineetask.domain.models

data class Cocktail(
    val id: Long,
    val name: String,
    val imageUri: String,
    val description: String,
    val recipe: String,
    val ingredients: List<String>
)