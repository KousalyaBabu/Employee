package com.example.employeeinfoapplication.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.employeeinfoapplication.data.employee.Employee

class EmployeeAdapter(private val list:List<Employee>) : RecyclerView.Adapter<EmployeeAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(EmployeeLayout(parent.context))
    }

    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.view.setEmployee(list[position])
    }

    class MyViewHolder(val view: EmployeeLayout):RecyclerView.ViewHolder(view)
}
