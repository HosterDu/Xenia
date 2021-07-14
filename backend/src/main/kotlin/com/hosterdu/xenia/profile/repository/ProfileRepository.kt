package com.hosterdu.xenia.profile.repository

import com.hosterdu.xenia.profile.model.Profile
import org.springframework.data.jpa.repository.JpaRepository

interface ProfileRepository: JpaRepository<Profile, String>
