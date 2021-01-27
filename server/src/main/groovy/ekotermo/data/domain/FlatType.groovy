package ekotermo.data.domain

import ekotermo.data.enums.FlatRoomType
import groovy.transform.ToString
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = 'flatTypes')
@ToString(includeFields = true, includeNames = true, includePackage = false)
class FlatType extends BaseDomain{

    FlatRoomType roomType

    int numberType

    double fullSpace

    double heatingSpace

    List<Image> images

    String userId

    String buildingId
}
