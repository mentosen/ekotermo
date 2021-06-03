package ekotermo.dto

import com.fasterxml.jackson.annotation.JsonProperty
import ekotermo.data.domain.FlatType
import ekotermo.data.domain.Image
import groovy.transform.ToString
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = 'images')
@ToString(includeFields = true, includeNames = true, includePackage = false)
class ImageDto extends BaseDto{

    @JsonProperty('content')
    byte[] content

    @JsonProperty('name')
    String name
    @JsonProperty('userId')
    String userId
    @JsonProperty('flatTypeId')
    String entityId

    static buildFromDomain(Image image){

        return new ImageDto(
                id: image.id,
                content: image.content,
                name: image.name,
                userId: image.userId,
                entityId: image.entityId,
        )
    }
}
