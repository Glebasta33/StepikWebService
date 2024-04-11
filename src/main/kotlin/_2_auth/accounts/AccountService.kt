package _2_auth.accounts

import _2_auth.accounts.model.UserProfile

class AccountService {
    private val loginToProfile = HashMap<String, UserProfile>()
    private val sessionToProfile = HashMap<String, UserProfile>()

    fun addNewUser(userProfile: UserProfile) {
        loginToProfile[userProfile.login] = userProfile
    }

    fun getUserByLogin(login: String): UserProfile? {
        return loginToProfile[login]
    }

    fun getUserBySessionId(sessionId: String): UserProfile? {
        return sessionToProfile[sessionId]
    }

    fun addSession(sessionId: String, userProfile: UserProfile) {
        sessionToProfile[sessionId] = userProfile
    }

    fun deleteSession(sessionId: String) {
        sessionToProfile.remove(sessionId)
    }
}