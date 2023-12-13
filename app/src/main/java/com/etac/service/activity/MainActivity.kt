package com.etac.service.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.etac.service.R
import com.etac.service.databinding.ActivityMainBinding
import com.etac.service.utils.Animation

class MainActivity : AppCompatActivity() {

    private val tAG = "MAIN_ACTIVITY"
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment_content_main)

        val window = window
        val decorView = window.decorView
        val wic = WindowInsetsControllerCompat(window, decorView)
        wic.isAppearanceLightStatusBars = true
        window.statusBarColor = ContextCompat.getColor(this,R.color.offWhiteApp)


        /*navController.addOnDestinationChangedListener{_,destination,_ ->
            when(destination.id){
                R.id.dashboardFragment, R.id.profileFragment-> {
                    binding.bottomBar.visibility = View.VISIBLE
                }
                R.id.dashboardFragment->{
                    binding.bottomBar.isSelected = true
                }
                else ->{
                    binding.bottomBar.visibility = View.GONE
                }
            }
        }*/

    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        try {
            when (navController.currentDestination?.id) {
                R.id.dashboardFragment,R.id.signInFragment -> {
                    finish()
                }
                R.id.laundryServiceFragment,R.id.carServiceFragment,R.id.profileFragment->{
                    navController.navigate(R.id.dashboardFragment,null,Animation.animNav().build())
                }
                else -> {
                    super.onBackPressed()
                }
            }
        }catch (e:Exception){
            Log.e(tAG , e.toString())
        }
    }
}