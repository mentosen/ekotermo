package ekotermo.dto

import ekotermo.data.domain.FlatType
import ekotermo.data.domain.Image
import ekotermo.data.enums.FlatRoomType
import groovy.transform.ToString

@ToString(includeFields = true, includeNames = true, includePackage = false)
class FlatTypeDto extends BaseDto{

    FlatRoomType roomType

    int numberType

    double fullSpace

    double heatingSpace

    List<Image> images

    String buildingId

    static buildFromDomain(FlatType flat){

        return new FlatTypeDto(
                roomType: flat.roomType,
                numberType: flat.numberType,
                fullSpace: flat.fullSpace,
                heatingSpace: flat.heatingSpace,
                images: flat.images,
                buildingId: flat.buildingId
        )
    }
}
