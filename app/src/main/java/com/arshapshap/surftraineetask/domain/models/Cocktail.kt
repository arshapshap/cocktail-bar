package com.arshapshap.surftraineetask.domain.models

data class Cocktail(
    val name: String,
    val image: String?, // TODO: String ли?
    val description: String,
    val recipe: String,
    val ingredients: List<String>
)