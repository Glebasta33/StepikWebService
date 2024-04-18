package _3_3_home_work.accounts

import _3_3_home_work.accounts.model.UserProfile
import _3_3_home_work.db.UserProfileDBService

class UsersProfileService(
    private val dbService: UserProfileDBService
) {

    fun addNewUser(login: String, pass: String, email: String) {
        dbService.addUser(login, pass, email)
    }

    fun getUserByLogin(login: String): UserProfile? {
        return dbService.getUser(login)
    }
}