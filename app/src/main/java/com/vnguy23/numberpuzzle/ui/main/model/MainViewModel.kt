package com.vnguy23.numberpuzzle.ui.main.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var _tileChoice: Int = 1
    private var _textChoice: Int = 1

    fun changeTile(opt:Int) {
        _tileChoice = opt
    }

    fun changeText(opt:Int) {
        _textChoice = opt
    }

    fun getTile(): Int {
        return _tileChoice
    }

    fun getText(): Int {
        return _textChoice
    }
}