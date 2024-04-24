package com.github.gltrusov.account_server

class AccountServerController(
    private val accountServer: AccountServerI
) : AccountServerControllerMBean {
    override fun getUsers(): Int {
        return accountServer.getUserCount()
    }

    override fun getUsersLimit(): Int {
        return accountServer.getUserLimit()
    }

    override fun setUsersLimit(limit: Int) {
        accountServer.setUserLimit(limit)
    }
}