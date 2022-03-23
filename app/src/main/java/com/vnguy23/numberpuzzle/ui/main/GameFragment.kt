package com.vnguy23.numberpuzzle.ui.main

import android.annotation.SuppressLint
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

/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {

    private lateinit var toResult:Button
    private lateinit var buttons:Array<Array<Button>>
//    private var sharedViewModel: MainViewModel by activityViewModels()

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
        val sharedViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
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
        val btn: Button = view.findViewById(resources.getIdentifier("Button$r$c", "id", context?.packageName ?: null))
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GameFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GameFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}