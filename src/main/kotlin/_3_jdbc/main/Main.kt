package _3_jdbc.main

import _3_jdbc.db_service.DBException
import _3_jdbc.db_service.DBService

fun main() {
    val dbService = DBService()
    dbService.printConnectInfo()

    try {
        val userId: Long = dbService.addUser("tully")
        println("Added user, id: $userId")

        val dataSet = dbService.getUser(userId)
        println("User data set from db: $dataSet")

        dbService.cleanUp()
    } catch (e: DBException) {
        e.printStackTrace()
    }
}