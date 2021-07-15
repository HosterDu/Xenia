package com.hosterdu.xenia.event.repository

import com.hosterdu.xenia.event.model.Geolocation
import org.springframework.data.jpa.repository.JpaRepository

interface GeolocationRepository: JpaRepository<Geolocation, Long>
