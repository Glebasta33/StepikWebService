package com.github.gltrusov.account_server

interface AccountServerI {
    fun addNewUser()

    fun removerUser()

    fun getUserLimit(): Int

    fun setUserLimit(limit: Int)

    fun getUserCount(): Int
}