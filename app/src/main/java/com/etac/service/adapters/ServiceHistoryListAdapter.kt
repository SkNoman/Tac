package com.etac.service.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.etac.service.R
import com.etac.service.databinding.ItemServiceHistoryBinding
import com.etac.service.models.service.ServiceHistoryList
import javax.inject.Inject

class ServiceHistoryListAdapter @Inject constructor():
    RecyclerView.Adapter<ServiceHistoryListAdapter.ServiceItemViewHolder>(){

    private var oldItemList = emptyList<ServiceHistoryList>()
    private var context: Context? = null
    private lateinit var onServiceListItemClickListener: OnClickService
    class ServiceItemViewHolder(val binding: ItemServiceHistoryBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(
        parent: ViewGroup ,
        viewType: Int
    ): ServiceItemViewHolder {
        val binding = ItemServiceHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ServiceItemViewHolder(binding)
    }



    override fun onBindViewHolder(holder: ServiceItemViewHolder, position: Int) {
       addData(holder,position)
    }

    private fun addData(holder: ServiceItemViewHolder , position: Int) {
        holder.binding.apply {
            var paymentVerified = false
            if (!oldItemList[position].payment_method.isNullOrEmpty()){
                paymentVerified = true
                tvPaymentDetails.text = "Payment Submitted ☑"
                tvPaymentDetails.setTextColor(getColorStateList(context!!,R.color.green))
                tvPaymentDetails.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
            }
            txtServiceDate.text = oldItemList[position].created_at
            txtServiceTitle.text = oldItemList[position].service_Name
            if (oldItemList[position].service_type == 1){
                ivServiceHistoryIcon.setImageResource(R.drawable.car_service_banner_temp)
            }else{
                ivServiceHistoryIcon.setImageResource(R.drawable.laundry_service_temp2)
            }
            txtServiceDetails.text = oldItemList[position].service_details
            when(oldItemList[position].service_status){
                0->{
                    tvServiceStatus.text = "In-Queue"
                    tvServiceStatus.backgroundTintList = getColorStateList(context!!,R.color.bg_tint_light_blue_sky);
                }
                1->{
                    tvServiceStatus.text = "Accepted ☑"
                    tvServiceStatus.backgroundTintList = getColorStateList(context!!,R.color.green);
                }
                2->{
                    tvServiceStatus.text = "Rejected"
                    tvServiceStatus.backgroundTintList = getColorStateList(context!!,R.color.red);
                }
            }
            holder.itemView.setOnClickListener{
                if (!paymentVerified){
                    oldItemList[position].id?.let { it1 -> onServiceListItemClickListener.onClick(it1) }
                }

            }

        }
    }

    override fun getItemCount(): Int {
        return oldItemList.size
    }

    fun setData(context: Context, serviceList: List<ServiceHistoryList>, onItemClick: OnClickService)
    {
        this.context = context
        oldItemList = serviceList
        this.onServiceListItemClickListener = onItemClick
        val diffUtil = ServiceListDiffUtils(oldItemList , serviceList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        diffResult.dispatchUpdatesTo(this)
    }
    interface OnClickService{
        fun onClick(id:Int)
    }
}


