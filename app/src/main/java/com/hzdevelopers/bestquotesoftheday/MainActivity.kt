package com.hzdevelopers.bestquotesoftheday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.hzdevelopers.bestquotesoftheday.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.bottomNavigation?.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> { navigateNow(R.id.homeFragment)
                true}
                R.id.favourite -> {navigateNow(R.id.favouriteFragment)
                true}
                else -> { false}
            }
        }
    }

    private fun navigateNow(fragment: Int) {
        val frag = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = frag.navController
        navController.navigate(fragment)
    }
}