package com.vnguy23.numberpuzzle.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.vnguy23.numberpuzzle.R
import com.vnguy23.numberpuzzle.ui.main.model.MainViewModel
import kotlin.math.abs

class GameFragment : Fragment() {

    private lateinit var toResult:Button
    private lateinit var buttons:Array<Array<Button>>
    private lateinit var tiles:Array<Int>
    private var emptyX:Int = 3
    private var emptyY:Int = 3

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
            Toast.makeText(context, R.string.SURRENDER,Toast.LENGTH_SHORT).show()
            val temp = "1"
            val action1 = GameFragmentDirections.actionGameFragmentToResultFragment(temp)
            findNavController().navigate(action1)
        }

        tiles = Array(15){it+1}
        do{
            tiles.shuffle()
        }while(!isSolvable(tiles))

        buttons = Array(4){r->
            Array(4){c->
                initButton(r,c, view,sharedViewModel, tiles)
            }
        }
        return view
    }

    private fun isSolvable(tiles: Array<Int>):Boolean {
        var countInversion = 0
        for(i in 0 until 15){
            for(j in 0 until i){
                if(tiles[j] > tiles[i]){
                    countInversion++
                }
            }
        }
        return countInversion%2 == 0
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initButton(
        r: Int,
        c: Int,
        view: View,
        sharedViewModel: MainViewModel,
        tiles: Array<Int>
    ): Button{
        val btn: Button = view.findViewById(resources.getIdentifier("Button$r$c", "id",
            context?.packageName
        ))
        if(r==3&&c==3) {
            btn.text = ""
            btn.background = resources.getDrawable(R.drawable.empty)
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
            btn.text = tiles[4*r + c].toString()
        }
        btn.setOnClickListener {
            btnCLicked(btn, view, sharedViewModel)
        }
        return btn
    }

    private fun btnCLicked(btn: Button, view: View, sharedViewModel: MainViewModel) {
        val arr = btn.tag.toString().toCharArray()
        val x:Int = arr[0] - '0'
        val y:Int = arr[1] - '0'

        if(((abs(emptyX-x)==1)&&(emptyY==y))||((abs(emptyY-y)==1)&&(emptyX==x))){
            val emptyBtn: Button = view.findViewById(resources.getIdentifier("Button$emptyX$emptyY", "id",
                context?.packageName
            ))
            emptyBtn.text = btn.text
            btn.text = ""
            btn.setBackgroundResource(R.drawable.empty)
            if (sharedViewModel.getText() == 1) {
                emptyBtn.setTextColor(ContextCompat.getColor(view.context, R.color.stroke1))
            } else {
                emptyBtn.setTextColor(ContextCompat.getColor(view.context, R.color.stroke2))
            }
            if (sharedViewModel.getTile() == 1) {
                emptyBtn.setBackgroundResource((R.drawable.shape1))
            } else {
                emptyBtn.setBackgroundResource((R.drawable.shape2))
            }
            emptyX = x
            emptyY = y

            checkWin(view)
        }
    }

    private fun checkWin(view: View) {
        var isWin=false
        if(emptyX==3&&emptyY==3){
            for(i in 0 until 4){
                for(j in 0 until 4){
                    if(i==3&&j==3) {continue}
                    val btn: Button = view.findViewWithTag("$i$j")
                    val count = i*4 + j + 1
                    if(btn.text == "$count"){
                        isWin = true
                    }else{
                        isWin = false
                        break;
                    }
                }
            }
        }

        if(isWin){
            val tem = "2"
            val action = GameFragmentDirections.actionGameFragmentToResultFragment(tem)
            findNavController().navigate(action)
            Toast.makeText(context, R.string.WINNER, Toast.LENGTH_SHORT).show()
        }
    }
}