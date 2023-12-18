package com.etac.service.ui.services

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.etac.service.R
import com.etac.service.adapters.OnClickService
import com.etac.service.adapters.ServiceHistoryListAdapter
import com.etac.service.base.BaseFragmentWithBinding
import com.etac.service.databinding.FragmentServiceHistoryBinding
import com.etac.service.models.ServiceHistoryList
import com.etac.service.utils.Animation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ServiceHistoryFragment : BaseFragmentWithBinding<FragmentServiceHistoryBinding>(
        FragmentServiceHistoryBinding::inflate),OnClickService
{
    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        binding.ivBack.setOnClickListener{
            findNavController().navigate(R.id.dashboardFragment,null,Animation.animNav().build())
        }

            val delayMillis = 500 // .5 seconds
            val handler = Handler()
            handler.postDelayed({
                                    setMenus()
                                } , delayMillis.toLong())


    }

    private fun setMenus() {
        val serviceItemList: MutableList<ServiceHistoryList> = mutableListOf()
        // Add items to the menusItem list
        serviceItemList.add(
            ServiceHistoryList(1,"Car Service Request","Lorem ipsum this is a dummy text to show on a grid to present the view","10-11-2023")
        )
        serviceItemList.add(
            ServiceHistoryList(2,"Laundry Service Request","Lorem ipsum this is a dummy text to show on a grid to present the view","11-10-2023")
        )
        serviceItemList.add(
                ServiceHistoryList(1,"Car Service Request","Lorem ipsum this is a dummy text to show on a grid to present the view","10-11-2023")
        )
        serviceItemList.add(
                ServiceHistoryList(2,"Laundry Service Request","Lorem ipsum this is a dummy text to show on a grid to present the view","11-10-2023")
        )
        serviceItemList.add(
                ServiceHistoryList(1,"Car Service Request","Lorem ipsum this is a dummy text to show on a grid to present the view","10-11-2023")
        )
        serviceItemList.add(
                ServiceHistoryList(2,"Laundry Service Request","Lorem ipsum this is a dummy text to show on a grid to present the view","11-10-2023")
        )
        showList(serviceItemList)
    }

    private fun showList(serviceItem: List<ServiceHistoryList>) {
        binding.recyclerViewServiceHistory.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        binding.recyclerViewServiceHistory.adapter =
            ServiceHistoryListAdapter(requireContext(),serviceItem,this)
    }

    override fun onClick(id: Int) {

    }
}