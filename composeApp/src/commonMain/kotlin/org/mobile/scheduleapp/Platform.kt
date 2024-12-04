package org.mobile.scheduleapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform