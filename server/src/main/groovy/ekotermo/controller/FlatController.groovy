package ekotermo.controller

import ekotermo.dto.FlatDto
import ekotermo.service.FlatService
import ekotermo.service.security.model.UserPrincipal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/flat")
class FlatController {

    @Autowired private FlatService flatService

    @PostMapping("/")
    void create(@AuthenticationPrincipal UserPrincipal principal,
                   @RequestBody FlatDto flatDto) {

        flatService.create(principal.userId, flatDto)
    }

    @PutMapping("/")
    void edit(@AuthenticationPrincipal UserPrincipal principal,
                      @RequestBody FlatDto flatDto) {

        flatService.edit(principal.userId, flatDto)
    }
}
