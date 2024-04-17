package _3_jdbc.db_service.executor

import java.sql.ResultSet

interface ResultHandler<T> {
    fun handle(resultSet: ResultSet): T
}