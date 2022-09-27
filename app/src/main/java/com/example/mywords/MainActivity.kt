package com.example.mywords

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.mywords.ui.FavoriteFragment
import com.example.mywords.ui.HomeFragment
import com.example.mywords.ui.SaveFragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar


class MainActivity : AppCompatActivity() {
    var bottomNav: ChipNavigationBar? = null
    var fragmentManager: FragmentManager? = null

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

    fun openHomeFragment(){
        bottomNav?.setItemSelected(R.id.home)
    }
}
