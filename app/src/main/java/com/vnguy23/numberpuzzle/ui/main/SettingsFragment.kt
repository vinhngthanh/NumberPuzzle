package com.vnguy23.numberpuzzle.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.vnguy23.numberpuzzle.R
import com.vnguy23.numberpuzzle.ui.main.model.MainViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsFragment : Fragment() {

    private lateinit var backToWelcome: Button
    private lateinit var TileColor: RadioGroup
    private lateinit var TextColor: RadioGroup
    private lateinit var TileColorOption: RadioButton
    private lateinit var TextColorOption: RadioButton
    private val sharedViewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        var tileOption = 1
        var textOption = 1
        TileColor = view.findViewById(R.id.TileSettingGroup)
        TileColor.setOnCheckedChangeListener { radioGroup, i ->
            TileColorOption = view.findViewById<RadioButton>(i)
            tileOption = if(TileColorOption.text.toString() == "BLUE TILE"){
                1
            } else{
                2
            }
        }
        TextColor = view.findViewById(R.id.TextSettingGroup)
        TextColor.setOnCheckedChangeListener { radioGroup, i ->
            TextColorOption = view.findViewById<RadioButton>(i)
            textOption = if(TextColorOption.text.toString() == "BLACK TEXT"){
                1
            } else{
                2
            }
        }
        backToWelcome = view.findViewById(R.id.SettingsToWelcome)
        backToWelcome.setOnClickListener {
            val action = SettingsFragmentDirections.actionSettingsFragmentToWelcomeFragment()
            findNavController().navigate(action)
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SettingsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}