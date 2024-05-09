package com.example.progetto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.progetto.databinding.HomepageBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePageAutonoma : AppCompatActivity() {

    private lateinit var binding: HomepageBinding

   /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val navView: BottomNavigationView = binding.navView

        // Inizializza il NavHostFragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.navigation_menu)
        bottomNavigation.setupWithNavController(navController)

        // Configura il NavigationUI per la BottomNavigationView
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_medicine, R.id.navigation_notifiche, R.id.navigation_contatti, R.id.navigation_profilo
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}*/
   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)

       binding = HomepageBinding.inflate(layoutInflater)
       setContentView(binding.root)

       val navView: BottomNavigationView = binding.navView

       val navController = findNavController(R.id.nav_host_fragment_activity_main)
       // Passing each menu ID as a set of Ids because each
       // menu should be considered as top level destinations.
       val appBarConfiguration = AppBarConfiguration(
           setOf(
               R.id.navigation_home, R.id.navigation_medicine, R.id.navigation_notifiche, R.id.navigation_contatti, R.id.navigation_profilo
           )
       )
       setupActionBarWithNavController(navController, appBarConfiguration)
       navView.setupWithNavController(navController)
   }
}
