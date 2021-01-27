package ekotermo.dto

import ekotermo.data.domain.Building
import ekotermo.data.enums.BuildingCategory
import groovy.transform.ToString

@ToString(includeFields = true, includeNames = true, includePackage = false)
class BuildingDto extends BaseDto{

    String region
    String area
    String city
    String street
    int buildingNumber

    BuildingCategory category

    int entranceCount
    int flatCount
    int floorCount
    int flatMin
    int flatMax

    double meterings

    String ownerName
    String position
    String phone

    static buildFromDomain(Building building){

        return new BuildingDto(
                region: building.region,
                area: building.area,
                city: building.city,
                street: building.street,
                buildingNumber: building.buildingNumber,

                category: building.category,

                entranceCount: building.entranceCount,
                flatCount: building.flatCount,
                floorCount: building.floorCount,
                flatMax: building.flatMax,
                flatMin: building.flatMin,

                meterings: building.metterings,

                ownerName: building.ownerName,
                position: building.position,
                phone: building.phone
        )
    }
}
