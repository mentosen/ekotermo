package ekotermo.controller

import ekotermo.dto.CheckDuplicateUserDto
import ekotermo.dto.CheckDuplicateUserResponseDto
import ekotermo.dto.UserResponseDto
import ekotermo.service.UserService
import ekotermo.service.security.model.UserPrincipal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController {

    @Autowired UserService userService

    @GetMapping("/getInfo")
    UserResponseDto findUser(@AuthenticationPrincipal UserPrincipal principal) {

        return userService.findById(principal.userId)
    }

    @GetMapping("/duplicate")
    CheckDuplicateUserResponseDto findDuplicateUser(@RequestParam('name') String name,
                                                    @RequestParam('email') String email,
                                                    @RequestParam('phone') String phone) {

        return userService.findDuplicateUser(new CheckDuplicateUserDto(name, email, phone))
    }
}
