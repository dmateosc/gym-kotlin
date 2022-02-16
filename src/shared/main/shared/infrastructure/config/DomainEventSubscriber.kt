package shared.infrastructure.config

import shared.domain.DomainEvent
import java.lang.annotation.Inherited
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.CLASS)
@Inherited
annotation class DomainEventSubscriber(val value: Array<KClass<out DomainEvent>>)