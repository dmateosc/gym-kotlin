package com.example.testskotlin.user.infraestructure.bus

import com.example.testskotlin.shared.domain.Event
import com.example.testskotlin.shared.domain.EventBus
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class UserEventBus(val publisher: ApplicationEventPublisher): EventBus {
    override fun send(event: Event) {
        publisher.publishEvent(event)
    }


}