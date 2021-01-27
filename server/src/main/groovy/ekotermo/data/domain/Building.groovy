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

    String userId
}
