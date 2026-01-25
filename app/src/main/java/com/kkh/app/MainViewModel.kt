package com.kkh.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkh.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject
    constructor(
        authRepository: AuthRepository,
    ) : ViewModel() {
        // coldStream to hotStream
        internal val autoLogin: StateFlow<Boolean?> =
            authRepository.autoLogin
                .stateIn(
                    scope = viewModelScope,
                    // 중단 이후 5초간 유지. 화면 전환
                    started = SharingStarted.WhileSubscribed(5_000),
                    initialValue = null,
                )

        internal fun initConfig() {}
    }