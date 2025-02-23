package com.hannoobz.cs2weapons

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeaponCard(
    val id: Int,
    val name: String,
    val price: String,
    val type: String,
    val photo: Int
) : Parcelable
