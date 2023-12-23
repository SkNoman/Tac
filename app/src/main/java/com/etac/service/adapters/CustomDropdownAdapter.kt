package com.etac.service.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

class CustomDropdownAdapter(context: Context, resource: Int, items: List<String>) :
    ArrayAdapter<String>(context, resource, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return super.getView(position, convertView, parent)
    }
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return super.getDropDownView(position, convertView, parent)
    }
}