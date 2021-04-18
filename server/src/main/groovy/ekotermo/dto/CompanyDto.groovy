package ekotermo.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonProperty
import ekotermo.data.domain.Company
import groovy.transform.ToString

@ToString(includeFields = true, includeNames = true, includePackage = false)
class CompanyDto extends BaseDto{

    @JsonProperty('companyTitle')
    String name
    @JsonProperty('companyCode')
    Integer ipn

    @JsonProperty('postalRegion')
    String regionPost
    @JsonProperty('postalTypeSettlement')
    String cityTypePost
    @JsonProperty('postalCity')
    String cityNamePost
    @JsonProperty('postalStreet')
    String streetPost
    @JsonProperty('postalTypeStreet')
    String streetTypePost
    @JsonProperty('postalBuildingNumber')
    Integer buildingNumberPost
    @JsonProperty('postalRoomNumber')
    Integer roomNumberPost

    @JsonProperty('addressMatch')
    Boolean isLegalAndPostAddressSame

    @JsonProperty('legalRegion')
    String regionLegal
    @JsonProperty('legalTypeSettlement')
    String cityTypeLegal
    @JsonProperty('legalCity')
    String cityNameLegal
    @JsonProperty('legalStreet')
    String streetLegal
    @JsonProperty('legalTypeStreet')
    String streetTypeLegal
    @JsonProperty('legalBuildingNumber')
    Integer buildingNumberLegal
    @JsonProperty('legalRoomNumber')
    Integer roomNumberLegal

    @JsonProperty('initials')
    String chiefName
    @JsonProperty('position')
    String chiefPosition
    @JsonProperty('phoneNumber')
    String chiefPhoneNumber
    @JsonProperty('email')
    String chiefEmail

    @JsonProperty('bankTitle')
    String bankName
    @JsonProperty('bankCode')
    String mfo
    String iban

    static buildFromDomain(Company company){

        return new CompanyDto(
                id:company.id,
                name: company.name,
                ipn: company.ipn,

                regionPost: company.regionPost,
                cityTypePost: company.cityTypePost,
                cityNamePost: company.cityNamePost,
                streetPost: company.streetPost,
                streetTypePost: company.streetTypePost,
                buildingNumberPost: company.buildingNumberPost,
                roomNumberPost: company.roomNumberPost,

                isLegalAndPostAddressSame: company.isLegalAndPostAddressSame,

                regionLegal: company.regionLegal,
                cityTypeLegal: company.cityTypeLegal,
                cityNameLegal: company.cityNameLegal,
                streetLegal: company.streetLegal,
                buildingNumberLegal: company.buildingNumberLegal,
                roomNumberLegal: company.roomNumberLegal,
                streetTypeLegal: company.streetTypeLegal,

                chiefName: company.chiefName,
                chiefPosition: company.chiefPosition,
                chiefPhoneNumber: company.chiefPhoneNumber,
                chiefEmail: company.chiefEmail,

                bankName: company.bankName,
                mfo: company.mfo,
                iban: company.iban,
        )
    }

    static buildSimpleFromDomain(Company company){

        return new CompanyDto(
                id:company.id,
                name: company.name,
        )
    }
}
