package com.example.code

import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class ClassToInject @AssistedInject constructor(
    @Assisted("downloadTaskParams") val downloadTaskParams: DownloadTaskParams,
){
    fun getInjectedValue(): String {
        return downloadTaskParams.data
    }
}
