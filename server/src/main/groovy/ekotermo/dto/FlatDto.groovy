package ekotermo.dto

import ekotermo.data.domain.Flat
import ekotermo.data.domain.Image
import groovy.transform.ToString

@ToString(includeFields = true, includeNames = true, includePackage = false)
class FlatDto extends BaseDto{

    int number
    int entranceNumber
    int floorNumber
    int counterNumber
    int firstDataCounter

    double coefficient

    List<Image> images

    String userId
    String buildingId
    String flatTypeId

    static buildFromDomain(Flat flat){

        return new FlatDto(
                number: flat.number,
                entranceNumber: flat.entranceNumber,
                floorNumber: flat.floorNumber,
                counterNumber: flat.counterNumber,
                firstDataCounter: flat.firstDataCounter,
                coefficient: flat.coefficient,
                images: flat.images,
                userId: flat.userId,
                buildingId: flat.buildingId,
                flatTypeId: flat.flatTypeId
        )
    }
}
