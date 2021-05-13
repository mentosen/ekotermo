package ekotermo.dto

import ekotermo.data.domain.address.Region
import groovy.transform.ToString

@ToString(includeFields = true, includeNames = true, includePackage = false)
class RegionDto extends BaseDto{
    
    String name
    List<CityDto> cities

    static buildFromDomain(Region region){

        RegionDto regionDto = new RegionDto(
                name: region.name
        )

        regionDto.cities = region.cities.collect { CityDto.buildFromDomain(it)}

        return regionDto
    }
}
