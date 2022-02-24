package gym.user.infrastructure.query.finder

import gym.user.application.find.UserFinder
import gym.user.application.find.UserResponse
import gym.user.infrastructure.repository.UserRepositoryPostgreSQL
import gym.user.infrastructure.query.finder.model.UserQueryPostgreSQL
import shared.domain.Service
import shared.infrastructure.bus.query.QueryHandler

@Service
class UserFinderPostgreSQL(private var userRepository: UserRepositoryPostgreSQL): QueryHandler<UserQueryPostgreSQL, UserResponse> {
    override fun handle(query: UserQueryPostgreSQL): UserResponse {
        return UserFinder(userRepository).finder(query.dni!!)
    }
}