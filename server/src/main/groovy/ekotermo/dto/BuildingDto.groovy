package ekotermo.dto

import ekotermo.data.domain.Building
import ekotermo.data.enums.BuildingCategory
import groovy.transform.ToString

@ToString(includeFields = true, includeNames = true, includePackage = false)
class BuildingDto extends BaseDto{

    String region
    String area
    String cities
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

    String generalCounterValue

    String firstName
    String lastName
    String thirdName
    String personPosition
    String personPhoneNumFirst
    String personPhoneNumSecond

    static buildFromDomain(Building building){

        return new BuildingDto(
                region: building.region,
                area: building.area,
                cities: building.city,
                street: building.street,
                buildingNumber: building.buildingNumber,

                buildingCategory: building.buildingCategory,

                entranceCount: building.entranceCount,
                flatsCount: building.flatsCount,
                floorsCount: building.floorsCount,
                numerableFlatsFrom: building.numerableFlatsFrom,
                numerableFlatsTo: building.numerableFlatsTo,

                generalCounterValue: building.generalCounterValue.toString(),

                firstName: building.firstName,
                lastName: building.lastName,
                thirdName: building.thirdName,
                personPosition: building.personPosition,
                personPhoneNumFirst: building.personPhoneNumFirst,
                personPhoneNumSecond: building.personPhoneNumSecond
        )
    }
}
