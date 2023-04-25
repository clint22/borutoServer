package com.example.di

import com.example.database.DAOCharacter
import com.example.database.DAOCharacterImpl
import com.example.database.DatabaseFactory
import com.example.database.DatabaseProviderContract
import com.example.repository.UserRepository
import com.example.repository.UserRepositoryImpl
import com.example.repository.character.CharacterRepository
import com.example.repository.character.CharacterRepositoryImpl
import com.example.repository.hero.HeroRepository
import com.example.repository.hero.HeroRepositoryImpl
import org.koin.dsl.module


val koinModule = module {
    single<HeroRepository> {
        HeroRepositoryImpl()
    }
    single<CharacterRepository> {
        CharacterRepositoryImpl()
    }
    single<UserRepository> {
        UserRepositoryImpl()
    }
    single<DAOCharacter> {
        DAOCharacterImpl()
    }
    single<DatabaseProviderContract> {
        DatabaseFactory()
    }
}