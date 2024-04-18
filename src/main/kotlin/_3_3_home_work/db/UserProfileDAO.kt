package _3_3_home_work.db

import _3_3_home_work.accounts.model.UserProfile
import org.hibernate.Criteria
import org.hibernate.Session
import org.hibernate.criterion.Restrictions

class UserProfileDAO(
     private val session: Session
) {

    fun getUser(login: String): UserProfile? {
        val criteria: Criteria = session.createCriteria(UserProfile::class.java)
        return criteria.add(Restrictions.eq("login", login)).uniqueResult() as UserProfile?
    }

    fun insertUser(login: String, pass: String, email: String): Long {
        return session.save(UserProfile(
            login = login,
            pass = pass,
            email = email
        )) as Long
    }

}