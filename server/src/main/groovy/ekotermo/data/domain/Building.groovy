package ekotermo.data.domain

import ekotermo.data.enums.BuildingCategory
import ekotermo.data.enums.BuildingStatus
import groovy.transform.ToString
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = 'buildings')
@ToString(includeFields = true, includeNames = true, includePackage = false)
class Building extends BaseDomain{

    String region
    String area
    String city
    String street

    BuildingCategory buildingCategory
    BuildingStatus status

    int buildingNumber
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

    @DBRef(lazy = true)
    List<FlatType> flatTypes = []

    @DBRef(lazy = true)
    List<Flat> flats = []
}
