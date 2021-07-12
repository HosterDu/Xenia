package com.hosterdu.xenia.profile.service

import com.hosterdu.xenia.profile.model.Profile
import com.hosterdu.xenia.profile.repository.ProfileRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ProfileService (
    private val profileRepository: ProfileRepository
        ) {
    fun findProfileById(id: String): Profile? {
        return profileRepository.findByIdOrNull(id)
    }

    fun findOrCreateProfile(profile: Profile): Profile {
        return findProfileById(profile.id) ?: profileRepository.saveAndFlush(profile)
    }

}
