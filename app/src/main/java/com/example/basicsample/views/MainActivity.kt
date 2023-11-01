package com.example.basicsample.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.basicsample.adapter.RvAdapter
import com.example.basicsample.databinding.ActivityMainBinding
import com.example.basicsample.viewmodels.MainActivityViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private val rvAdapter = RvAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = MainActivityViewModel()
        setUpRecyclerView()
        viewModel.getDataFromViewModel()
        viewModel.dataObserver.observe(this) {
            rvAdapter.saveData(it)
        }
        /*lifecycleScope.launch {
            viewModel.getDataUsingPagination().collectLatest {
                rvAdapter.saveData(it)
            }
        }*/

    }

    private fun setUpRecyclerView() {
        binding.rvStaggered.apply {
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = rvAdapter
        }
    }
}