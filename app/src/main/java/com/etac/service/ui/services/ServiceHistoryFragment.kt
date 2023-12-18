package com.etac.service.ui.services

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.etac.service.R
import com.etac.service.adapters.OnClickService
import com.etac.service.adapters.ServiceHistoryListAdapter
import com.etac.service.base.BaseFragmentWithBinding
import com.etac.service.databinding.FragmentServiceHistoryBinding
import com.etac.service.models.ServiceHistoryList


class ServiceHistoryFragment : BaseFragmentWithBinding<FragmentServiceHistoryBinding>(
        FragmentServiceHistoryBinding::inflate),OnClickService
{
    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
       // setMenus()
    }

    private fun setMenus() {
        val serviceItemList: MutableList<ServiceHistoryList> = mutableListOf()
        // Add items to the menusItem list
        serviceItemList.add(
            ServiceHistoryList(1,"Car Service Request","Lorem ipsum this is a dummy text to show on a grid to present the view",R.drawable.car_service_icon,"10-11-2023")
        )
        serviceItemList.add(
            ServiceHistoryList(2,"Laundry Service Request","Lorem ipsum this is a dummy text to show on a grid to present the view",R.drawable.laundry_service_online,"11-10-2023")
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