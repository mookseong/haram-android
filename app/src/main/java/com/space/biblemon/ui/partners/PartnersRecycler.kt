package com.space.biblemon.ui.partners

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.biblemon.databinding.ModelPartnersCardLayoutBinding
import com.space.shared.data.partner.Partner


class PartnersRecycler(

) : RecyclerView.Adapter<PartnersRecycler.PartnersViewHolder>() {
    private val models = mutableListOf<Partner>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(resultModel: MutableList<Partner>) {
        this.models.addAll(resultModel)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartnersViewHolder =
        PartnersViewHolder(
            ModelPartnersCardLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount() = models.size

    override fun onBindViewHolder(holder: PartnersViewHolder, position: Int) =
        holder.bindItem(models[position])

    inner class PartnersViewHolder(
        private val binding: ModelPartnersCardLayoutBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(model: Partner) {
//            binding.partners = model
        }
    }
}

