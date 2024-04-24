package com.github.gltrusov.account_server

interface AccountServerControllerMBean {
    fun getUsers(): Int

    fun getUsersLimit(): Int

    fun setUsersLimit(limit: Int)
}