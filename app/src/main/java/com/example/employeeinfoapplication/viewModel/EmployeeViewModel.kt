package com.example.employeeinfoapplication.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.employeeinfoapplication.data.employee.Employee
import com.example.employeeinfoapplication.repository.EmployeeRepository

class EmployeeViewModel:ViewModel() {
    private var mutableLiveData:MutableLiveData<List<Employee>>?=null

    fun init(context: Context):LiveData<List<Employee>>{
        mutableLiveData=mutableLiveData?: EmployeeRepository.getEmployees(context)
        return mutableLiveData!!
    }

    fun sortAtoZ(){
        mutableLiveData?.value=mutableLiveData?.value?.sortedBy { it.employeeName}
    }

    fun sortZtoA(){
        mutableLiveData?.value=mutableLiveData?.value?.sortedByDescending { it.employeeName}
    }
}