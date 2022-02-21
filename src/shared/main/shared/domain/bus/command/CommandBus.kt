package shared.domain.bus.command

import shared.domain.Service

@Service
interface CommandBus {
    fun dispatch(command: Command)
}