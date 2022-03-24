package com.vnguy23.numberpuzzle.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.vnguy23.numberpuzzle.R
import com.vnguy23.numberpuzzle.ui.main.model.MainViewModel

class GameFragment : Fragment() {

    private lateinit var toResult:Button
    private lateinit var buttons:Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game, container, false)
        val sharedViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        toResult = view.findViewById(R.id.gameToResult)
        toResult.setOnClickListener {
            findNavController().navigate(R.id.action_gameFragment_to_resultFragment)
        }

        buttons = Array(4){r->
            Array(4){c->
                initButton(r,c, view,sharedViewModel)
            }
        }
        return view
    }

    private fun initButton(r: Int, c: Int, view: View, sharedViewModel: MainViewModel): Button{
        val btn: Button = view.findViewById(resources.getIdentifier("Button$r$c", "id",
            context?.packageName
        ))
        if(r==3&&c==3) {
        } else{
            if (sharedViewModel.getText() == 1) {
                btn.setTextColor(ContextCompat.getColor(view.context, R.color.stroke1))
            } else {
                btn.setTextColor(ContextCompat.getColor(view.context, R.color.stroke2))
            }
            if (sharedViewModel.getTile() == 1) {
                btn.setBackgroundResource((R.drawable.shape1))
            } else {
                btn.setBackgroundResource((R.drawable.shape2))
            }
        }
        btn.setOnClickListener {  }
        return btn
    }
}