package ekotermo.data.domain

import groovy.transform.ToString
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = 'images')
@ToString(includeFields = true, includeNames = true, includePackage = false)
class Image extends BaseDomain{

    byte[] content

    String name
    String userId
    String entityId
}
