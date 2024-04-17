package _3_2_hibernate.db_service

import _3_2_hibernate.db_service.dao.UsersDAO
import _3_2_hibernate.db_service.data_set.UsersDataSet
import org.hibernate.SessionFactory
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.cfg.Configuration
import org.hibernate.internal.SessionFactoryImpl
import java.sql.SQLException

class DBService(
    private val sessionFactory: SessionFactory = createSessionFactory(getH2Configuration())
) {


    fun getUser(id: Long): UsersDataSet {
        try {
            val session = sessionFactory.openSession()
            val dao = UsersDAO(session)
            val dataSet = dao.get(id)
            session.close()
            return dataSet
        } catch (e: SQLException) {
            throw DBException(e)
        }
    }

    fun addUser(name: String): Long {
        try {
            val session = sessionFactory.openSession()
            val transaction = session.beginTransaction()
            val dao = UsersDAO(session)
            val id = dao.insertUser(name)
            transaction.commit()
            session.close()
            return id
        } catch (e: SQLException) {
            throw DBException(e)
        }
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
                addAnnotatedClass(UsersDataSet::class.java)
//                addResource("hibernate.cfg.xml")
            }

        }

        // тут можно добавить также функцию для создания соединения с другой БД: MySQL или PostgreSQL.

        fun createSessionFactory(configuration: Configuration): SessionFactory {
            val serviceRegistry = StandardServiceRegistryBuilder()
                .applySettings(configuration.properties)
                .build()

            return configuration.buildSessionFactory(serviceRegistry)
        }
    }
}