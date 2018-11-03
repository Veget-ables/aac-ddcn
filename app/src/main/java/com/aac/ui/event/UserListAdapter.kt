package com.aac.ui.event

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aac.R
import com.aac.data.User
import com.aac.databinding.UserItemBinding

class UserListAdapter(private val lister: UserClickListener) : RecyclerView.Adapter<UserListAdapter.UserItemHolder>() {

    val items: MutableList<User> = mutableListOf()

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: UserItemHolder, position: Int) {
        holder.binding.user = items[position]
        holder.binding.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemHolder {
        val binding = DataBindingUtil.inflate<UserItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.user_item,
                parent,
                false,
                null
        )

        binding.root.setOnClickListener { view ->
            binding.user?.let { user ->
                lister.onItemClick(view, user)
            }
        }
        return UserItemHolder(binding)
    }

    interface UserClickListener{
        fun onItemClick(view: View, user: User)
    }

    class UserItemHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root)
}