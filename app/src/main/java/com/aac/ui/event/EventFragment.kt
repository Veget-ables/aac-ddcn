package com.aac.ui.event

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aac.R
import com.aac.data.User
import com.aac.databinding.EventFragmentBinding
import com.aac.di.Injectable
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.Job
import javax.inject.Inject
import kotlin.coroutines.experimental.CoroutineContext

class EventFragment : Fragment(), Injectable, CoroutineScope, UserListAdapter.UserClickListener {
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: EventViewModel by lazy { ViewModelProviders.of(activity!!, factory).get(EventViewModel::class.java) }

    private lateinit var binding: EventFragmentBinding
    private val adapter: UserListAdapter = UserListAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.event_fragment, container, false)
        binding.fragment = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.userRecyclerView.adapter = adapter
        binding.userRecyclerView.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL,
                false
        )

        viewModel.users.observe(this, Observer{users->
            adapter.also{
                users?: return@Observer
                it.items.clear()
                it.items.addAll(users)
                it.notifyDataSetChanged()
            }
        })

        viewModel.eventId.value = (Math.random()*10).toInt()
    }

    override fun onItemClick(view: View, user: User) {
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}
