package ekotermo.controller


import ekotermo.dto.CityDto
import ekotermo.service.CityService
import ekotermo.service.security.model.UserPrincipal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/city")
@Secured(['ROLE_ADMIN', 'ROLE_COMPANY'])
class CityController {

    @Autowired private CityService cityService

    @GetMapping("/")
    List<CityDto> getCities() {
        return cityService.getCities()
    }

    @PostMapping("/")
    void create(@AuthenticationPrincipal UserPrincipal principal) {

        cityService.create()
    }

    @PutMapping("/")
    void edit(@AuthenticationPrincipal UserPrincipal principal) {

        cityService.edit()
    }
}
