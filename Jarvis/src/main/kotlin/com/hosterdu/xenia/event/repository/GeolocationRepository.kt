package com.hosterdu.xenia.event.repository

import com.hosterdu.xenia.event.model.Geolocation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
 interface GeolocationRepository: JpaRepository<Geolocation, String>