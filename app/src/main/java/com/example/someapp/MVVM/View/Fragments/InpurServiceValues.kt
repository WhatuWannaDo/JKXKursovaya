package com.example.someapp.MVVM.View.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.someapp.MVVM.Model.SavedPayments
import com.example.someapp.MVVM.ViewModel.SavedPaymentsViewModel
import com.example.someapp.R
import kotlinx.android.synthetic.main.fragment_inpur_service_values.view.*
import java.text.SimpleDateFormat
import java.util.*


class InpurServiceValues : Fragment() {
    private val args by navArgs<InpurServiceValuesArgs>()
    private lateinit var viewModel : SavedPaymentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_inpur_service_values, container, false)

        viewModel = ViewModelProvider(this).get(SavedPaymentsViewModel::class.java)

        view.seviceInfo.text = "Информация о " + args.serviceArgs.name + ", " + args.serviceArgs.price +" руб, за " + args.serviceArgs.edIzm

        view.inputValues.doOnTextChanged { text, start, before, count ->
            if(!text.isNullOrEmpty()){
                val input = view.inputValues.text.toString().toDouble()
                val price = args.serviceArgs.price.toDouble()
                val result = String.format("%.3f", input*price)
                view.result.text = result + " руб."
            }
        }
        //ввод данных о платеже
        view.saveValues.setOnClickListener {
            if(view.inputValues.length() > 5){
                Toast.makeText(context, "Введите корректное значение", Toast.LENGTH_SHORT).show()
            }else{
                try {
                    val sdf = SimpleDateFormat("dd.M.yyyy")
                    val currentDate = sdf.format(Calendar.getInstance().time)

                    val payment = SavedPayments(0, args.serviceArgs.name, args.serviceArgs.price, view.result.text.toString(), args.serviceArgs.edIzm, currentDate)
                    viewModel.addPayment(payment)
                    Toast.makeText(context, "Информация сохранена", Toast.LENGTH_SHORT).show()
                    Navigation.findNavController(view).navigate(R.id.action_inpurServiceValues_to_mainMenu2)
                }catch (ex: Exception){
                    Toast.makeText(context, "Что то пошло не так", Toast.LENGTH_SHORT).show()
                }
            }
        }


        return view
    }

}