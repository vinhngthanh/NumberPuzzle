package com.vnguy23.numberpuzzle.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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

/**
 * A simple [Fragment] subclass.
 * Use the [ConfigFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConfigFragment : Fragment() {

    private lateinit var toGame:Button
    private lateinit var recycler: RecyclerView
    private lateinit var prefs: SharedPreferences
    private lateinit var gameMode: TextView
    private var isClicked:Boolean = false

    companion object {
        const val POSITION = "adapter_position"
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

        recycler = view.findViewById(R.id.recyclerView)
        recycler.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        populateConfig()
        recycler.adapter = ColorAdapter(ConfigList)
    }

    private fun populateConfig() {
        val c1 = resources.getString(R.string.easy)
        val Config1 = Config(c1)
        ConfigList.add(Config1)
        val c2 = resources.getString(R.string.medium)
        val Config2 = Config(c2)
        ConfigList.add(Config2)
        val c3 = resources.getString(R.string.hard)
        val Config3 = Config(c3)
        ConfigList.add(Config3)
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
            if(configList.mode == "easy"){
                gameMode.text = resources.getString(R.string.game_mode_easy)
            }else if(configList.mode == "medium"){
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