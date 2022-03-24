package com.vnguy23.numberpuzzle.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vnguy23.numberpuzzle.R
import com.vnguy23.numberpuzzle.ui.main.model.Config
import com.vnguy23.numberpuzzle.ui.main.model.ConfigList

@Suppress("DEPRECATION")
class ConfigFragment : Fragment() {

    private lateinit var toGame:Button
    private lateinit var recycler: RecyclerView
    private lateinit var prefs: SharedPreferences
    private lateinit var gameMode: TextView

    companion object {
        fun newInstance() = ConfigFragment()
    }

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
        prefs = PreferenceManager.getDefaultSharedPreferences(view.context)
        toGame = view.findViewById(R.id.configToGame)
        gameMode = view.findViewById(R.id.gameMode)
        toGame.setOnClickListener {
            val action = ConfigFragmentDirections.actionConfigFragmentToGameFragment()
            findNavController().navigate(action)
        }
        populateConfig()
        recycler = view.findViewById(R.id.recyclerView)
        recycler.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recycler.adapter = ColorAdapter(ConfigList)
    }

    private fun populateConfig() {
        if(ConfigList.isEmpty()){
            val config1 = Config(resources.getString(R.string.easy))
            ConfigList.add(config1)
            val config2 = Config(resources.getString(R.string.medium))
            ConfigList.add(config2)
            val config3 = Config(resources.getString(R.string.hard))
            ConfigList.add(config3)
        }
    }

    private inner class ColorAdapter(private val list: List<Config>) : RecyclerView.Adapter<ConfigViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConfigViewHolder {
            val view = layoutInflater.inflate(R.layout.recycler_item, parent, false)
            return ConfigViewHolder(view)
        }

        override fun getItemCount() = list.size

        override fun onBindViewHolder(holder: ConfigViewHolder, position: Int) {
            holder.bind(list[position])
        }
    }

    private inner class ConfigViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var configList: Config
        private val configText: TextView = itemView.findViewById(R.id.config_textView)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if(configList.mode == resources.getString(R.string.easy)){
                gameMode.text = resources.getString(R.string.game_mode_easy)
            }else if(configList.mode == resources.getString(R.string.medium)){
                gameMode.text = resources.getString(R.string.game_mode_medium)
            }else{
                gameMode.text = resources.getString(R.string.game_mode_hard)
            }
        }

        fun bind(Options: Config) {
            this.configList = Options
            configText.text = Options.mode
        }
    }
}