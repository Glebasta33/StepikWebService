package _3_jdbc.db_service.dao

import _3_jdbc.db_service.data_set.UsersDataSet
import _3_jdbc.db_service.executor.Executor
import _3_jdbc.db_service.executor.ResultHandler
import java.sql.Connection
import java.sql.ResultSet

class UsersDAO(
    connection: Connection
) {
    private val executor: Executor = Executor(connection)

    fun get(id: Long): UsersDataSet {
        return executor.execQuery("select * from users where id=$id", object : ResultHandler<UsersDataSet> {
            override fun handle(resultSet: ResultSet): UsersDataSet {
                resultSet.next()
                return UsersDataSet(resultSet.getLong(1), resultSet.getString(2))
            }
        })
    }

    fun getUserId(name: String): Long {
        return executor.execQuery("select * from users where user_name='$name'", object : ResultHandler<Long> {
            override fun handle(resultSet: ResultSet): Long {
                resultSet.next()
                return resultSet.getLong(1)
            }
        })
    }

    fun insertUser(name: String) {
        executor.execUpdate("insert into users (user_name) values ('$name')")
    }

    fun createTable() {
        executor.execUpdate("create table if not exists users (id bigint auto_increment, user_name varchar(256), primary key (id))")
    }

    fun dropTable() {
        executor.execUpdate("drop table users")
    }
}