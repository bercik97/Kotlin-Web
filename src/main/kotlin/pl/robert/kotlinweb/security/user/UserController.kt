package pl.robert.kotlinweb.security.user

import lombok.AccessLevel
import lombok.experimental.FieldDefaults

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired

import pl.robert.kotlinweb.security.user.domain.User
import pl.robert.kotlinweb.security.user.domain.UserService
import pl.robert.kotlinweb.security.user.domain.dto.UpdateUserEmailDto
import pl.robert.kotlinweb.security.user.domain.dto.UserDto
import pl.robert.kotlinweb.security.user.domain.dto.UserDetailsDto

@FieldDefaults(level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("/api/user")
class UserController @Autowired constructor(val service: UserService) {

    @PostMapping("/save")
    fun save(@RequestBody dto: UserDto): ResponseEntity<User> = ResponseEntity.ok(service.save(dto))

    @GetMapping
    fun get(): ResponseEntity<Iterable<UserDetailsDto>> = ResponseEntity.ok(service.getUsers())

    @GetMapping("{email}")
    fun getByEmail(@PathVariable(name = "email") email: String): ResponseEntity<User> = ResponseEntity.ok(service.getByEmail(email))

    @PutMapping
    fun update(@RequestBody dto: UpdateUserEmailDto): ResponseEntity<User> = ResponseEntity.ok(service.updateEmail(dto.oldEmail, dto.newEmail))

    @DeleteMapping("{id}")
    fun delete(@PathVariable(name = "id") id: String): ResponseEntity<Any> = ResponseEntity.ok(service.deleteUser(id))

    @DeleteMapping
    fun deleteAll(): ResponseEntity<Any> = ResponseEntity.ok(service.deleteAll())
}