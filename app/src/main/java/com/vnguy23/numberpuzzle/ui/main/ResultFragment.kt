package com.vnguy23.numberpuzzle.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.NavHost
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.vnguy23.numberpuzzle.R

class ResultFragment : Fragment(R.layout.fragment_result) {
    private lateinit var newGame: Button
    private lateinit var stat: TextView
    private val args:ResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_result, container, false)
        stat = view.findViewById(R.id.GameStatus)
        if(args.status == "1"){
            stat.text = resources.getText(R.string.SURRENDER)
        }else{
            stat.text = resources.getText(R.string.WINNER)
        }
        newGame = view.findViewById(R.id.newGame)
        newGame.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_welcomeFragment)
        }
        return view
    }

}