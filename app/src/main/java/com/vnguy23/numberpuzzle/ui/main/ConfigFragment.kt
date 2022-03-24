package com.vnguy23.numberpuzzle.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vnguy23.numberpuzzle.R
import com.vnguy23.numberpuzzle.ui.main.model.Config
import com.vnguy23.numberpuzzle.ui.main.model.ConfigList

/**
 * A simple [Fragment] subclass.
 * Use the [ConfigFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConfigFragment : Fragment() {

    private lateinit var toGame:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_config, container, false)
        toGame = view.findViewById(R.id.configToGame)
        toGame.setOnClickListener {
            val action = ConfigFragmentDirections.actionConfigFragmentToGameFragment()
            findNavController().navigate(action)
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        populateConfig()
    }

    private fun populateConfig(){
        val Config1 = Config("easy")
        ConfigList.add(Config1)

        val Config2 = Config("medium")
        ConfigList.add(Config2)

        val Config3 = Config("hard")
        ConfigList.add(Config3)
    }
}