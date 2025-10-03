package com.example.symptomtracker

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
