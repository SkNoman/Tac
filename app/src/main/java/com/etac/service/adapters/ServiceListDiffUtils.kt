package com.etac.service.adapters

import androidx.recyclerview.widget.DiffUtil
import com.etac.service.models.service.ServiceHistoryList

class ServiceListDiffUtils(
    private val oldItemList:List<ServiceHistoryList>,
    private val newItemList: List<ServiceHistoryList>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldItemList.size
    }

    override fun getNewListSize(): Int {
        return newItemList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItemList[oldItemPosition].id== newItemList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldItemList[oldItemPosition] != newItemList[newItemPosition] -> {
                false
            }
            else -> true
        }
    }
}