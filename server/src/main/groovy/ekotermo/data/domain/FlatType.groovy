package ekotermo.data.domain

import ekotermo.data.enums.FlatRoomType
import groovy.transform.ToString
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = 'flatTypes')
@ToString(includeFields = true, includeNames = true, includePackage = false)
class FlatType extends BaseDomain{

    FlatRoomType roomType

    int numberType

    double fullSpace

    double heatingSpace

    @DBRef(lazy = true)
    Image image

    String userId

    String buildingId
}
