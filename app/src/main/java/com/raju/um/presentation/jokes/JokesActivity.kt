package com.raju.um.presentation.jokes

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.raju.um.bean.order.JokesBean
import com.raju.um.databinding.ActivityJokesBinding
import com.raju.um.presentation.base.BaseActivity
import com.raju.um.presentation.jokes.adapter.JokesListAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class JokesActivity : BaseActivity() {
    private lateinit var binding: ActivityJokesBinding
    private val viewModel: JokesViewModel by viewModels()
    private var jokesListAdapter: JokesListAdapter? = null
    private val jokes = mutableListOf<JokesBean>()
    private val executorService = Executors.newSingleThreadScheduledExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViews()
        callAPI()
        initObservers()
    }

    private fun initObservers() {
        viewModel.jokesLD.observe(this) {
            binding.progressBar.visibility = View.GONE
            if (jokes.size >= 10) {
                jokes.removeAt(0)
            }
            jokes.add(it)
            setupAdapter(jokes)
        }
    }

    private fun setViews() {
        binding.progressBar.visibility = View.VISIBLE
        binding.rvJokes.setHasFixedSize(true)
        binding.rvJokes.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvJokes.isNestedScrollingEnabled = true
    }

    private fun callAPI() {
        executorService.scheduleAtFixedRate({
            runOnUiThread {
                viewModel.init()
            }
        }, 0, 1, TimeUnit.MINUTES)

    }

    private fun setupAdapter(it: List<JokesBean>?) {
        it?.run {
            if (jokesListAdapter == null) {
                jokesListAdapter = JokesListAdapter(this)
                binding.rvJokes.adapter = jokesListAdapter
            } else {
                jokesListAdapter?.notifyDataSetChanged()
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        executorService.shutdown()
    }
}