package _2_auth.accounts.model

data class UserProfile(
    val login: String,
    val pass: String,
    val email: String?
)
