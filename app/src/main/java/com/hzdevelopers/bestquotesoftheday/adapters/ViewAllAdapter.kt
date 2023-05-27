package com.hzdevelopers.bestquotesoftheday.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hzdevelopers.bestquotesoftheday.databinding.LayoutViewallBinding
import com.hzdevelopers.bestquotesoftheday.interfaces.ViewAllInterface
import com.hzdevelopers.bestquotesoftheday.models.ViewAllCategoriesModel

class ViewAllAdapter(
    var context: Context,
    var name: String,
    var dataList: ViewAllCategoriesModel?,
    var onClick : ViewAllInterface
) : RecyclerView.Adapter<ViewAllAdapter.ViewHolder>() {
    class ViewHolder(var binding: LayoutViewallBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutViewallBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        val sizeData = "${dataList}?.${name}"
        Log.d("sizeData",sizeData)
        when (name) {
            "Love" -> return dataList?.Love?.size ?: return 0
            "Sad" -> return dataList?.Sad?.size ?: return 0
            "Motivations" -> return dataList?.Motivations?.size ?: return 0
            "Birthday" -> return dataList?.Birthday?.size ?: return 0
            "Authors" -> return dataList?.Authors?.size ?: return 0
            "Friends" -> return dataList?.Friends?.size ?: return 0
        }
        return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (name) {
            "Love" -> {
                val data = dataList?.Love?.get(position)
                Glide.with(context).load(data?.imageUrl).into(holder.binding.imageView)
                holder.binding.cardView.setOnClickListener {
                    onClick.onClick(dataList?:return@setOnClickListener,name,position)
                }
            }
            "Sad" -> {
                val data = dataList?.Sad?.get(position)
                Glide.with(context).load(data?.imageUrl).into(holder.binding.imageView)
                holder.binding.cardView.setOnClickListener {
                    onClick.onClick(dataList?:return@setOnClickListener,name,position)
                }
            }
            "Motivations" -> {
                val data = dataList?.Motivations?.get(position)
                Glide.with(context).load(data?.imageUrl).into(holder.binding.imageView)
                holder.binding.cardView.setOnClickListener {
                    onClick.onClick(dataList?:return@setOnClickListener,name,position)
                }
            }
            "Birthday" -> {
                val data = dataList?.Birthday?.get(position)
                Glide.with(context).load(data?.imageUrl).into(holder.binding.imageView)
                holder.binding.cardView.setOnClickListener {
                    onClick.onClick(dataList?:return@setOnClickListener,name,position)
                }
            }
            "Authors" -> {
                val data = dataList?.Authors?.get(position)
                Glide.with(context).load(data?.imageUrl).into(holder.binding.imageView)
                holder.binding.cardView.setOnClickListener {
                    onClick.onClick(dataList?:return@setOnClickListener,name,position)
                }
            }
            "Friends" -> {
                val data = dataList?.Friends?.get(position)
                Glide.with(context).load(data?.imageUrl).into(holder.binding.imageView)
                holder.binding.cardView.setOnClickListener {
                    onClick.onClick(dataList?:return@setOnClickListener,name,position)
                }
            }
        }
    }
}