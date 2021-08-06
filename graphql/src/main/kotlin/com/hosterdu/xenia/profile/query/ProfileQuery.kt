package com.hosterdu.xenia.profile.query

import com.expediagroup.graphql.server.operations.Query
import com.hosterdu.xenia.context.XeniaGraphQLContext
import com.hosterdu.xenia.profile.service.ProfileService
import org.springframework.stereotype.Component

@Component
class ProfileQuery (val profileService: ProfileService): Query {
    fun  profile(context: XeniaGraphQLContext) = context.profile?.let { profileService.findProfileById(it.id) }

}

