package com.example.authentication.activities.utils

object ColorPicker {

    val colors = arrayOf(
        "#3EB9DF",
        "#3685BC",
        "#D36280",
        "#B5BFC6",
        "#FA8056",
        "#818BCA",
        "#7D659F",
        "#51BAB3",
        "#627991",
        "#E3AD17",
    )
    var currentColorIndex = 0

    fun getColor(): String {
        currentColorIndex = (currentColorIndex +1)% colors.size
        return colors[currentColorIndex]

    }
}