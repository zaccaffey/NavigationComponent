package com.example.navigationcomponent

import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var listener: NavController.OnDestinationChangedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialize our navigation controller with the fragment in our activity_main xml
        navController = findNavController(R.id.fragmentContainerView2)

        // grab the drawer layout from our activity_main xml
        drawerLayout = findViewById(R.id.drawerLayout)

        // define our app bar config using the controller graph (my_nav.xml) and the drawer layout
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        // grab the nav view from our activity_main.xml
        navigationView = findViewById(R.id.navigationView)

        // set the nav controller on our navigation view
        navigationView.setupWithNavController(navController)

        // setup the action bar, passing the nav controller and the app bar config
        setupActionBarWithNavController(navController, appBarConfiguration)
        
        listener = NavController.OnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.firstFragment) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.purple_700)))
                }
            } else if (destination.id == R.id.secondFragment) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.teal_700)))
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(listener)
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView2)

        return navController.navigateUp(appBarConfiguration) ||
        super.onSupportNavigateUp()
    }
}