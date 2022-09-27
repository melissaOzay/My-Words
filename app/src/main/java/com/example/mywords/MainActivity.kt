package com.example.mywords

import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.mywords.ui.FavoriteFragment
import com.example.mywords.ui.HomeFragment
import com.example.mywords.ui.OpenFragment
import com.example.mywords.ui.SaveFragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar


class MainActivity : AppCompatActivity(),OpenFragment {
    var bottomNav: ChipNavigationBar? = null
    var fragmentManager: FragmentManager? = null
    private lateinit var chargeLevel: ChargeLevel

    private val homeFragment by lazy {
        HomeFragment()
    }

    private val saveFragment by lazy {
        SaveFragment()
    }

    private val favoriteFragment by lazy {
        FavoriteFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        chargeLevel = ChargeLevel()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentView, HomeFragment()).commit()
        bottomNav = findViewById(R.id.bottomNavMenu)
        bottomNav?.setItemSelected(R.id.home)
        bottomNav?.setOnItemSelectedListener(object : ChipNavigationBar.OnItemSelectedListener {
            override fun onItemSelected(id: Int) {
                var fragment: Fragment? = null
                when (id) {
                    R.id.home -> fragment = homeFragment
                    R.id.add -> fragment = saveFragment
                    R.id.favorites -> fragment = favoriteFragment
                }
                if (fragment != null) {
                    fragmentManager = getSupportFragmentManager()
                    fragmentManager?.beginTransaction()
                        ?.replace(R.id.fragmentView, fragment)
                        ?.commit()
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        val filter=IntentFilter()
        filter.addAction("android.intent.action.BATTERY_LOW")
        registerReceiver(chargeLevel,filter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(chargeLevel)
    }

    override fun openFragment() {
        bottomNav?.setItemSelected(R.id.home)
    }
}
