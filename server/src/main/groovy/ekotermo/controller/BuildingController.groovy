package ekotermo.controller

import ekotermo.data.enums.Role
import ekotermo.dto.BuildingDto
import ekotermo.service.BuildingService
import ekotermo.service.security.model.UserPrincipal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/building")
@Secured(['ROLE_ADMIN', 'ROLE_COMPANY'])
class BuildingController {

    @Autowired private BuildingService buildingService

    @GetMapping("/")
    BuildingDto findBuilding(@AuthenticationPrincipal UserPrincipal principal, @RequestParam('id') String id) {

        buildingService.findByIdAndUserId(id, principal.userId)
    }

    @PostMapping("/")
    void create(@AuthenticationPrincipal UserPrincipal principal,
                @RequestBody BuildingDto buildingDto) {

        buildingService.create(principal.userId, buildingDto)
    }

    @PutMapping("/")
    void edit(@AuthenticationPrincipal UserPrincipal principal,
                                     @RequestBody BuildingDto buildingDto) {

        buildingService.edit(principal.userId, buildingDto)
    }
}
