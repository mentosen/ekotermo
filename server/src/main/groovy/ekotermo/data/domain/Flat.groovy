package ekotermo.data.domain

import groovy.transform.ToString
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = 'flats')
@ToString(includeFields = true, includeNames = true, includePackage = false)
class Flat extends BaseDomain{

    int number
    int entranceNumber
    int floorNumber
    long counterNumber
    double firstDataCounter

    double coefficient

    @DBRef(lazy = true)
    List<Image> images

    String userId
    String buildingId
    String flatTypeId
}
