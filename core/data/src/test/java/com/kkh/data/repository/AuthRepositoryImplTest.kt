package com.kkh.data.repository

import com.kkh.data.datasource.FakeLocalTokenDataSource
import com.kkh.data.datasource.FakeLocalUserDataSource
import com.kkh.datastore.datasource.user.LocalUserDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AuthRepositoryImplTest {
    private lateinit var localUserDataSource: FakeLocalUserDataSource
    private lateinit var localTokenDataSource: FakeLocalTokenDataSource

    private lateinit var authRepository: AuthRepositoryImpl

    @BeforeEach
    fun setUp() {
        localUserDataSource = FakeLocalUserDataSource()
        localTokenDataSource = FakeLocalTokenDataSource()

        authRepository = AuthRepositoryImpl(
            localUserDataSource = localUserDataSource,
            localTokenDataSource = localTokenDataSource
        )
    }

    @Test
    fun `유저가 회원가입에 성공했을 경우 토큰과 유저 상태를 저장한다`() = runTest {
        // when
        authRepository.login()

        // then
        assertTrue(localTokenDataSource.accessToken.first().isNotEmpty())
        assertTrue(localTokenDataSource.refreshToken.first().isNotEmpty())
    }
}
