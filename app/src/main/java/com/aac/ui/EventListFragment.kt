package com.aac.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aac.R
import com.aac.databinding.EventListFragmentBinding
import com.aac.di.Injectable
import javax.inject.Inject

class EventListFragment : Fragment(), Injectable {
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: EventListViewModel by lazy { ViewModelProviders.of(activity!!, factory).get(EventListViewModel::class.java) }

    private lateinit var binding: EventListFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.event_list_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.generateEmails(20)
        viewModel.emails.observe(this,
                Observer {
                    it ?: return@Observer
                })
    }
}