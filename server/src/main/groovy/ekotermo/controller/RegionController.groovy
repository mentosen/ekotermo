package ekotermo.controller

import ekotermo.dto.RegionDto
import ekotermo.service.RegionService
import ekotermo.service.security.model.UserPrincipal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/region")
@Secured(['ROLE_ADMIN', 'ROLE_COMPANY'])
class RegionController {

    @Autowired private RegionService regionService

    @GetMapping("/")
    List<RegionDto> getRegions() {
        return regionService.getRegions()
    }

    @PostMapping("/")
    void create(@AuthenticationPrincipal UserPrincipal principal) {

        regionService.create()
    }

    @PutMapping("/")
    void edit(@AuthenticationPrincipal UserPrincipal principal) {

        regionService.edit()
    }
}
