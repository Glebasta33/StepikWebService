package _3_3_home_work.accounts.model

import javax.persistence.*

@Entity
@Table(name = "user_profile")
data class UserProfile(
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,

    @Column(name = "login", nullable = false)
    val login: String = "",

    @Column(name = "pass", nullable = false)
    val pass: String = "",

    @Column(name = "email", nullable = false)
    val email: String = ""
)
