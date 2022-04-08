package com.sorsix.barmanagmentapi.domain

import com.sorsix.barmanagmentapi.domain.enumerations.Role
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*
import javax.persistence.Table
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size


@Entity
@Table(
    name = "users"
)
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @NotBlank
    @Email
    @Column(unique = true)
    val email: String,
    @NotBlank
    @Size(min = 8, max = 100)
    private val password: String,
    @NotBlank
    @Size(min = 2, max = 100)
    val name: String,
    val phoneNumber: String? = null,
    @Enumerated(EnumType.STRING)
    val role: Role = Role.WAITER,
    @OneToMany
    val locale: List<Locale>? = null
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        mutableListOf(SimpleGrantedAuthority(role.name))

    override fun getUsername(): String = email

    override fun getPassword(): String = password

    override fun isEnabled(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true
}

