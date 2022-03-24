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
import androidx.lifecycle.ViewModelProvider
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
    private lateinit var tileColor: RadioGroup
    private lateinit var textColor: RadioGroup
    private lateinit var tileColorOption: RadioButton
    private lateinit var textColorOption: RadioButton
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
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        val sharedViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        if(sharedViewModel.getTile() == 1){
            tileColorOption = view.findViewById(R.id.BlueTile)
            tileColorOption.isChecked = true
        }else{
            tileColorOption = view.findViewById(R.id.RedTile)
            tileColorOption.isChecked = true
        }

        if(sharedViewModel.getText() == 1){
            textColorOption = view.findViewById(R.id.BlackText)
            textColorOption.isChecked = true
        }else{
            textColorOption = view.findViewById(R.id.BeigeText)
            textColorOption.isChecked = true
        }
        tileColor = view.findViewById(R.id.TileSettingGroup)
        tileColor.setOnCheckedChangeListener { _, i ->
            tileColorOption = view.findViewById<RadioButton>(i)
            if(tileColorOption.text.toString() == resources.getString(R.string.blue_tile)){
                sharedViewModel.changeTile(1)
            } else{
                sharedViewModel.changeTile(2)
            }
        }
        textColor = view.findViewById(R.id.TextSettingGroup)
        textColor.setOnCheckedChangeListener { _, i ->
            textColorOption = view.findViewById<RadioButton>(i)
            if(textColorOption.text.toString() == resources.getString(R.string.black_text)){
                sharedViewModel.changeText(1)
            } else{
                sharedViewModel.changeText(2)
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