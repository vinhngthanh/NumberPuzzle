package com.vnguy23.numberpuzzle.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.vnguy23.numberpuzzle.R
import com.vnguy23.numberpuzzle.ui.main.model.MainViewModel

class WelcomeFragment : Fragment() {

    private lateinit var toSettings: Button
    private lateinit var toConfig: Button

    companion object {
        fun newInstance() = WelcomeFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)
        toSettings = view.findViewById(R.id.welcomeToSettings)
        toSettings.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_settingsFragment)
        }
        toConfig = view.findViewById(R.id.welcomeToConfig)
        toConfig.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_configFragment)
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}