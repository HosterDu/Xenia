package com.hosterdu.xenia.profile.model

import java.security.Principal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Profile(
    @Id
    val id: String,
    @Column(unique=true)
    val email: String,
    val given_name: String,
    val family_name: String,
    val picture: String,
    @Column(length = 6)
    val locale: String,
): Principal {
    override fun getName(): String {
        return email
    }
}
