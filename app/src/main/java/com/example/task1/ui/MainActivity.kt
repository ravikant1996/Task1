package com.example.task1.ui

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task1.R
import com.example.task1.UserApplication
import com.example.task1.adapter.UserAdapter
import com.example.task1.databinding.ActivityMainBinding
import com.example.task1.model.UserItem
import com.example.task1.sealedclass.Response
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: ViewModel
    lateinit var binding: ActivityMainBinding
    lateinit var userAdapter: UserAdapter
    private lateinit var searchView: SearchView
    var userList: ArrayList<UserItem>? = null
    var filteredDataList: List<UserItem?>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = (application as UserApplication).dataRepository
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(this, repository)
        )[ViewModel::class.java]

        viewModel.users.observe(this, Observer { it ->
            when (it) {
                is Response.Loading -> {
                    Toast.makeText(this@MainActivity, "Loading", Toast.LENGTH_SHORT)
                        .show()
                }
                is Response.Success -> {
                    val userItem = it.data as MutableList<UserItem>
                    binding.noData.isVisible = userItem.size == 0

                    userList = ArrayList()
                    userList!!.addAll(userItem)
                    userAdapter = UserAdapter(userItem)
                    binding.recyclerView.apply {
                        setHasFixedSize(true)
                        adapter = userAdapter
                        layoutManager =
                            LinearLayoutManager(
                                this@MainActivity,
                                LinearLayoutManager.VERTICAL,
                                false
                            )
                    }
                }
                is Response.Failure -> {
                    binding.noData.isVisible = true
                    Toast.makeText(this@MainActivity, it.errorMessage, Toast.LENGTH_SHORT)
                        .show()
                }
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.action_search)
        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    filteredDataList = filter(userList!!, newText);
                    userAdapter.setFilter(filteredDataList as List<UserItem?>)
                    return false
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }
            })
            searchView.setOnCloseListener {
                true
            }
        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun filter(userList: List<UserItem>, queryText: String): List<UserItem> {
        var newText = queryText
        newText = newText.lowercase(Locale.getDefault())
        var text: String
        val filteredDataList = ArrayList<UserItem>()
        for (dataFromDataList in userList) {
            text = dataFromDataList.name.lowercase(Locale.getDefault())
            if (text.contains(newText)) {
                filteredDataList.add(dataFromDataList)
            }
        }
        return filteredDataList
    }

    override fun onBackPressed() {
        if (!searchView.isIconified) {
            searchView.onActionViewCollapsed();
        } else {
            super.onBackPressed();
        }
    }
}