package ekotermo.dto

import ekotermo.data.domain.Company
import groovy.transform.ToString

@ToString(includeFields = true, includeNames = true, includePackage = false)
class CompanyDto extends BaseDto{

    String name
    Integer ipn

    String regionPost
    String cityTypePost
    String cityNamePost
    String streetPost
    Integer buildingNumberPost
    Integer roomNumberPost

    Boolean isLegalAndPostAddressSame

    String regionLegal
    String cityTypeLegal
    String cityNameLegal
    String streetLegal
    Integer buildingNumberLegal
    Integer roomNumberLegal

    String chiefName
    String chiefPosition
    String chiefPhoneNumber
    String chiefEmail

    String bankName
    String mfo
    String iban

    static buildFromDomain(Company company){

        return new CompanyDto(
                name: company.name,
                ipn: company.ipn,

                regionPost: company.regionPost,
                cityTypePost: company.cityTypePost,
                cityNamePost: company.cityNamePost,
                streetPost: company.streetPost,
                buildingNumberPost: company.buildingNumberPost,
                roomNumberPost: company.roomNumberPost,

                isLegalAndPostAddressSame: company.isLegalAndPostAddressSame,

                regionLegal: company.regionLegal,
                cityTypeLegal: company.cityTypeLegal,
                cityNameLegal: company.cityNameLegal,
                streetLegal: company.streetLegal,
                buildingNumberLegal: company.buildingNumberLegal,
                roomNumberLegal: company.roomNumberLegal,

                chiefName: company.chiefName,
                chiefPosition: company.chiefPosition,
                chiefPhoneNumber: company.chiefPhoneNumber,
                chiefEmail: company.chiefEmail,

                bankName: company.bankName,
                mfo: company.mfo,
                iban: company.iban,
        )
    }
}
