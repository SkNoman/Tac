package com.etac.service.ui.dashboard

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.etac.service.R
import com.etac.service.adapters.SlideItemAdapter
import com.etac.service.base.BaseFragmentWithBinding
import com.etac.service.databinding.FragmentDashboardBinding
import com.etac.service.models.SlideItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardFragment : BaseFragmentWithBinding<FragmentDashboardBinding>
    (FragmentDashboardBinding::inflate)
{
    private val handler = Handler(Looper.getMainLooper())
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val window = requireActivity().window
        window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.appThemeBlue)
        CoroutineScope(Dispatchers.IO).launch {
            autoPlaceSlider()
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
    private fun autoPlaceSlider() {
        val sliderItem : ArrayList<SlideItem> = ArrayList()
        sliderItem.add(SlideItem("https://img.freepik.com/free-vector/laundry-room-isometric-composition-with-conceptual-image-tablet-laundry-equipment-with-people-touchscreen_1284-32369.jpg?w=740&t=st=1701891675~exp=1701892275~hmac=87d3914f30b64e5c3da105f8122edd387a7b8e37721dab8eced7edb52fe8f89c"))
        sliderItem.add(SlideItem("https://previews.123rf.com/images/sergray/sergray1912/sergray191200097/137922158-futuristic-car-service-scanning-and-auto-data-analysis-intelligent-car-banner-futuristic-isometric.jpg"))
        sliderItem.add(SlideItem("https://img.freepik.com/premium-vector/professional-laundry-service-typography-banner-modern-washing-machine-agitation-rinsing-ironing-folding-clothing_87771-4848.jpg?w=2000"))

        binding.viewPagerHotItem.apply {
            adapter = SlideItemAdapter(requireContext(),sliderItem)
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
}