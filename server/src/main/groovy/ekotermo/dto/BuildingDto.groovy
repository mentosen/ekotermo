package ekotermo.dto

import com.fasterxml.jackson.annotation.JsonProperty
import ekotermo.data.domain.Building
import ekotermo.data.enums.BuildingCategory
import ekotermo.data.enums.BuildingStatus
import groovy.transform.ToString

@ToString(includeFields = true, includeNames = true, includePackage = false)
class BuildingDto extends BaseDto{

    String region
    String area
    String city
    String street
    @JsonProperty('buildingNumber')
    int buildingNumber

    @JsonProperty('buildingCategory')
    BuildingCategory buildingCategory
    @JsonProperty('status')
    BuildingStatus status

    @JsonProperty('entranceCount')
    int entranceCount
    @JsonProperty('flatsCount')
    int flatsCount
    @JsonProperty('floorsCount')
    int floorsCount
    @JsonProperty('numerableFlatsFrom')
    int numerableFlatsFrom
    @JsonProperty('numerableFlatsTo')
    int numerableFlatsTo

    @JsonProperty('isEdit')
    Boolean isEdit
    @JsonProperty('isSaved')
    Boolean isSaved

    @JsonProperty('generalCounterValue')
    Double generalCounterValue

    @JsonProperty('firstName')
    String firstName
    @JsonProperty('lastName')
    String lastName
    @JsonProperty('thirdName')
    String thirdName
    @JsonProperty('personPosition')
    String personPosition
    @JsonProperty('personPhoneNumFirst')
    String personPhoneNumFirst
    @JsonProperty('personPhoneNumSecond')
    String personPhoneNumSecond

    static buildFromDomain(Building building){

        return new BuildingDto(
                region: building.region,
                area: building.area,
                city: building.city,
                street: building.street,
                buildingNumber: building.buildingNumber,

                buildingCategory: building.buildingCategory,
                status: building.status,

                entranceCount: building.entranceCount,
                flatsCount: building.flatsCount,
                floorsCount: building.floorsCount,
                numerableFlatsFrom: building.numerableFlatsFrom,
                numerableFlatsTo: building.numerableFlatsTo,

                generalCounterValue: building.generalCounterValue,

                firstName: building.firstName,
                lastName: building.lastName,
                thirdName: building.thirdName,
                personPosition: building.personPosition,
                personPhoneNumFirst: building.personPhoneNumFirst,
                personPhoneNumSecond: building.personPhoneNumSecond
        )
    }
}
