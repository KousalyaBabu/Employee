package com.example.employeeinfoapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.employeeinfoapplication.ui.EmployeeAdapter
import com.example.employeeinfoapplication.ui.EmployeeViewModel

class EmployeeActivity:AppCompatActivity(),TextWatcher {
    private var employeeViewModel: EmployeeViewModel?=null
    private var recyclerView:RecyclerView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val linearLayout=LinearLayout(this)
        linearLayout.orientation=LinearLayout.VERTICAL
        setContentView(linearLayout)

        val searchLayout= LinearLayout(this)
        linearLayout.addView(searchLayout)
        val searchEditText= EditText(this)
        searchLayout.addView(searchEditText)
        searchEditText.hint="Search"
        searchEditText.addTextChangedListener(this)
        searchEditText.layoutParams=LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,0.5F)

        recyclerView = RecyclerView(this)
        linearLayout.addView(recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(this)

        employeeViewModel=ViewModelProviders.of(this)[EmployeeViewModel::class.java]
        employeeViewModel?.init(this)?.observe(this,(Observer {
            recyclerView?.adapter = EmployeeAdapter(it)
            recyclerView?.adapter?.notifyDataSetChanged()
        }))
    }

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val employees=employeeViewModel?.init(this)?.value
        val list=employees?.filter { it.employeeName.contains(s!!,true) }
        recyclerView?.adapter=EmployeeAdapter(list!!)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add("Sort A to Z")
        menu?.add("Sort Z to A")
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.title){
            "Sort A to Z"->employeeViewModel?.sortAtoZ()
            "Sort Z to A"->employeeViewModel?.sortZtoA()
        }
        return super.onOptionsItemSelected(item)
    }
}