package com.example.someapp.MVVM.View.Fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.someapp.MVVM.Model.ServicesModel
import com.example.someapp.MVVM.View.Fragments.Adapters.MainMenuAdapter
import com.example.someapp.MVVM.ViewModel.ServicesViewModel
import com.example.someapp.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottom_sheet_change_price.view.*
import kotlinx.android.synthetic.main.fragment_main_menu.view.*
import kotlinx.android.synthetic.main.services_row.view.*


class MainMenu : Fragment() {

    private val adapter = MainMenuAdapter()
    private lateinit var viewModel : ServicesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main_menu, container, false)

        viewModel = ViewModelProvider(this).get(ServicesViewModel::class.java)
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
        val bottomSheetView = LayoutInflater.from(requireContext()).inflate(R.layout.bottom_sheet_change_price, container, false)

        val recyclerView = view.recycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.readAllServices.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                bottomSheetView.priceChange.hint = viewHolder.itemView.price.text.toString()
                bottomSheetView.saveNewPrice.setOnClickListener {
                    if(bottomSheetView.priceChange.text!!.isEmpty()){
                        Toast.makeText(context, "Заполните все поля", Toast.LENGTH_SHORT).show()
                    }else{
                        val service = ServicesModel(
                            Integer.parseInt(viewHolder.itemView.idItem.text.toString()),
                            viewHolder.itemView.header.text.toString(),
                            bottomSheetView.priceChange.text.toString(),
                            viewHolder.itemView.edinica.text.toString())
                        viewModel.updateService(service)
                        Toast.makeText(context, "Информация обновлена", Toast.LENGTH_SHORT).show()
                        adapter.notifyDataSetChanged()
                        bottomSheetDialog.dismiss()
                    }
                }

                bottomSheetDialog.setContentView(bottomSheetView)
                bottomSheetDialog.show()
            }
        }).attachToRecyclerView(recyclerView)


        return view
    }


}