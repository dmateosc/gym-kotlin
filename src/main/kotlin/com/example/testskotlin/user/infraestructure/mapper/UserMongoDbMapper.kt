package com.example.testskotlin.user.infraestructure.mapper

import com.example.testskotlin.user.domain.model.*
import com.example.testskotlin.user.infraestructure.repository.entity.UserEntity
import com.example.testskotlin.user.infraestructure.repository.entity.UserMongo


class UserMongoDbMapper {

    fun entityToDto(userEntity: UserMongo): User{

        return User(
            UserId(userEntity.userId),
            Name(userEntity.name),
            LastName(userEntity.first_lastname),
            LastName(userEntity.second_lastname),
            Email(userEntity.email),
            Age(userEntity.age),
            Password(userEntity.password),
            DNI(userEntity.dni)
        )
    }

    fun dtoToEntity(user: User):UserMongo{
        return UserMongo(
            userId = user.userId().value,
            dni = user.dni().value,
            name = user.name().value,
            first_lastname = user.firstLastName().value,
            second_lastname = user.secondLastName().value,
            age = user.age().value,
            email = user.email().value,
            password = user.password().value
        )
    }

}