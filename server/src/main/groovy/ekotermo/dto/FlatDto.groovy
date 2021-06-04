package ekotermo.dto

import com.fasterxml.jackson.annotation.JsonProperty
import ekotermo.data.domain.Flat
import groovy.transform.ToString

@ToString(includeFields = true, includeNames = true, includePackage = false)
class FlatDto extends BaseDto{

    @JsonProperty('flatNumber')
    int number
    @JsonProperty('entrance')
    int entranceNumber
    @JsonProperty('floor')
    int floorNumber
    @JsonProperty('counterNum')
    long counterNumber
    @JsonProperty('counterStartVal')
    Double firstDataCounter

    @JsonProperty('cof')
    Double coefficient

//    @JsonProperty('flatNumber')
//    List<Image> images

    @JsonProperty('userId')
    String userId
    @JsonProperty('buildingId')
    String buildingId
    @JsonProperty('flatTypeId')
    String flatTypeId

    static buildFromDomain(Flat flat){

        return new FlatDto(
                number: flat.number,
                entranceNumber: flat.entranceNumber,
                floorNumber: flat.floorNumber,
                counterNumber: flat.counterNumber,
                firstDataCounter: flat.firstDataCounter,
                coefficient: flat.coefficient,
//                images: flat.images,
                userId: flat.userId,
                buildingId: flat.buildingId,
                flatTypeId: flat.flatTypeId
        )
    }
}
