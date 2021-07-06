package com.example.config

import com.example.util.OAuth.GoogleClient
import com.example.util.OAuth.GoogleUserProfile
import com.example.util.OAuth.UserSession
import com.example.user.model.User
import com.example.user.repository.UserRepository
import com.example.util.Session.SessionHandler
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.request.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.sessions.*
import kotlinx.html.*
import java.lang.Exception
import java.util.concurrent.*

enum class loginProvider {
    github,
    google
}

val loginProviders = listOf(
/*    OAuthServerSettings.OAuth2ServerSettings(
        name = loginProvider.github.name,
        authorizeUrl = "https://github.com/login/oauth/authorize",
        accessTokenUrl = "https://github.com/login/oauth/access_token",
        clientId = "b9621591a6edab44e900",
        clientSecret = "c34fd07dde978212651a0d541256833e4b76a01e",
    ),*/
    OAuthServerSettings.OAuth2ServerSettings(
        name = loginProvider.google.name,
        authorizeUrl = "https://accounts.google.com/o/oauth2/v2/auth",
        accessTokenUrl = "https://www.googleapis.com/oauth2/v3/token",
        requestMethod = HttpMethod.Post,

        clientId = "816095121514-8jit6tmhhrpe0va6ivjvp8qvek425i49.apps.googleusercontent.com",
        clientSecret = "thI920-0swv4U0aLdB07ICNs",
        passParamsInURL = true,
        defaultScopes = listOf(
            "https://www.googleapis.com/auth/plus.login",
            "https://www.googleapis.com/auth/userinfo.email",
            "https://www.googleapis.com/auth/userinfo.profile",
        )

    ),
).associateBy { it.name }


private val exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 4)

@OptIn(KtorExperimentalLocationsAPI::class)
fun Application.oAuth() {
    oAuthWithDeps(
        oauthHttpClient = HttpClient(Apache).apply {
            environment.monitor.subscribe(ApplicationStopping) {
                close()
            }
        }
    )
}

@OptIn(KtorExperimentalLocationsAPI::class)
fun Application.oAuthWithDeps(oauthHttpClient: HttpClient) {
    val authOauthForLogin = "OAuthForLogin"
    val userRepository = UserRepository()

    install(Authentication) {
        oauth(authOauthForLogin) {
            client = oauthHttpClient
            providerLookup = {
                loginProviders[application.locations.resolve<login>(login::class, this).type]
            }
            urlProvider = { p -> redirectUrl(login(p.name), false) }
        }
    }


    routing {
        authenticate(authOauthForLogin) {
            location<login>() {
                param("error") {
                    handle {
                        call.respond("Error when logging in")

                    }
                }
                handle {
                    val principal = call.authentication.principal<OAuthAccessTokenResponse>()
                    if (principal != null) {
                        principal as OAuthAccessTokenResponse.OAuth2
                        val provider = call.parameters["type"]
                        var providerProfile: GoogleUserProfile? = null
                        when(provider) {
                            loginProvider.github.name -> {
                            }
                            loginProvider.google.name -> {
                                providerProfile = GoogleClient.getUserProfile(principal)
                            }
                            else -> {
                                println("No provider found")
                            }
                        }
                        if (providerProfile != null) {
                            val user = userRepository.readOrCreateAuthenticatedUser(providerProfile)
                            SessionHandler.setUserSession(call, user)
                        }
                        println(SessionHandler.getUserSession(call))
                        call.respondRedirect(System.getenv("FRONTEND_URL"))
                    } else {
                        call.loginPage()
                    }
                }
            }
        }
    }
}


@OptIn(KtorExperimentalLocationsAPI::class)
@Location("/login/{type?}")
class login(val type: String = "")

fun <T : Any> ApplicationCall.redirectUrl(t: T, secure: Boolean = true): String {
    val hostPort = request.host() + request.port().let { port -> if (port == 80) "" else ":$port" }
    val protocol = when {
        secure -> "https"
        else -> "http"
    }
    return "$protocol://$hostPort${application.locations.href(t)}"
}

@OptIn(KtorExperimentalLocationsAPI::class)
suspend fun ApplicationCall.loginPage() {
    respondHtml {
        head {
            title { +"Login with" }
        }
        body {
            h1 {
                +"Login with:"
            }

            for (p in loginProviders) {
                p {
                    a(href = application.locations.href(login(p.key))) {
                        +p.key
                    }
                }
            }
        }
    }
}
