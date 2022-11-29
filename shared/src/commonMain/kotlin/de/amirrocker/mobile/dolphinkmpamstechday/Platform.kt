package de.amirrocker.mobile.dolphinkmpamstechday

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
