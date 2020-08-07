package com.example.employeeinfoapplication.ui

import android.content.Context
import android.graphics.Typeface
import android.widget.LinearLayout
import android.widget.TextView
import com.example.employeeinfoapplication.data.employee.Employee

class EmployeeLayout(context: Context):LinearLayout(context) {
    private val idTextView=getTextView()
    private val nameTextView=getTextView()
    private val salaryTextView=getTextView()
    private val ageTextView=getTextView()

    init {
        orientation= VERTICAL
        addView(idTextView.also {
            it.typeface= Typeface.DEFAULT_BOLD
            it.textSize=20F
        })
        addView(nameTextView)
        addView(salaryTextView)
        addView(ageTextView)
    }
    private fun getTextView()=TextView(context).apply {
        textSize=16F
        layoutParams= LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT).also {
            it.setMargins(10,10,10,10)
        }
    }
    fun setEmployee(employee: Employee){
        idTextView.text=employee.id
        nameTextView.text=employee.employeeName
        salaryTextView.text=employee.employeeSalary
        ageTextView.text=employee.employeeAge
    }
}