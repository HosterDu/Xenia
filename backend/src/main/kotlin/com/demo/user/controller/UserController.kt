package ntnu.idatt2105.user.controller

import com.querydsl.core.types.Predicate
import io.swagger.annotations.Api
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import ntnu.idatt2105.core.response.Response
import ntnu.idatt2105.core.util.PaginationConstants
import ntnu.idatt2105.user.dto.DetailedUserDto
import ntnu.idatt2105.user.dto.UserDto
import ntnu.idatt2105.user.dto.UserRegistrationDto
import ntnu.idatt2105.user.model.User
import ntnu.idatt2105.user.service.UserDetailsImpl
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.querydsl.binding.QuerydslPredicate
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.util.*

@Api(value = "User services", tags = ["User Services"], description = "User Services")
@RequestMapping("/users/")
interface UserController {

    @Operation(summary = "Create a new user", responses = [
        ApiResponse(responseCode = "201", description = "Created: new user was created"),
        ApiResponse(responseCode = "400", description = "Bad request: new user was not created"),
    ])
    @PostMapping
    fun registerUser(@RequestBody userRegistrationDto: UserRegistrationDto): ResponseEntity<UserDto>


    @Operation(summary = "Fetch user details for the currently authenticated user", responses = [
        ApiResponse(responseCode = "200", description = "Success"),
        ApiResponse(responseCode = "404", description = "Not found: the authenticated was not found")
    ])
    @GetMapping("me/")
    fun getMe(@AuthenticationPrincipal principal: UserDetailsImpl): ResponseEntity<DetailedUserDto>

    @Operation(summary = "Fetch user details for the given user id", responses = [
        ApiResponse(responseCode = "200", description = "Success"),
        ApiResponse(responseCode = "404", description = "Not found: user with the given id does not exist")
    ])
    @GetMapping("{userId}/")
    fun getUser(@PathVariable userId: UUID): ResponseEntity<DetailedUserDto>

    @Operation(summary = "Fetch users", responses = [ApiResponse(responseCode = "200", description = "Success")])
    @GetMapping
    fun getUsers(
        @QuerydslPredicate(root = User::class) predicate: Predicate,
        @PageableDefault(size = PaginationConstants.PAGINATION_SIZE, sort = ["firstName"], direction = Sort.Direction.ASC) pageable: Pageable
    ): ResponseEntity<Page<UserDto>>

    @Operation(summary = "Update existing user", responses = [
        ApiResponse(responseCode = "200", description = "Success: user was updated"),
        ApiResponse(responseCode = "400", description = "Bad request: existing user was not updated"),
        ApiResponse(responseCode = "404", description = "Not found: user with the given id does not exist"),
    ])
    @PutMapping("{userId}/")
    fun updateUser(@PathVariable userId: UUID, @Valid @RequestBody user: UserDto): ResponseEntity<UserDto>

    @Operation(summary = "Delete existing user", responses = [
        ApiResponse(responseCode = "200", description = "Success: user was deleted"),
        ApiResponse(responseCode = "404", description = "Not found: user with the given id does not exist"),
    ])
    @DeleteMapping("{userId}/")
    fun deleteUser(@PathVariable userId: UUID): ResponseEntity<Response>
}
