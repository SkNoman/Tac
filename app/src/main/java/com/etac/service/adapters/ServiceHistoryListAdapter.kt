package com.etac.service.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.etac.service.R
import com.etac.service.models.ServiceHistoryList

class ServiceHistoryListAdapter(
    context: Context,
    menuList: List<ServiceHistoryList>,
    private var onMenuItemClick: OnClickService
)
    : RecyclerView.Adapter<ServiceItemViewHolder>(){

    private var mContext: Context = context
    private val content: List<ServiceHistoryList> = menuList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ServiceItemViewHolder(inflater,parent)
    }



    override fun onBindViewHolder(holder: ServiceItemViewHolder, position: Int) {
        val menuContent: ServiceHistoryList = content[position]
        holder.bindServiceHistory(mContext,menuContent)
        holder.itemView.setOnClickListener{
            menuContent.serviceType?.let { it1 -> onMenuItemClick.onClick(it1) }
        }
    }

    override fun getItemCount(): Int {
        return content.size
    }
}
interface OnClickService{
    fun onClick(id:Int)
}
class ServiceItemViewHolder(inflater: LayoutInflater,parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_service_history,parent,false)){

    private var title: TextView = itemView.findViewById(R.id.txtServiceType)
    private var details: TextView = itemView.findViewById(R.id.txtServiceDetails)
    private var icon: ImageView = itemView.findViewById(R.id.ivServiceHistoryIcon)
    private var serviceDate: TextView = itemView.findViewById(R.id.txtServiceDate)

    fun bindServiceHistory(context: Context,menuData:ServiceHistoryList){
        title.text = menuData.serviceTitle
        details.text = menuData.serviceDetails
        serviceDate.text = menuData.serviceDate
        //menuData.menuIcon?.let { icon.setImageResource(it) }
        if (menuData.serviceType == 1){
            icon.setImageResource(R.drawable.car_service_banner_temp)
        }else{
            icon.setImageResource(R.drawable.laundry_service_online)
        }
    }
}

