package ekotermo.dto

import ekotermo.data.domain.User
import ekotermo.data.enums.Role

class UserResponseDto {
    String id
    String name
    List<Role> roles

    static UserResponseDto buildFromDomain(User user){
        return new UserResponseDto(
                id: user.id,
                name: user.name,
                roles: user.roles
        )
    }
}
