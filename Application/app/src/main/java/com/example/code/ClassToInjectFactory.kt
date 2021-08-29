package com.example.code

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory

@AssistedFactory
interface ClassToInjectFactory {
    fun create(
        @Assisted("downloadTaskParams") downloadTaskParams: DownloadTaskParams,
    ): ClassToInject
}