package com.fups.examplecaseandroid.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<BINDING : ViewDataBinding, T : ListAdapterItem>(
) : RecyclerView.Adapter<BaseViewHolder<BINDING>>() {


    @get:LayoutRes
    abstract val layoutId: Int

    abstract fun bind(binding: BINDING, item: T)

    private val differCallback = object : DiffUtil.ItemCallback<T?>() {
        override fun areItemsTheSame(
            oldItem: T,
            newItem: T
        ): Boolean {
            return oldItem.mId == newItem.mId
        }

        override fun areContentsTheSame(
            oldItem: T,
            newItem: T
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BINDING> {
        val binder = DataBindingUtil.inflate<BINDING>(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )

        return BaseViewHolder(binder)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BINDING>, position: Int) {
        differ.currentList[position]?.let { bind(holder.binder, it) }
    }

    override fun getItemCount(): Int = differ.currentList.size
}