package ekotermo.data.domain

import ekotermo.data.enums.BuildingCategory
import groovy.transform.ToString
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = 'buildings')
@ToString(includeFields = true, includeNames = true, includePackage = false)
class Building extends BaseDomain{

    String region
    String area
    String city
    String street
    int buildingNumber

    BuildingCategory buildingCategory

    int entranceCount
    int flatsCount
    int floorsCount
    int numerableFlatsFrom
    int numerableFlatsTo

    Boolean isEdit
    Boolean isSaved

    double generalCounterValue

    String firstName
    String lastName
    String thirdName
    String personPosition
    String personPhoneNumFirst
    String personPhoneNumSecond

    String userId
}
