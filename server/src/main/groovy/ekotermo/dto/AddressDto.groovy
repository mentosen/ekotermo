package ekotermo.dto

import ekotermo.data.domain.address.Address
import groovy.transform.ToString

@ToString(includeFields = true, includeNames = true, includePackage = false)
class AddressDto extends BaseDto{

    List<String> regions
    List<String> cities
    List<String> streets

    static buildFromDomain(Address address){

        return new AddressDto(
                regions: address.regions,
                cities: address.cities,
                streets: address.streets,
        )
    }
}
