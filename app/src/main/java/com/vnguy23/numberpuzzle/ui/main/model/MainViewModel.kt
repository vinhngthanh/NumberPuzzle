package com.vnguy23.numberpuzzle.ui.main.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _tileChoice: MutableLiveData<Int> = MutableLiveData()
    private val _textChoice: MutableLiveData<Int> = MutableLiveData()

    val tileChoice: LiveData<Int>
        get() = _tileChoice

    val textChoice: LiveData<Int>
        get() = _textChoice

    init {
        _tileChoice.value = 1
    }

    init {
        _textChoice.value = 1
    }

    fun changeTile(opt:Int) {
        _tileChoice.value = opt
    }

    fun changeText(opt:Int) {
        _textChoice.value = opt
    }
}