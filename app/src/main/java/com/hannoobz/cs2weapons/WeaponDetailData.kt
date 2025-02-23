package com.hannoobz.cs2weapons
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeaponDetailData(
    val name: String,
    val price: String,
    val damage: String,
    val type: String,
    val purchasableBy: String,
    val description: String,
    val photo: Int
) : Parcelable
