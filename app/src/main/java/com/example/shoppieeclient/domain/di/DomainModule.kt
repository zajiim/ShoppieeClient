package com.example.shoppieeclient.domain.di

import org.koin.dsl.module

val domainModule = module {
    includes(useCasesModule)
}