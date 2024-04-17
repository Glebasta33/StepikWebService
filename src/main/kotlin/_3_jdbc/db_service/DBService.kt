package _3_jdbc.db_service

import _3_jdbc.db_service.dao.UsersDAO
import _3_jdbc.db_service.data_set.UsersDataSet
import org.h2.jdbcx.JdbcDataSource
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class DBService(
    private val connection: Connection = getH2Connection()
) {

    fun getUser(id: Long): UsersDataSet {
        try {
            return UsersDAO(connection).get(id)
        } catch (e: SQLException) {
            throw DBException(e)
        }
    }

    fun addUser(name: String): Long {
        try {
            connection.autoCommit = false
            val dao = UsersDAO(connection)
            dao.createTable() //?
            dao.insertUser(name)
            connection.commit()
            return dao.getUserId(name)
        } catch (e: SQLException) {
            connection.rollback()
            throw DBException(e)
        } finally {
            connection.autoCommit = true
        }
    }

    fun cleanUp() {
        val dao = UsersDAO(connection)
        try {
            dao.dropTable()
        } catch (e: SQLException) {
            throw DBException(e)
        }
    }

    fun printConnectInfo() {
        println("DB name: ${connection.metaData.databaseProductName}")
        println("DB version: ${connection.metaData.databaseProductVersion}")
        println("Driver: ${connection.metaData.driverName}")
        println("Autocommit: ${connection.autoCommit}")
    }

    companion object {
        fun getH2Connection(): Connection {
            val urlH2 = "jdbc:h2:./h2db"
            val name = "tully"
            val pass = "tully"
            JdbcDataSource().apply {
                url = urlH2
                user = name
                password = pass
            }

            val connection: Connection = DriverManager.getConnection(urlH2, name, pass)
            return connection

        }


        // тут можно добавить также функцию для создания соединения с другой БД: MySQL или PostgreSQL.
    }
}