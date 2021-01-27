package ekotermo.service

import ekotermo.data.domain.User
import ekotermo.data.service.UserDataService
import ekotermo.dto.CheckDuplicateUserDto
import ekotermo.dto.CheckDuplicateUserResponseDto
import ekotermo.dto.RegistrationRequestDto
import ekotermo.dto.UserResponseDto
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class UserService {

    @Autowired private UserDataService userDataService

    UserResponseDto findById(String id){

        User user = userDataService.findById(id)

        return UserResponseDto.buildFromDomain(user)
    }

    User create(RegistrationRequestDto registrationRequestDto){

        User user = userDataService.create(registrationRequestDto)

        return user
    }

    CheckDuplicateUserResponseDto findDuplicateUser(CheckDuplicateUserDto duplicateUserDto){

        User user = userDataService.findDuplicateUserByNameOrEmailOrPhone(duplicateUserDto)

        return new CheckDuplicateUserResponseDto(
                isLoginExists: duplicateUserDto.login == user.name,
                isEmailExists: duplicateUserDto.email == user.email,
                isPhoneExists: duplicateUserDto.phone == user.phone,
        )
    }
}
