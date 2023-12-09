package com.etac.service.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.etac.service.R
import com.etac.service.models.MenusItem


class DashboardSubMainMenuAdapter(
    context: Context,
    menuList: List<MenusItem>,
    private var onMenuItemClick: OnClickSubMenu
)
    :RecyclerView.Adapter<SubMenuItemViewHolder>(){

    private var mContext: Context = context
    private val content: List<MenusItem> = menuList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubMenuItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SubMenuItemViewHolder(inflater,parent)
    }


    override fun onBindViewHolder(holder: SubMenuItemViewHolder, position: Int) {
        val menuContent: MenusItem = content[position]
        holder.bind(mContext,menuContent)
        holder.itemView.setOnClickListener{
            menuContent.menuId?.let { it1 -> onMenuItemClick.onClickSubMen(it1) }
        }
    }

    override fun getItemCount(): Int {
        return content.size
    }
}
interface OnClickSubMenu{
    fun onClickSubMen(id:Int)
}
class SubMenuItemViewHolder(inflater: LayoutInflater,parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_sub_menu,parent,false)){

    private var title: TextView = itemView.findViewById(R.id.menuTitle)
    private var icon: ImageView = itemView.findViewById(R.id.menuIcon)
    //private var serviceCount: TextView = itemView.findViewById(R.id.txtServiceCount)
    //private var menuLayout : ConstraintLayout = itemView.findViewById(R.id.menuLayoutDB)

    fun bind(context: Context,menuData:MenusItem){
        title.text = menuData.menuTitle
        menuData.menuIcon?.let { icon.setImageResource(it) }
       // serviceCount.text = menuData.menuServiceCount.toString()
       // menuLayout.background = ContextCompat.getDrawable(context, R.drawable.rounded_bd_10dp)
        //menuData.menuBGColor?.let { menuLayout.setBackgroundColor(it) }
        // menuLayout.backgroundTintList = ColorStateList.valueOf(menuData.menuBGColor!!)
    }
}

