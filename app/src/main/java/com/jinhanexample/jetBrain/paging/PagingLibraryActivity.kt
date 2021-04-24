package com.jinhanexample.jetBrain.paging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.jinhanexample.R
import com.jinhanexample.databinding.ActivityPagingLibraryBinding
import kotlinx.coroutines.launch

class PagingLibraryActivity : AppCompatActivity() {

    private val viewModel by viewModels<PagingViewModel>()

    private val adapter = PagingAdapter()

    lateinit var b: ActivityPagingLibraryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_paging_library)

        b.apply {
            button.setOnClickListener {
                adapter.refresh()
            }

            recyclerView.layoutManager = LinearLayoutManager(this@PagingLibraryActivity)
            recyclerView.adapter =
                adapter.withLoadStateHeaderAndFooter(JinhanLoadStateAdapter { adapter.retry() },
                    JinhanLoadStateAdapter { adapter.retry() })

            adapter.addLoadStateListener {
                println("pre ${it.prepend}")
                println("ap ${it.append}")
                println("re ${it.refresh}")
                if (it.refresh is LoadState.Error) {
                    adapter.retry()
                }
            }

            viewModel.data.observe(this@PagingLibraryActivity, Observer {
                lifecycleScope.launch {
                    adapter.submitData(it)
                }
            })

            viewModel.init()
        }
    }
}