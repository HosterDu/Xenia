package ntnu.idatt2105.user.model

import ntnu.idatt2105.security.token.PasswordResetToken
import java.util.*
import javax.persistence.*

@Entity
data class User(
    @Id
    @Column(columnDefinition = "CHAR(32)")
    var id: UUID = UUID.randomUUID(),
    var firstName: String = "",
    var surname: String = "",
    @Column(unique = true)
    var email: String = "",
    var phoneNumber: String = "",
    var image: String = "",
    var password: String = "",
    @OneToOne(mappedBy = "user", cascade = [CascadeType.ALL])
    val pwdToken: PasswordResetToken? = null
)
