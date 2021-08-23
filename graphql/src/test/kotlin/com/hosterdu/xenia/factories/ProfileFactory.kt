package com.hosterdu.xenia.factories

import com.hosterdu.xenia.event.model.Event
import com.hosterdu.xenia.event.model.Geolocation
import com.hosterdu.xenia.profile.model.Profile
import org.springframework.beans.factory.FactoryBean
import java.util.*

class ProfileFactory : FactoryBean<Profile> {


    override fun getObjectType(): Class<*> =
        Event::class.java

    override fun isSingleton(): Boolean = false

    override fun getObject(): Profile =
        Profile(
            id = "",
            email = "reeee",
            given_name = "namer",
            family_name = "dsdsds",
            picture = "sdsds",
            locale = "dsdds"


        )
}