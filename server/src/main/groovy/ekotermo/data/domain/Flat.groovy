package ekotermo.data.domain

import groovy.transform.ToString
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = 'flats')
@ToString(includeFields = true, includeNames = true, includePackage = false)
class Flat extends BaseDomain{

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
}
