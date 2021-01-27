package ekotermo.controller

import ekotermo.dto.FlatTypeDto
import ekotermo.service.FlatTypeService
import ekotermo.service.security.model.UserPrincipal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/flat-type")
class FlatTypeController {

    @Autowired private FlatTypeService flatTypeService

    @PostMapping("/")
    void create(@AuthenticationPrincipal UserPrincipal principal,
                @RequestBody FlatTypeDto flatTypeDto) {

        flatTypeService.create(principal.userId, flatTypeDto)
    }

    @PutMapping("/")
    void edit(@AuthenticationPrincipal UserPrincipal principal,
              @RequestBody FlatTypeDto flatTypeDto) {

        flatTypeService.edit(principal.userId, flatTypeDto)
    }
}
