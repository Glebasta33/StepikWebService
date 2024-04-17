package _3_2_hibernate.db_service.dao

import _3_2_hibernate.db_service.data_set.UsersDataSet
import org.hibernate.Criteria
import org.hibernate.Session
import org.hibernate.criterion.Restrictions

class UsersDAO(
     private val session: Session
) {
    fun get(id: Long): UsersDataSet {
        return session.get(UsersDataSet::class.java, id)
    }

    fun getUserId(name: String): Long {
        val criteria: Criteria = session.createCriteria(UsersDataSet::class.java)
        return (criteria.add(Restrictions.eq("name", name)).uniqueResult() as UsersDataSet).id
    }

    fun insertUser(name: String): Long {
        return session.save(UsersDataSet(name = name)) as Long
    }

}