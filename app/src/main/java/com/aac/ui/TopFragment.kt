package com.aac.ui

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.aac.R
import com.aac.databinding.TopFragmentBinding

class TopFragment : Fragment() {
    lateinit var binding: TopFragmentBinding
    private lateinit var viewModel: TopViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.top_fragment, container, false)
        binding.topFragment = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TopViewModel::class.java)
        // TODO: Use the ViewModel
    }

    fun onLoginClick(view: View) {
        Navigation.findNavController(view).navigate(R.id.toEventsListFragment)
    }

}
