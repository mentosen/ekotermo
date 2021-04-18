package ekotermo.service

import ekotermo.data.domain.Company
import ekotermo.data.domain.User
import ekotermo.data.service.CompanyDataService
import ekotermo.data.service.UserDataService
import ekotermo.dto.CompanyDto
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class CompanyService {

    @Autowired private CompanyDataService companyDataService
    @Autowired private UserDataService userDataService

    CompanyDto findByUserId(String userId) {

        log.info("Find company by userID [${userId}]")

        Company company = companyDataService.findByUserId(userId)

        return CompanyDto.buildFromDomain(company)
    }

    CompanyDto findIdByUserId(String userId) {

        log.info("Find company by userID [${userId}]")

        Company company = companyDataService.findByUserId(userId)

        return CompanyDto.buildSimpleFromDomain(company)
    }

    CompanyDto create(String userId, CompanyDto companyDto){

        log.info("Saving company ${companyDto}")

        User user = userDataService.findById(userId)

        Company company = companyDataService.getByUserId(userId)

        if(!company){
            company = new Company(
                    name: companyDto.name,
                    ipn: companyDto.ipn,

                    regionPost: companyDto.regionPost,
                    cityTypePost: companyDto.cityTypePost,
                    cityNamePost: companyDto.cityNamePost,
                    streetPost: companyDto.streetPost,
                    buildingNumberPost: companyDto.buildingNumberPost,
                    roomNumberPost: companyDto.roomNumberPost,

                    isLegalAndPostAddressSame: companyDto.isLegalAndPostAddressSame,

                    regionLegal: companyDto.regionLegal,
                    cityTypeLegal: companyDto.cityTypeLegal,
                    cityNameLegal: companyDto.cityNameLegal,
                    streetLegal: companyDto.streetLegal,
                    buildingNumberLegal: companyDto.buildingNumberLegal,
                    roomNumberLegal: companyDto.roomNumberLegal,

                    chiefName: companyDto.chiefName,
                    chiefPosition: companyDto.chiefPosition,
                    chiefPhoneNumber: companyDto.chiefPhoneNumber,
                    chiefEmail: companyDto.chiefEmail,

                    bankName: companyDto.bankName,
                    mfo: companyDto.mfo,
                    iban: companyDto.iban,

                    user: user
            )
        }

        company = companyDataService.save(company)

        return CompanyDto.buildFromDomain(company)
    }

    CompanyDto edit(String userId, CompanyDto companyDto){

        log.info("Edit company ${companyDto}")

        Company company = companyDataService.findByIdAndUserId(companyDto.id, userId)

        company.name = companyDto.name
        company.ipn = companyDto.ipn

        company.regionPost = companyDto.regionPost
        company.cityTypePost = companyDto.cityTypePost
        company.cityNamePost = companyDto.cityNamePost
        company.streetPost = companyDto.streetPost
        company.buildingNumberPost = companyDto.buildingNumberPost
        company.roomNumberPost = companyDto.roomNumberPost

        company.isLegalAndPostAddressSame = companyDto.isLegalAndPostAddressSame

        company.regionLegal = companyDto.regionLegal
        company.cityTypeLegal = companyDto.cityTypeLegal
        company.cityNameLegal = companyDto.cityNameLegal
        company.streetLegal = companyDto.streetLegal
        company.buildingNumberLegal = companyDto.buildingNumberLegal
        company.roomNumberLegal = companyDto.roomNumberLegal

        company.chiefName = companyDto.chiefName
        company.chiefPosition = companyDto.chiefPosition
        company.chiefPhoneNumber = companyDto.chiefPhoneNumber
        company.chiefEmail = companyDto.chiefEmail

        company.bankName = companyDto.bankName
        company.mfo = companyDto.mfo
        company.iban = companyDto.iban

        company = companyDataService.save(company)

        return CompanyDto.buildFromDomain(company)
    }

    void delete(){
        //А надо ли физически удалять, это ж готовая инфа ???
    }
}
