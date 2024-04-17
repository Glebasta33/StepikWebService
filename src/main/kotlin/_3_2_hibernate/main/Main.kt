package _3_2_hibernate.main

import _3_2_hibernate.db_service.DBException
import _3_2_hibernate.db_service.DBService

fun main() {
    val dbService = DBService()
//    dbService.printConnectInfo()

    try {
        val userId: Long = dbService.addUser("tully")
        println("Added user, id: $userId")

        val dataSet = dbService.getUser(userId)
        println("User data set from db: $dataSet")

    } catch (e: DBException) {
        e.printStackTrace()
    }
}