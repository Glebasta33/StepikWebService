package _3_1_jdbc.db_service.executor

import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement

class Executor(
    private val connection: Connection
) {

    fun execUpdate(update: String) {
        val statement: Statement = connection.createStatement()
        statement.execute(update)
        statement.close()
    }

    fun <T> execQuery(query: String, handler: ResultHandler<T>): T {
        val statement: Statement = connection.createStatement()
        statement.execute(query)
        val result: ResultSet = statement.resultSet
        val value: T = handler.handle(result)
        result.close()
        statement.close()

        return value
    }

}