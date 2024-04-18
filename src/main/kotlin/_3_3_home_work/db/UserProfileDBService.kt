package _3_3_home_work.db

import _3_3_home_work.accounts.model.UserProfile
import org.hibernate.SessionFactory
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.cfg.Configuration

class UserProfileDBService(
    private val sessionFactory: SessionFactory = createSessionFactory(getH2Configuration())
) {


    fun getUser(login: String): UserProfile? {
        val session = sessionFactory.openSession()
        val dao = UserProfileDAO(session)
        val user = dao.getUser(login)
        session.close()
        return user

    }

    fun addUser(login: String, pass: String, email: String): Long {
        val session = sessionFactory.openSession()
        val transaction = session.beginTransaction()
        val dao = UserProfileDAO(session)
        val id = dao.insertUser(login, pass, email)
        transaction.commit()
        session.close()
        return id
    }

    fun printConnectInfo() {
        val connection = sessionFactory.currentSession.disconnect()
        println("DB name: ${connection.metaData.databaseProductName}")
        println("DB version: ${connection.metaData.databaseProductVersion}")
        println("Driver: ${connection.metaData.driverName}")
        println("Autocommit: ${connection.autoCommit}")
    }

    companion object {
        private const val HIBERNATE_SHOW_SQL = "true"
        private const val HIBERNATE_HBM2DDL = "create"

        fun getH2Configuration(): Configuration {
            return Configuration().apply {
                setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
                setProperty("hibernate.connection.driver_class", "org.h2.Driver")
                setProperty("hibernate.connection.url", "jdbc:h2:./h2db")
                setProperty("hibernate.connection.username", "tully")
                setProperty("hibernate.connection.password", "tully")
                setProperty("hibernate.show_sql", HIBERNATE_SHOW_SQL)
                setProperty("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL)
                addAnnotatedClass(UserProfile::class.java)
            }

        }


        fun createSessionFactory(configuration: Configuration): SessionFactory {
            val serviceRegistry = StandardServiceRegistryBuilder()
                .applySettings(configuration.properties)
                .build()

            return configuration.buildSessionFactory(serviceRegistry)
        }
    }
}