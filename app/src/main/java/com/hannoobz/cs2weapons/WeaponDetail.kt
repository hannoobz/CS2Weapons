package com.hannoobz.cs2weapons

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Objects

class WeaponDetail : AppCompatActivity() {
    companion object {
        const val WEAPON_DATA = "DATA"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_weapon_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.parseColor("#F1F1F2")
        }

        Objects.requireNonNull(supportActionBar)?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#272A3B")))
        supportActionBar?.setTitle(Html.fromHtml("<font color=\"#F1F1F2\">" + getString(R.string.app_name) + "</font>"))

        val weaponrcvd = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<WeaponDetailData>(WEAPON_DATA, WeaponDetailData::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<WeaponDetailData>(WEAPON_DATA)
        }

        if (weaponrcvd != null) {
            val name = weaponrcvd.name.toString()
            val price = weaponrcvd.price.toString()
            val type = weaponrcvd.type.toString()
            val purchasableBy = weaponrcvd.purchasableBy.toString()
            val damage = weaponrcvd.damage.toString()
            val desc = weaponrcvd.description.toString()
            val photo = weaponrcvd.photo

            val tvName: TextView = findViewById(R.id.detail_name)
            tvName.text = name

            val tvPrice: TextView = findViewById(R.id.detail_priceValue)
            tvPrice.text = price

            val tvType: TextView = findViewById(R.id.detail_typeValue)
            tvType.text = type

            val tvPurchasableBy: TextView = findViewById(R.id.detail_purchasableByValue)
            tvPurchasableBy.text = purchasableBy

            val tvDamage: TextView = findViewById(R.id.detail_damageValue)
            tvDamage.text = damage

            val tvDesc: TextView = findViewById(R.id.detail_description)
            tvDesc.text = desc

            val ivWeapon: ImageView = findViewById(R.id.detail_image)
            ivWeapon.setImageResource(photo)
        }
    }
}