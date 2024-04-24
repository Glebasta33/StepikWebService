package com.github.gltrusov.account_server

class AccountServer(
    private var usersCount: Int = 0,
    private var usersLimit: Int
) : AccountServerI {


    override fun addNewUser() {
        usersCount++
    }

    override fun removerUser() {
        usersCount--
    }

    override fun getUserLimit(): Int {
        return usersLimit
    }

    override fun setUserLimit(limit: Int) {
        usersLimit = limit
    }

    override fun getUserCount(): Int {
        return usersCount
    }
}