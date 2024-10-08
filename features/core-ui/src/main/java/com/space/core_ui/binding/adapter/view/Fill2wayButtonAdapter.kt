package com.space.core_ui.binding.adapter.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.BR
import com.space.core_ui.util.NonParamsItemHandler
import com.space.core_ui.databinding.View2wayButtonBinding



class Fill2wayButtonAdapter(
    private val adapter: RecyclerView.Adapter<*>,
    private val cancelHandler: NonParamsItemHandler,
    private val nextHandler: NonParamsItemHandler
) : RecyclerView.Adapter<Fill2wayButtonViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Fill2wayButtonViewHolder {
        return Fill2wayButtonViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: Fill2wayButtonViewHolder, position: Int) =
        holder.itemBind(adapter, cancelHandler, nextHandler)

    override fun getItemCount(): Int = 1
}

class Fill2wayButtonViewHolder(
    private val binding: View2wayButtonBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): Fill2wayButtonViewHolder {
            val binding =
                View2wayButtonBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return Fill2wayButtonViewHolder(binding)
        }
    }

    fun itemBind(
        adapter: RecyclerView.Adapter<*>,
        cancelHandler: NonParamsItemHandler,
        nextHandler: NonParamsItemHandler
    ) {
        binding.setVariable(BR.cancelButtonHandler, cancelHandler)
        binding.setVariable(BR.nextButtonHandler, nextHandler)
        binding.recyclerView.adapter = adapter
    }
}