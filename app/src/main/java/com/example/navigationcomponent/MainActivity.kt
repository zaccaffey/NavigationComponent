package com.example.navigationcomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

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
    }
}