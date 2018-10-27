package com.aac.ui

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aac.R
import com.aac.data.Event
import com.aac.databinding.EventItemBinding

class EventListAdapter(private val listener: EventClickListener)
    : RecyclerView.Adapter<EventListAdapter.EventItemHolder>() {

    val items: MutableList<Event> = mutableListOf()

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: EventItemHolder, position: Int) {
        holder.binding.event = items[position]
        holder.binding.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventItemHolder {
        val binding = DataBindingUtil.inflate<EventItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.event_item,
                parent,
                false,
                null)

        binding.root.setOnClickListener { view ->
            binding.event?.let { event ->
                listener.onItemClick(view, event)
            }
        }
        return EventItemHolder(binding)
    }

    interface EventClickListener {
        fun onItemClick(view: View, event: Event)
    }

    class EventItemHolder(var binding: EventItemBinding) : RecyclerView.ViewHolder(binding.root)
}