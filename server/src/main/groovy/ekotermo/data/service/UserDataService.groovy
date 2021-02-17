package ekotermo.data.service

import ekotermo.data.domain.User
import ekotermo.data.enums.Role
import ekotermo.data.repository.UserRepository
import ekotermo.dto.CheckDuplicateUserDto
import ekotermo.dto.RegistrationRequestDto
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Slf4j
@Service
class UserDataService {

    @Autowired private UserRepository userRepository

    User findById(String id){
        log.debug("Find user by user ID: [${id}]")

        User user = userRepository.findById(id).orElse(null)

        if(!user){
            log.debug("User by user ID: [${id}] not found")
            throw new UsernameNotFoundException("User by user ID: [${id}] not found")
        }

        log.debug("Found user by user ID: [${id}]")

        return user
    }

    User findByUserName(String userName){
        log.debug("Find user by user name: [${userName}]")

        User user = userRepository.findByName(userName)

        if(!user){
            log.error("User by user name: [${userName}] not found")
            throw new UsernameNotFoundException("User by user name: [${userName}] not found")
        }

        log.debug("Found user by user name: [${userName}]")

        return user
    }

    User findDuplicateUserByNameOrEmailOrPhone(CheckDuplicateUserDto checkDto){
        log.debug("Find duplicate user by checkDto: [${checkDto}]")

        User user = userRepository.findDuplicateUserByNameOrEmailOrPhone(checkDto.login, checkDto.email, checkDto.phone)

        if(!user){
            log.error("User duplicate by user checkDto: [${checkDto}] not found")
            return new User()
        }

        log.debug("Found duplicate user by user name: [${checkDto}]")

        return user
    }

    User create(RegistrationRequestDto registrationRequestDto){
        log.debug("Create user. Registration info [${registrationRequestDto}]")

        User user = new User(
                name: registrationRequestDto.login,
                password: new BCryptPasswordEncoder().encode(registrationRequestDto.password),
                email: registrationRequestDto.email,
                phone: registrationRequestDto.phone,
                city: registrationRequestDto.city,
                street: registrationRequestDto.street,
                building: registrationRequestDto.building,
                apartment: registrationRequestDto.apartment,
                serialNumber: registrationRequestDto.serialNumber,
                roles: registrationRequestDto.roles
        )

        user = userRepository.save(user)

        return user
    }
}
