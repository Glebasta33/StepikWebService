package _3_2_hibernate.db_service.executor

import java.sql.ResultSet

interface ResultHandler<T> {
    fun handle(resultSet: ResultSet): T
}