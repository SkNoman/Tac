package com.etac.service.activity

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.etac.service.R
import com.etac.service.databinding.ActivityMainBinding
import com.etac.service.utils.Animation

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment_content_main)

        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.offWhiteApp)


        /*binding.bottomBar.onItemSelected = {
            when(it){
                0->{
                    navController.navigate(R.id.dashboardFragment,null,Animation.animNav().build())
                }
                1->{
                    navController.navigate(R.id.profileFragment,null,Animation.animNav().build())
                }
                2->{
                    //navController.navigate(R.id.laundryServiceFragment,null,Animation.animNav().build())
                }
            }
            //Toast.makeText(requireContext(),"Item $it selected",Toast.LENGTH_SHORT).show()
        }*/
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
}