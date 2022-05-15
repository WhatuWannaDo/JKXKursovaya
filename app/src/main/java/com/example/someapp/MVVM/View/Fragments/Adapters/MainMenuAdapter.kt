package com.example.someapp.MVVM.View.Fragments.Adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.someapp.MVVM.Model.ServicesModel
import com.example.someapp.MVVM.View.Fragments.MainMenuDirections
import com.example.someapp.R
import kotlinx.android.synthetic.main.services_row.view.*
import java.text.SimpleDateFormat
import java.util.*

class MainMenuAdapter : RecyclerView.Adapter<MainMenuAdapter.MyViewHolder>() {

    private var serviceList = emptyList<ServicesModel>()

    private lateinit var overdueCallback : (item : String) -> Unit

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


        val calendar =  Calendar.getInstance()
        calendar.set(Calendar.YEAR+2021, Calendar.MONTH, 11)
        val paymentDate = SimpleDateFormat("dd.M.yyyy").format((calendar.time) as Date)

        val const = paymentDate
        val formatter = SimpleDateFormat("dd.M.yyyy")
        val _date_ : Date= formatter.parse(const)
        val formatDate = _date_.time.toString()
        var fDate = formatDate.toLong()



        val currentDate = Calendar.getInstance().timeInMillis.toString()

        if(currentDate.toLong() > fDate){
            overdueCallback(list.name)
            while(currentDate.toLong() > fDate) {
                fDate += 2629746000
            }
            val resultdate = Date(fDate).toString()
            val form : SimpleDateFormat = SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy")
            val parsedDate = form.parse(resultdate)
            val outputPrint : SimpleDateFormat = SimpleDateFormat("dd.M.yyyy")

            holder.itemView.nextPayemntDate.text =  "Следующий платёж до " + outputPrint.format(parsedDate)
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

    fun overdueListener(callback: (item: String) -> Unit){
        overdueCallback = callback
    }
}