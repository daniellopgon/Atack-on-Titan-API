package com.example.atackontitanapi.core.di

import android.content.Context
import com.example.atackontitanapi.core.data.local.xml.XmlCacheStorage
import com.example.atackontitanapi.features.characters.data.local.xml.CharacterXmlModel
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("com.example.atackontitanapi")
class AppModule {

    @Single
    fun provideCharacterXmlCacheStorage(context: Context): XmlCacheStorage<CharacterXmlModel> {
        return XmlCacheStorage(
            context,
            "character_cache",
            CharacterXmlModel.serializer()
        )
    }
}
