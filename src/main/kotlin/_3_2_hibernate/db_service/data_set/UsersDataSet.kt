package _3_2_hibernate.db_service.data_set

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
data class UsersDataSet(
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,

    @Column(name = "name", nullable = false)
    val name: String = ""
) : Serializable