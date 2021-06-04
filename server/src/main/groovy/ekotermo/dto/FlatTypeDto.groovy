package ekotermo.dto

import com.fasterxml.jackson.annotation.JsonProperty
import ekotermo.data.domain.FlatType
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

    @JsonProperty('image')
    ImageDto image

    @JsonProperty('buildingId')
    String buildingId

    static buildFromDomain(FlatType flatType){

        ImageDto imageDto = null
        if(flatType.image){
            imageDto = ImageDto.buildFromDomain(flatType.image)
        }

        return new FlatTypeDto(
                id: flatType.id,
                roomType: flatType.roomType,
                numberType: flatType.numberType,
                fullSpace: flatType.fullSpace,
                heatingSpace: flatType.heatingSpace,
                image: imageDto,
                buildingId: flatType.buildingId
        )
    }
}
