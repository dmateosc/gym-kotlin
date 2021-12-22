package com.example.testskotlin.user.infraestructure.mapper

import com.example.testskotlin.user.domain.model.*
import com.example.testskotlin.user.infraestructure.repository.entity.UserEntity


class UserPostgreSQLMapper {

    fun entityToDto(userEntity: UserEntity): User{

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

    fun dtoToEntity(user: User):UserEntity{
        return UserEntity(
            userId = user.userId!!.value,
            dni = user.dni.value,
            name = user.name.value,
            first_lastname = user.firstLastName.value,
            second_lastname = user.secondLastName.value,
            age = user.age.value,
            email = user.email.value,
            password = user.password.value
        )
    }

}