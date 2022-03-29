package com.example.someapp.MVVM.View.Fragments.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.someapp.MVVM.Model.ServicesModel
import com.example.someapp.MVVM.View.Fragments.MainMenuDirections
import com.example.someapp.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_main_menu.view.*
import kotlinx.android.synthetic.main.services_row.view.*
import java.text.SimpleDateFormat
import java.util.*

class MainMenuAdapter : RecyclerView.Adapter<MainMenuAdapter.MyViewHolder>() {

    private var serviceList = emptyList<ServicesModel>()

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.services_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val list = serviceList[position]
        holder.itemView.header.text = list.name
        holder.itemView.price.text = list.price
        holder.itemView.edinica.text = list.edIzm
        holder.itemView.rubbles.text = "руб."
        holder.itemView.idItem.text = list.id.toString()

        holder.itemView.row.setOnClickListener {
            val action = MainMenuDirections.actionMainMenu2ToInpurServiceValues(list)
            holder.itemView.findNavController().navigate(action)
        }


        val dateT = "10.3.2022"
        val sdf = SimpleDateFormat("dd.M.yyyy")
        val currentDate = Calendar.getInstance().timeInMillis.toString()
        val date : Date = sdf.parse(dateT)

        if(currentDate.toLong() > date.time){
            val calendar =  Calendar.getInstance()
            calendar.set(2022, Calendar.MONTH + 1, 10)

            val paymentDate = SimpleDateFormat("dd.M.yyyy").format((calendar.time) as Date)
            holder.itemView.nextPayemntDate.text =  "Следующий платёж до " + paymentDate.toString()
        }



    }

    override fun getItemCount(): Int {
        return serviceList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setData(service : List<ServicesModel>){
        this.serviceList = service
        notifyDataSetChanged()
    }
}