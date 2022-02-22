package gym.user.infrastructure.query.finder

import gym.user.application.find.UserFinder
import gym.user.application.find.UserResponse
import gym.user.infrastructure.UserRepositoryMongoDB
import gym.user.infrastructure.UserRepositoryPostgreSQL
import gym.user.infrastructure.query.finder.model.UserQueryMongo
import shared.domain.Service
import shared.infrastructure.bus.query.QueryHandler

@Service
class MongoQueryHandler(private var userRepository: UserRepositoryMongoDB) : QueryHandler<UserQueryMongo, UserResponse> {
    override fun handle(query: UserQueryMongo): UserResponse {
        return UserFinder(userRepository).finder(query.dni!!)
    }
}