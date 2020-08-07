package com.example.employeeinfoapplication

import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.employeeinfoapplication.ui.EmployeeAdapter
import com.example.employeeinfoapplication.ui.EmployeeLayout
import com.example.employeeinfoapplication.ui.EmployeeViewModel

class EmployeeActivity:AppCompatActivity() {
    private var employeeViewModel: EmployeeViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val linearLayout=LinearLayout(this)
        linearLayout.orientation=LinearLayout.VERTICAL
        setContentView(linearLayout)

        val searchLayout= LinearLayout(this)
        linearLayout.addView(searchLayout)
        val searchEditText= EditText(this)
        searchLayout.addView(searchEditText)
        searchEditText.layoutParams=LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,0.5F)

        val searchButton= Button(this)
        searchButton.text="Search"
        searchLayout.addView(searchButton)

        val mainLayout=LinearLayout(this).apply {
            orientation=LinearLayout.VERTICAL
            gravity=Gravity.END
            visibility=View.GONE
        }
        linearLayout.addView(mainLayout)

        val closeImageView=ImageView(this).apply {
            setImageResource(R.drawable.close)
            layoutParams=LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        }
        mainLayout.addView(closeImageView)

        val employeeLayout=
            EmployeeLayout(this)
        mainLayout.addView(employeeLayout)

        val recyclerView = RecyclerView(this)
        linearLayout.addView(recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        searchButton.setOnClickListener {
            if(searchEditText.text.toString().isNotEmpty()) {
                val employee=employeeViewModel?.searchByName(searchEditText.text.toString())
                employeeLayout.setEmployee(employee!!)
                recyclerView.visibility=View.GONE
                mainLayout.visibility=View.VISIBLE
            }else{
                mainLayout.visibility=View.GONE
                recyclerView.visibility=View.VISIBLE
            }
        }

        closeImageView.setOnClickListener {
            mainLayout.visibility=View.GONE
            recyclerView.visibility=View.VISIBLE
        }

        employeeViewModel=ViewModelProviders.of(this)[EmployeeViewModel::class.java]
        employeeViewModel?.init(this)?.observe(this,(Observer {
            recyclerView.adapter =
                EmployeeAdapter(it)
            recyclerView.adapter?.notifyDataSetChanged()
        }))
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