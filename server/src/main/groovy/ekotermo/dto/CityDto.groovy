package ekotermo.dto

import ekotermo.data.domain.address.City
import groovy.transform.ToString

@ToString(includeFields = true, includeNames = true, includePackage = false)
class CityDto extends BaseDto{

    String name
    List<String> streets

    static buildFromDomain(City city){

        return new CityDto(
                name: city.name,
                streets: city.streets
        )
    }
}
