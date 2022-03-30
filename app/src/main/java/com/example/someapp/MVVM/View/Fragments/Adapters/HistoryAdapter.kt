package com.example.someapp.MVVM.View.Fragments.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.someapp.MVVM.Model.SavedPayments
import com.example.someapp.R
import kotlinx.android.synthetic.main.services_row.view.*

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.MyViewHolder>() {
    private var savedPayments = emptyList<SavedPayments>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.services_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val list = savedPayments[position]

        holder.itemView.header.text = list.name
        holder.itemView.price.text = list.value
        holder.itemView.edinica.text = "по цене " + list.price + " руб."
        holder.itemView.rubbles.text = "за " + list.edinica
        holder.itemView.paymentDate.text = list.paymentDate
        holder.itemView.idItem.text = list.id.toString()

    }

    override fun getItemCount(): Int {
        return savedPayments.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setData(saved : List<SavedPayments>){
        this.savedPayments = saved
        notifyDataSetChanged()
    }
}