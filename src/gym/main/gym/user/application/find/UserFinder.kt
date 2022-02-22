package gym.user.application.find

import gym.user.domain.model.DNI
import gym.user.domain.model.User
import gym.user.domain.model.UserId
import gym.user.domain.repository.UserRepository
import gym.user.infrastructure.mapper.UserMapperResponse

class UserFinder(
    private val userRepository: UserRepository
) {

    fun finder(dni: String): UserResponse{
        return UserMapperResponse().fromDomain(userRepository.findByDNI(DNI(dni)))
    }

}