package com.example.someapp.MVVM.View.FirstTime

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.someapp.MVVM.Model.ServicesModel
import com.example.someapp.MVVM.ViewModel.ServicesViewModel
import com.example.someapp.R
import kotlinx.android.synthetic.main.fragment_welcome.view.*


class welcomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)


        view.welcomeNextButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_chooseSocial)
        }

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sharedPrefs : SharedPreferences = requireContext().getSharedPreferences("welcome", MODE_PRIVATE)
        if (sharedPrefs.getBoolean("firstTime", false) == true){
            Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_mainMenu2)
        }
    }



}