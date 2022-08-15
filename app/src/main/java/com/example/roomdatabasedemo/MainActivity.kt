package com.example.roomdatabasedemo

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.roomdatabasedemo.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val employeeDao = (application as EmployeeApp).db.employeeDao()

        binding?.btnAddRecord?.setOnClickListener{
            addRecord(employeeDao = employeeDao)
        }

        lifecycleScope.launch{
            employeeDao.fetchAllEmployees().collect{
                val list = ArrayList(it)
                setUpRecycleView(list, employeeDao)
            }
        }
    }

    fun addRecord(employeeDao : EmployeeDao){
        val name = binding?.etName?.text.toString()
        val email = binding?.etEmail?.text.toString()

        if (name.isNotEmpty() && email.isNotEmpty()){
            lifecycleScope.launch{
                employeeDao.insert(EmployeeEntity(name = name, email = email    ))
                Toast.makeText(applicationContext, "Record updated", Toast.LENGTH_SHORT).show()
                binding?.etName?.text?.clear()
                binding?.etEmail?.text?.clear()
            }
        }else{
            Toast.makeText(applicationContext, "Name and E-mail cannot be empty", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpRecycleView(employeeList:ArrayList<EmployeeEntity>, employeeDao: EmployeeDao){

        if (employeeList.isNotEmpty()){
            val itemAdapter = ItemAdapter(employeeList)

            recyclerView.adapter = itemAdapter

            recyclerView.visibility = View.VISIBLE
            tvNoRecords.visibility = View.GONE
        }else{
            recyclerView.visibility = View.GONE
            tvNoRecords.visibility = View.VISIBLE
        }

    }
}