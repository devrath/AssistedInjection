package com.example.code

import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class ClassToInject @AssistedInject constructor(
    @Assisted("downloadTaskParams") val modelDataClass: ModelDataClass,
){
    fun getInjectedValue(): String {
        return modelDataClass.data
    }
}
