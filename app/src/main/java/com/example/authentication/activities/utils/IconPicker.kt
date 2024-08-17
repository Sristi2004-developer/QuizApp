package com.example.authentication.activities.utils

import com.example.authentication.R

object IconPicker {

    val icons = arrayOf(
       R.drawable.idea,
        R.drawable.globe,
        R.drawable.maths,
        R.drawable.pencil,
        R.drawable.feather,
        R.drawable.dna,
        R.drawable.lab,
        R.drawable.apple


    )
    var currentIcon = 0

    fun getIcon(): Int {
        currentIcon = (currentIcon +1)% icons.size
        return icons[currentIcon]

    }
}