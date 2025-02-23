package com.hannoobz.cs2weapons

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Objects


class MainActivity : AppCompatActivity() {
    private lateinit var rvWeapons: RecyclerView
    private val list = ArrayList<WeaponCard>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
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

        rvWeapons = findViewById(R.id.rv_weapons)
        rvWeapons.setHasFixedSize(true)

        list.addAll(getListWeaponCards())
        showRecyclerList()
    }

    private fun getListWeaponCards(): ArrayList<WeaponCard> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataPrice = resources.getStringArray(R.array.data_price)
        val dataType = resources.getStringArray(R.array.data_type)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listWeaponCard = ArrayList<WeaponCard>()
        for (i in dataName.indices) {
            val weaponCard = WeaponCard(i,dataName[i], dataPrice[i], dataType[i], dataPhoto.getResourceId(i, -1))
            listWeaponCard.add(weaponCard)
        }
        return listWeaponCard
    }

    private fun getListWeaponDetailData(index: Int): WeaponDetailData {
        val dataName = resources.getStringArray(R.array.data_name)[index]
        val dataPrice = resources.getStringArray(R.array.data_price)[index]
        val dataType = resources.getStringArray(R.array.data_type)[index]
        val dataDamage = resources.getStringArray(R.array.data_damage)[index]
        val dataPurchasableBy = resources.getStringArray(R.array.data_purchasable_by)[index]
        val dataDescription = resources.getStringArray(R.array.data_description)[index]
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo).getResourceId(index,-1)
        val WeaponData = WeaponDetailData(dataName,dataPrice,dataDamage,dataType,dataPurchasableBy,dataDescription,dataPhoto)
        return WeaponData
    }

    private fun showRecyclerList() {
        rvWeapons.layoutManager = LinearLayoutManager(this)
        val listWeaponAdapter = ListWeaponAdapter(list)
        rvWeapons.adapter = listWeaponAdapter
        listWeaponAdapter.setOnItemClickCallback(object : ListWeaponAdapter.OnItemClickCallback {
            override fun onItemClicked(data: WeaponCard) {
                showSelectedWeapon(data)
            }
        })
    }

    private fun showSelectedWeapon(weaponCard: WeaponCard) {
        val targetData = getListWeaponDetailData(weaponCard.id)
        val targetIntent = Intent(this@MainActivity,WeaponDetail::class.java)
        targetIntent.putExtra(WeaponDetail.WEAPON_DATA,targetData)
        startActivity(targetIntent)
//        println(list)
//        Toast.makeText(this, "Kamu memilih " + weaponCard.name + " " + weaponCard.id, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_about,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_about->{
                val aboutActivity = Intent(this@MainActivity,About::class.java)
                startActivity(aboutActivity)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

