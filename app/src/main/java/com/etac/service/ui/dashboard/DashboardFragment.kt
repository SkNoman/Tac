package com.etac.service.ui.dashboard

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.etac.service.utils.Constant
import com.etac.service.R
import com.etac.service.adapters.DashboardMainMenuAdapter
import com.etac.service.adapters.OnClickMenu
import com.etac.service.adapters.SlideItemAdapter
import com.etac.service.base.BaseFragmentWithBinding
import com.etac.service.databinding.FragmentDashboardBinding
import com.etac.service.models.MenusItem
import com.etac.service.models.SlideItem
import com.etac.service.shared_preference.SharedPref
import com.etac.service.utils.Animation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardFragment : BaseFragmentWithBinding<FragmentDashboardBinding>
    (FragmentDashboardBinding::inflate), OnClickMenu
{
    private val handler = Handler(Looper.getMainLooper())
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val savedUserInfo = SharedPref(requireContext()).getUserInfo()
        binding.txtUserName.text = buildString {
            append(getString(R.string.good_day))
            append(", ")
            append(savedUserInfo?.name)
        }
        binding.txtUserName.setOnClickListener{
            findNavController().navigate(R.id.profileFragment,null,Animation.animNav().build())
        }

        CoroutineScope(Dispatchers.IO).launch {
            autoImageSlider()
        }
        setMenus()

        binding.bottomBar.onItemSelected = {
            when(it){
                1->{
                    val delayMillis = 500 // .5 seconds
                    val handler = Handler()
                    handler.postDelayed({
                        findNavController().navigate(R.id.profileFragment,null,Animation.animNav().build())
                        } , delayMillis.toLong())
                }
                2->{
                    Toast.makeText(requireContext(),"Coming soon",Toast.LENGTH_SHORT).show()
                    //navController.navigate(R.id.laundryServiceFragment,null,Animation.animNav().build())
                }
            }
        }
    }
    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(viewPagerHotItemRunnable)
    }
    override fun onResume() {
        super.onResume()
        handler.postDelayed(viewPagerHotItemRunnable, 5000)
    }
    private fun autoImageSlider() {
        val sliderItem : MutableList<SlideItem> = ArrayList()
        sliderItem.add(SlideItem(Constant.c1))
        sliderItem.add(SlideItem(Constant.l1))
        sliderItem.add(SlideItem(Constant.c2))
        sliderItem.add(SlideItem(Constant.l2))
        sliderItem.add(SlideItem(Constant.c3))
        sliderItem.add(SlideItem(Constant.l3))
        sliderItem.add(SlideItem(Constant.c4))
        sliderItem.add(SlideItem(Constant.l4))
        sliderItem.add(SlideItem(Constant.c5))
        sliderItem.add(SlideItem(Constant.l5))
        sliderItem.add(SlideItem(Constant.c6))
        sliderItem.add(SlideItem(Constant.l6))
        sliderItem.add(SlideItem(Constant.c7))

        binding.viewPagerHotItem.apply {
            adapter = SlideItemAdapter(requireContext(),sliderItem.shuffled().toMutableList())
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = sliderItem.size
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
        binding.viewPagerHotItem.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                handler.removeCallbacks(viewPagerHotItemRunnable)
                handler.postDelayed(viewPagerHotItemRunnable, 5000)
            }
        })
    }

    val viewPagerHotItemRunnable = object : Runnable {
        override fun run() {
            binding.viewPagerHotItem.currentItem = binding.viewPagerHotItem.currentItem + 1
            handler.postDelayed(this, 5000)
        }
    }
    private fun setMenus() {
        val menusItem: MutableList<MenusItem> = mutableListOf()
        // Add items to the menusItem list
        menusItem.add(
                MenusItem(
                        1 ,
                        "Car Service" ,
                        10 ,
                        R.drawable.car_icon_lift ,
                        Color.BLUE
                )
        )
        menusItem.add(
                MenusItem(
                        2 ,
                        "Laundry Service" ,
                        11 ,
                        R.drawable.laundry_service_icon ,
                        Color.YELLOW
                )
        )
        menusItem.add(
                MenusItem(
                        3 ,
                        "Service History" ,
                        12 ,
                        R.drawable.service_history_icon ,
                        Color.RED
                )
        )
        menusItem.add(
                MenusItem(
                        4 ,
                        "About Us" ,
                        13 ,
                        R.drawable.payment_info_icon ,
                        Color.MAGENTA
                )
        )

        showMenus(menusItem)
    }
    private fun showMenus(menusItem: List<MenusItem>) {
        binding.recyclerviewMainMenu.layoutManager =
            GridLayoutManager(activity,2,GridLayoutManager.VERTICAL,false)
        binding.recyclerviewMainMenu.adapter =
            DashboardMainMenuAdapter(requireContext(),menusItem,this)
    }
    override fun onClick(id: Int) {
        when(id){
            1->{
                findNavController().navigate(R.id.carServiceFragment , null , Animation.animNav().build())
            }
            2->{
                findNavController().navigate(R.id.laundryServiceFragment,null,Animation.animNav().build())
            }
            3->{
                findNavController().navigate(R.id.serviceHistoryFragment,null,Animation.animNav().build())
            }
            else->{
                findNavController().navigate(R.id.aboutUsFragment,null,Animation.animNav().build())
            }
        }
    }
}