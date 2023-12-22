package com.etac.service.ui.services

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.etac.service.R
import com.etac.service.adapters.ServiceHistoryListAdapter
import com.etac.service.base.BaseFragmentWithBinding
import com.etac.service.databinding.FragmentServiceHistoryBinding
import com.etac.service.dialogs.PaymentInfoSubmitDialog
import com.etac.service.models.service.ServiceHistoryList
import com.etac.service.network.ApiEndPoint
import com.etac.service.utils.Animation
import com.etac.service.utils.AppUtils
import com.etac.service.utils.CheckNetworkStatus
import com.etac.service.utils.Constant
import com.etac.service.viewmodels.ServiceViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ServiceHistoryFragment : BaseFragmentWithBinding<FragmentServiceHistoryBinding>(
        FragmentServiceHistoryBinding::inflate), ServiceHistoryListAdapter.OnClickService
{
    @Inject
    lateinit var serviceHistoryListAdapter: ServiceHistoryListAdapter
    private var serviceItemList: MutableList<ServiceHistoryList> = mutableListOf()

    private val serviceViewModel: ServiceViewModel by viewModels()
    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        binding.ivBack.setOnClickListener{
            findNavController().navigate(R.id.dashboardFragment,null,Animation.animNav().build())
        }

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }
        })

        CheckNetworkStatus.isOnline(requireContext(),object:CheckNetworkStatus.Status{
            override fun online() {
                try {
                    onLoadingVm().showLoadingFun(true)
                    serviceViewModel.getServiceList(ApiEndPoint.GET_SERVICE_LIST,"01718228277")
                }catch (e:Exception){
                    onLoadingVm().showLoadingFun(false)
                    AppUtils.showToast(requireContext(),
                                       Constant.ERROR_MESSAGE, false, getString(R.string.toast_type_warning))
                }
            }

            override fun offline() {
                onLoadingVm().showLoadingFun(false)
                AppUtils.showToast(requireContext(),
                                   getString(R.string.pls_check_internet), false, getString(R.string.toast_type_warning))
            }

        })

        serviceViewModel.serviceListRes.observe(viewLifecycleOwner) { data ->
            data.getContentIfNotHandled().let {
                onLoadingVm().showLoadingFun(false)
                if (it?.result_code == 0) {
                    serviceItemList = it.result.toMutableList()
                    showList(it.result)
                }
            }
        }
        serviceViewModel.errorResponse.observe(viewLifecycleOwner){error ->
            onLoadingVm().showLoadingFun(false)
            error.getContentIfNotHandled().let {
                AppUtils.showToast(requireContext(),
                                   it?.message.toString(), false, getString(R.string.toast_type_error))
            }
        }


    }

    private fun filter(item: String) {
        val temp: MutableList<ServiceHistoryList> = ArrayList()
        for (i in serviceItemList) {
            //SEARCH ON DETAILS & DATE
            if (i.service_details!!.contains(item, true) ||
                i.created_at!!.contains(item,true)){
                temp.add(i)
            }
        }
        //update recyclerview
        binding.recyclerViewServiceHistory.layoutManager = LinearLayoutManager(requireContext())
        serviceHistoryListAdapter.setData(requireContext(),temp,this)
        binding.recyclerViewServiceHistory.adapter = serviceHistoryListAdapter
    }

    private fun showList(serviceItem: List<ServiceHistoryList>) {
        binding.recyclerViewServiceHistory.layoutManager = LinearLayoutManager(requireContext())
        serviceHistoryListAdapter.setData(requireContext(),serviceItem,this)
        binding.recyclerViewServiceHistory.adapter = serviceHistoryListAdapter
    }

    override fun onClick(id: Int) {
        PaymentInfoSubmitDialog(requireContext(),object:PaymentInfoSubmitDialog.OnClickListener{
            override fun onClickSubmit() {
                Toast.makeText(requireContext(),"Payment details submitted successfully",Toast.LENGTH_SHORT).show()
            }
            override fun onClickCancel() {}

        }).show()
    }
}