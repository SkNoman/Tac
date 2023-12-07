package com.etac.service.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.etac.service.R
import com.etac.service.models.MenusItem


class DashboardMainMenuAdapter(
    context: Context,
    menuList: List<MenusItem>,
    private var onMenuItemClick: OnClickMenu
)
    :RecyclerView.Adapter<MenuItemViewHolder>(){

    private var mContext: Context = context
    private val content: List<MenusItem> = menuList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MenuItemViewHolder(inflater,parent)
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        val menuContent: MenusItem = content[position]
        holder.bind(mContext,menuContent)
        holder.itemView.setOnClickListener{
            menuContent.menuId?.let { it1 -> onMenuItemClick.onClick(it1) }
        }
    }

    override fun getItemCount(): Int {
        return content.size
    }
}
interface OnClickMenu{
    fun onClick(id:Int)
}
class MenuItemViewHolder(inflater: LayoutInflater,parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_db_main_menu,parent,false)){

    private var title: TextView = itemView.findViewById(R.id.txtMenuTitle)
    private var icon: ImageView = itemView.findViewById(R.id.ivMenuIcon)
    private var serviceCount: TextView = itemView.findViewById(R.id.txtServiceCount)

    fun bind(context: Context,menuData:MenusItem){
        title.text = menuData.menuTitle
        menuData.menuIcon?.let { icon.setImageResource(it) }
        serviceCount.text = menuData.menuServiceCount.toString()
    }
}

