package com.kkh.multimodule.data.datasource

import com.kkh.multimodule.datastore.datasource.LocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeLocalDataSource : LocalDataSource{
    private var customText : String = ""

    override suspend fun saveCustomText(saveString: String) {
        customText = saveString
    }

    override suspend fun getCustomText(): String = customText
    override fun observeCustomText(): Flow<String> = flow{
        emit(customText)
    }
}