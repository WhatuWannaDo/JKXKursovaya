package com.example.someapp.MVVM.View.FirstTime

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.someapp.MVVM.Model.ServicesModel
import com.example.someapp.MVVM.ViewModel.ServicesViewModel
import com.example.someapp.R
import kotlinx.android.synthetic.main.fragment_choose_social.view.*


class ChooseSocial : Fragment() {
    private lateinit var viewModel : ServicesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_choose_social, container, false)
        val finishedPrefs : SharedPreferences = requireContext().getSharedPreferences("welcome", MODE_PRIVATE)


        //подготовка всех коммунальных услуг
        view.socialGroupNextButton.setOnClickListener {

            if((!view.physical.isChecked) && (!view.uridical.isChecked)){
                Toast.makeText(context, "Вы не выбрали!", Toast.LENGTH_SHORT).show()
            }else{
                if(view.uridical.isChecked){
                    Toast.makeText(context,"Не забудьте о договоре коммунальных услуг",Toast.LENGTH_SHORT).show()
                }
                val editorFinish = finishedPrefs.edit()
                editorFinish.apply(){
                    putBoolean("firstTime", true)
                }.apply()

                viewModel = ViewModelProvider(this).get(ServicesViewModel::class.java)

                val service1 = ServicesModel(1,"Горячая вода", "211.67", "куб.м.")
                val service2 = ServicesModel(2,"Холодная вода", "43.57","куб.м.")
                val service3 = ServicesModel(3,"Тепловая энергия", "2546.83", "Гкал")
                val service4 = ServicesModel(4,"Водоотведение", "32.02", "куб.м.")
                val service5 = ServicesModel(5,"Электроснабжение", "5.15", "кВт")
                val service6 = ServicesModel(6,"Газоснабжение", "7.25", "куб.м.")
                val service7 = ServicesModel(7,"Отопление", "2546.83", "Гкал")
                val service8 = ServicesModel(8,"Твердое топливо", "1493.62", "тон.")
                val service9 = ServicesModel(9,"Содержание и ремонт", "28.48", "кв.м")
                val service10 = ServicesModel(10,"Вывоз мусора", "7.27", "кв.м.")

                viewModel.addService(service1)
                viewModel.addService(service2)
                viewModel.addService(service3)
                viewModel.addService(service4)
                viewModel.addService(service5)
                viewModel.addService(service6)
                viewModel.addService(service7)
                viewModel.addService(service8)
                viewModel.addService(service9)
                viewModel.addService(service10)


                Navigation.findNavController(view).navigate(R.id.action_chooseSocial_to_mainMenu2)
                requireActivity().recreate()
            }
        }



        return view
    }


}