package com.aac.ui

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
import androidx.navigation.Navigation
import com.aac.R
import com.aac.data.Event
import com.aac.databinding.EventListFragmentBinding
import com.aac.di.Injectable
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.Job
import javax.inject.Inject
import kotlin.coroutines.experimental.CoroutineContext

class EventListFragment : Fragment(), Injectable, EventListAdapter.EventClickListener, CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: EventListViewModel by lazy { ViewModelProviders.of(activity!!, factory).get(EventListViewModel::class.java) }

    private lateinit var binding: EventListFragmentBinding

    private val adapter: EventListAdapter = EventListAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.event_list_fragment, container, false)
        binding.fragment = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.eventRecyclerView.adapter = adapter
        binding.eventRecyclerView.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL,
                false
        )

        viewModel.users.observe(this, Observer { users ->
            users ?: return@Observer
            val list: List<Event> = users.map { user -> Event(user.email, user) }
            adapter.also {
                it.items.clear()
                it.items.addAll(list)
                it.notifyDataSetChanged()
            }
        })
    }

    override fun onItemClick(view: View, event: Event) {
        Navigation.findNavController(view).navigate(R.id.toEventFragment)
    }

    fun onSaveClick(view: View) {
        viewModel.refreshUsers(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}