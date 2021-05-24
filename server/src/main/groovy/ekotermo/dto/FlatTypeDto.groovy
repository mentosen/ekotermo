package ekotermo.dto

import com.fasterxml.jackson.annotation.JsonProperty
import ekotermo.data.domain.FlatType
import ekotermo.data.domain.Image
import ekotermo.data.enums.FlatRoomType
import groovy.transform.ToString

@ToString(includeFields = true, includeNames = true, includePackage = false)
class FlatTypeDto extends BaseDto{

    @JsonProperty('flatType')
    FlatRoomType roomType

    @JsonProperty('typeShort')
    int numberType

    @JsonProperty('totalArea')
    double fullSpace
    @JsonProperty('heatingArea')
    double heatingSpace

    @JsonProperty('scan')
    List<Image> images

    @JsonProperty('buildingId')
    String buildingId

    static buildFromDomain(FlatType flatType){

        return new FlatTypeDto(
                id: flatType.id,
                roomType: flatType.roomType,
                numberType: flatType.numberType,
                fullSpace: flatType.fullSpace,
                heatingSpace: flatType.heatingSpace,
                images: flatType.images,
                buildingId: flatType.buildingId
        )
    }
}
