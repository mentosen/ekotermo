package ekotermo.data.domain

import groovy.transform.ToString
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = 'companies')
@ToString(includeFields = true, includeNames = true, includePackage = false)
class Company extends BaseDomain {

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

    User user

    @DBRef(lazy = true)
    List<Building> buildings
}
