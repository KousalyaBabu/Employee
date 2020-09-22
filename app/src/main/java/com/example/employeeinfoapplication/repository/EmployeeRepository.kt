package com.example.employeeinfoapplication.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.employeeinfoapplication.data.employee.Employee
import org.json.JSONArray
import org.json.JSONObject

object EmployeeRepository{
    private val employees= mutableListOf<Employee>()

    fun getEmployees(context: Context):MutableLiveData<List<Employee>>{
        val mutableLiveData=MutableLiveData<List<Employee>>()
        val url ="https://run.mocky.io/v3/325512ea-9fd0-4aeb-9cf5-cfda5c96f7a0"
        val requestQueue= Volley.newRequestQueue(context)
        val jsonObjReq= JsonObjectRequest(Request.Method.GET,url,null, Response.Listener { response ->
            val resultArray=response.getJSONArray("data")
            jsonToPojo(resultArray)
            mutableLiveData.value= employees
        }, Response.ErrorListener { println("EmployeeRepository.getEmployees Error occurred") })
        requestQueue.add(jsonObjReq)
        return mutableLiveData
    }
    private fun jsonToPojo(quizObject: JSONArray){
        for(i in 0 until quizObject.length()){
            val jsonObject=quizObject.get(i) as JSONObject
            val id=jsonObject.getString("id")
            val employeeName=jsonObject.getString("employee_name")
            val employeeSalary=jsonObject.getString("employee_salary")
            val employeeAge=jsonObject.getString("employee_age")
            val profileImage=jsonObject.getString("profile_image")
            employees.add(Employee(id, employeeName, employeeSalary, employeeAge, profileImage))
        }
    }

}