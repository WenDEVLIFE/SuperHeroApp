package com.example.materialuiapp.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.materialuiapp.R

data class MemeHeroes(
 @DrawableRes val image: Int,
    @StringRes val name: Int,
    @StringRes val description: Int
)

val memeHeroes = listOf(
    MemeHeroes(R.drawable.victor, R.string.hero1, R.string.description1),
    MemeHeroes(R.drawable.cardo, R.string.hero2, R.string.description2),
    MemeHeroes(R.drawable.mrbeastfake, R.string.hero3, R.string.description3),
    MemeHeroes(R.drawable.superidol, R.string.hero4, R.string.description4),
    MemeHeroes(R.drawable.whyugeyy, R.string.hero5, R.string.description5),
    MemeHeroes(R.drawable.vaultboy, R.string.hero6, R.string.description6),

)
