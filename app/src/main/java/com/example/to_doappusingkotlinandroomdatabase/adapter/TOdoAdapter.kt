package com.example.to_doappusingkotlinandroomdatabase.adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.to_doappusingkotlinandroomdatabase.UpdateCardActivity
import com.example.to_doappusingkotlinandroomdatabase.databinding.ViewLayoutBinding
import com.example.to_doappusingkotlinandroomdatabase.model.CardInfo

class TOdoAdapter( var list: List<CardInfo>) : RecyclerView.Adapter<TOdoAdapter.todoViewHolder>() {
    class todoViewHolder(var binding: ViewLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): todoViewHolder {
        return todoViewHolder(
            ViewLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: todoViewHolder, position: Int) {
        var currentItem = list[position]
        holder.binding.title.text = currentItem.title
        holder.binding.priority.text = currentItem.priority
        when (list[position].priority.toLowerCase()) {
            "high" -> holder.binding.mylayout.setBackgroundColor(Color.parseColor("#F05454"))
            "medium" -> holder.binding.mylayout.setBackgroundColor(Color.parseColor("#EDC988"))
            else -> holder.binding.mylayout.setBackgroundColor(Color.parseColor("#EDC956"))
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateCardActivity::class.java)
            intent.putExtra("id", position)
            holder.itemView.context.startActivity(intent)
        }

    }
}