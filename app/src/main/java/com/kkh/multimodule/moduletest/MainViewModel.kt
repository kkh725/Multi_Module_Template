package com.kkh.multimodule.moduletest

import androidx.lifecycle.ViewModel
import com.kkh.multimodule.domain.repository.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val historyRepository: HistoryRepository) :
    ViewModel() {

    internal fun initConfig(){}
}