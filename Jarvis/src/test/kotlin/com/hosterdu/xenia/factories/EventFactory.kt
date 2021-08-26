package com.hosterdu.xenia.factories
import com.hosterdu.xenia.event.model.Event
import com.hosterdu.xenia.event.model.Geolocation
import com.hosterdu.xenia.util.IdGenerator
import org.springframework.beans.factory.FactoryBean
import java.time.ZonedDateTime


class EventFactory : FactoryBean<Event> {

    val geolocationFactory = GeolocationFactory()
    override fun getObjectType(): Class<*> =
            Event::class.java

    override fun isSingleton(): Boolean = false

    override fun getObject(): Event =
            Event(
                    id = IdGenerator().createId(),
                    title = "swap with sometinhgelse",
                    description = "what shee said",
                    startDate = ZonedDateTime.now(),
                    endDate = ZonedDateTime.now().plusDays(10),
                    location = geolocationFactory.`object`,
                    picture = ""
            )
}