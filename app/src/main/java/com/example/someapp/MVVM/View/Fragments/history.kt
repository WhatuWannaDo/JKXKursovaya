package com.example.someapp.MVVM.View.Fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.view.View.GONE
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.someapp.MVVM.Model.SavedPayments
import com.example.someapp.MVVM.View.Fragments.Adapters.HistoryAdapter
import com.example.someapp.MVVM.ViewModel.SavedPaymentsViewModel
import com.example.someapp.R
import kotlinx.android.synthetic.main.fragment_history.view.*
import kotlinx.android.synthetic.main.services_row.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class history : Fragment() {

    private val adapter = HistoryAdapter()
    private lateinit var viewModel : SavedPaymentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        setHasOptionsMenu(true)

        viewModel = ViewModelProvider(this).get(SavedPaymentsViewModel::class.java)

        val recyclerView = view.historyRecycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getAllSavedPayments.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
            GlobalScope.launch(Dispatchers.IO) {
                checkData(adapter,view)
            }
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
                val builder = AlertDialog.Builder(context)
                val payment = SavedPayments(Integer.parseInt(viewHolder.itemView.idItem.text.toString()), "", "", "", "", "")
                builder.setPositiveButton("Да") { _, _ ->
                    viewModel.deleteSinglePayment(payment)
                    Toast.makeText(context, "Платёж удален", Toast.LENGTH_SHORT)
                        .show()
                    adapter.notifyDataSetChanged()

                }
                builder.setNegativeButton("Нет") { _, _ ->
                    adapter.notifyDataSetChanged()
                }
                builder.setTitle("Удалить платёж?")
                builder.setMessage("Вы уверены что хотите это сделать?")
                builder.create().show()
            }
        }).attachToRecyclerView(recyclerView)

        return view
    }

    suspend fun checkData(adapter: HistoryAdapter, view: View){
        GlobalScope.launch(Dispatchers.IO) {
            if(adapter.itemCount > 0){
                view.nothingHere.visibility = GONE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_menu, menu)
        val deleteAll = menu.findItem(R.id.deleteAll)

        deleteAll.setOnMenuItemClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setPositiveButton("Да") { _, _ ->
                viewModel.deleteAllPayments()
                Toast.makeText(context, "Все платежи удалены", Toast.LENGTH_SHORT)
                    .show()
            }
            builder.setNegativeButton("Нет") { _, _ -> }
            builder.setTitle("Удалить все платежи?")
            builder.setMessage("Вы уверены что хотите это сделать?")
            builder.create().show()

            true
        }
    }

}