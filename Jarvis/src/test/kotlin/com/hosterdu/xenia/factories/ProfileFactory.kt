package com.hosterdu.xenia.factories

import com.hosterdu.xenia.event.model.Event
import com.hosterdu.xenia.event.model.Geolocation
import com.hosterdu.xenia.profile.model.Profile
import com.hosterdu.xenia.util.IdGenerator
import org.springframework.beans.factory.FactoryBean
import java.util.*

class ProfileFactory : FactoryBean<Profile> {
    override fun getObjectType(): Class<*> =
            Profile::class.java

    override fun isSingleton(): Boolean = false

    override fun getObject(): Profile =
            Profile(
                    id = IdGenerator().createId(),
                    email = "her@her.com",
                    given_name = "h",
                    family_name = "lol",
                    picture = "fddsf",
                    locale = "ddd"
            )
}