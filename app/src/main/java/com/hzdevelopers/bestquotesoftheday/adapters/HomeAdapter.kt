package com.hzdevelopers.bestquotesoftheday.adapters

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hzdevelopers.bestquotesoftheday.databinding.HomeRecyclerviewLayoutBinding
import com.hzdevelopers.bestquotesoftheday.interfaces.HomeInterface
import com.hzdevelopers.bestquotesoftheday.models.QuotesCategory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URI
import java.net.URL

class HomeAdapter(var context : Context,var dataList : List<QuotesCategory>,var onClick : HomeInterface) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    class ViewHolder(var binding: HomeRecyclerviewLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = HomeRecyclerviewLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        /*CoroutineScope(Dispatchers.IO).launch {
            val uri = URL("https://drive.google.com/file/d/1rh2ddqilvxuEH2jZ-lngCuwteHj89I0h/view?usp=drivesdk")
            val bitmap = BitmapFactory.decodeStream(uri.openStream())
            withContext(Dispatchers.Main){
                holder.binding.imageViewCategories.setImageBitmap(bitmap)
            }
        }*/
        holder.binding.categoryName.text = data.name
        holder.binding.totalChannels.text = data.totalQuotes
        Glide.with(context).load(data.avatar).dontAnimate().into(holder.binding.imageViewCategories)
        //Picasso.get().load(data.avatar).into(holder.binding.imageViewCategories)


        holder.binding.cardView.setOnClickListener {
            onClick.onClick(data)
        }
    }
}