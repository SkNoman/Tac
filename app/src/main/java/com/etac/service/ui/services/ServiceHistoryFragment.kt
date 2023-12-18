package com.etac.service.ui.services

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.etac.service.R
import com.etac.service.adapters.ServiceHistoryListAdapter
import com.etac.service.base.BaseFragmentWithBinding
import com.etac.service.databinding.FragmentServiceHistoryBinding
import com.etac.service.models.ServiceHistoryList
import com.etac.service.utils.Animation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ServiceHistoryFragment : BaseFragmentWithBinding<FragmentServiceHistoryBinding>(
        FragmentServiceHistoryBinding::inflate), ServiceHistoryListAdapter.OnClickService
{
    @Inject
    lateinit var serviceHistoryListAdapter: ServiceHistoryListAdapter
    private var adapter: ServiceHistoryListAdapter? = null
    private var serviceHistoryList = listOf<ServiceHistoryList>()
    private val serviceItemList: MutableList<ServiceHistoryList> = mutableListOf()
    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        binding.ivBack.setOnClickListener{
            findNavController().navigate(R.id.dashboardFragment,null,Animation.animNav().build())
        }
        val delayMillis = 500 // .5 seconds
        val handler = Handler()
        handler.postDelayed({ setMenus() } , delayMillis.toLong())

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }
        })
    }

    private fun filter(item: String) {
        val temp: MutableList<ServiceHistoryList> = ArrayList()
        for (i in serviceItemList) {
            //SEARCH ON DETAILS & DATE
            if (i.serviceDetails!!.contains(item, true) ||
                i.serviceDate!!.contains(item,true)){
                temp.add(i)
            }
        }
        //update recyclerview
        binding.recyclerViewServiceHistory.layoutManager = LinearLayoutManager(requireContext())
        serviceHistoryListAdapter.setData(requireContext(),temp,this)
        binding.recyclerViewServiceHistory.adapter = serviceHistoryListAdapter
    }

    private fun setMenus() {

        // Add items to the menusItem list
        serviceItemList.add(
            ServiceHistoryList(
                    1 ,
                    1 ,
                    "Car Service Request" ,
                    "Lorem ipsum this is a dummy text to show on a grid to present the view" ,
                    "10-11-2023",
                    0
            )
        )
        serviceItemList.add(
            ServiceHistoryList(
                    2 ,
                    2 ,
                    "Laundry Service Request" ,
                    "Rakib ipsum this is a dummy text to show on a grid to present the view" ,
                    "11-10-2023",
                    1
            )
        )
        serviceItemList.add(
                ServiceHistoryList(
                        3,
                        1,
                        "Car Service Request",
                        "Sk ipsum this is a dummy text to show on a grid to present the view",
                        "10-11-2023",
                        2
                )
        )
        serviceItemList.add(
                ServiceHistoryList(
                        4,
                        2,
                        "Laundry Service Request",
                        "Onim ipsum this is a dummy text to show on a grid to present the view",
                        "11-10-2023",
                        3
                )
        )
        serviceItemList.add(
                ServiceHistoryList(
                        5,
                        1,
                        "Car Service Request",
                        "Shamim ipsum this is a dummy text to show on a grid to present the view",
                        "10-11-2023",
                        2
                )
        )
        serviceItemList.add(
                ServiceHistoryList(
                        6,
                        2,
                        "Laundry Service Request",
                        "Jakaria ipsum this is a dummy text to show on a grid to present the view",
                        "11-10-2023",
                        1
                )
        )
        serviceItemList.add(
                ServiceHistoryList(
                        7,
                        1,
                        "Car Service Request",
                        "Lorem ipsum this is a dummy text to show on a grid to present the view",
                        "10-11-2023",
                        0
                )
        )
        serviceItemList.add(
                ServiceHistoryList(
                        8,
                        2,
                        "Laundry Service Request",
                        "Sir Alex ipsum this is a dummy text to show on a grid to present the view",
                        "11-10-2023",
                        1
                )
        )
        serviceItemList.add(
                ServiceHistoryList(
                        9,
                        1,
                        "Car Service Request",
                        "Lorem ipsum this is a dummy text to show on a grid to present the view",
                        "10-11-2023",
                        3
                )
        )
        serviceItemList.add(
                ServiceHistoryList(
                        10,
                        2,
                        "Laundry Service Request",
                        "Lorem ipsum this is a dummy text to show on a grid to present the view",
                        "11-10-2023",
                        2
                )
        )
        showList(serviceItemList)
    }

    private fun showList(serviceItem: List<ServiceHistoryList>) {
        binding.recyclerViewServiceHistory.layoutManager = LinearLayoutManager(requireContext())
        serviceHistoryListAdapter.setData(requireContext(),serviceItem,this)
        binding.recyclerViewServiceHistory.adapter = serviceHistoryListAdapter
    }

    override fun onClick(id: Int) {
        Toast.makeText(requireContext(),"Clicked item: $id",Toast.LENGTH_SHORT).show()
    }
}