package com.hosterdu.xenia.factories

import com.hosterdu.xenia.event.model.Event
import com.hosterdu.xenia.event.model.Geolocation
import org.springframework.beans.factory.FactoryBean
import java.time.ZonedDateTime
import java.util.*


class GeolocationFactory : FactoryBean<Geolocation> {


    override fun getObjectType(): Class<*> =
        Event::class.java

    override fun isSingleton(): Boolean = false

    override fun getObject(): Geolocation =
        Geolocation(
            id = "",
            lat = Random().nextLong(),
            lng = Random().nextLong()
        )
}