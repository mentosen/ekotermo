package ekotermo.controller

import ekotermo.dto.CompanyDto
import ekotermo.service.CompanyService
import ekotermo.service.security.model.UserPrincipal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/company")
@Secured(['ROLE_ADMIN', 'ROLE_COMPANY'])
class CompanyController {

    @Autowired CompanyService companyService

    @GetMapping("/getCompany")
    CompanyDto findCompanyByUser(@AuthenticationPrincipal UserPrincipal principal) {

        return companyService.findByUserId(principal.userId)
    }

    @PostMapping("/")
    void create(@AuthenticationPrincipal UserPrincipal principal,
                @RequestBody CompanyDto companyDto) {

        companyService.create(principal.userId, companyDto)
    }

    @PutMapping("/")
    void edit(@AuthenticationPrincipal UserPrincipal principal,
              @RequestBody CompanyDto companyDto) {

        companyService.edit(principal.userId, companyDto)
    }
}
