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
import com.aac.api.SuggestionUser
import com.aac.databinding.EventFragmentBinding
import com.aac.di.Injectable
import javax.inject.Inject

class EventFragment : Fragment(), Injectable, UserListAdapter.UserClickListener {

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

        val args = EventFragmentArgs.fromBundle(arguments)
        binding.eventTitle.text = args.title

        binding.userRecyclerView.adapter = adapter
        binding.userRecyclerView.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL,
                false
        )

        viewModel.suggestionUsers.observe(this, Observer { users ->
            adapter.also {
                users ?: return@Observer
                it.items.clear()
                it.items.addAll(users)
                it.notifyDataSetChanged()
            }
        })

        viewModel.joinedUsers.observe(this, Observer { users ->
        })

        viewModel.eventId.value = (Math.random() * 10).toLong()
    }

    override fun onItemClick(view: View, user: SuggestionUser) {
    }
}
