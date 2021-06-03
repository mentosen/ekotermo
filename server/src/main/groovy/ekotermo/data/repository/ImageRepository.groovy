package ekotermo.data.repository

import ekotermo.data.domain.Image
import org.springframework.data.mongodb.repository.MongoRepository

interface ImageRepository extends MongoRepository<Image, String>{

    List<Image> findAllByIdInAndUserId(List<String> ids, String userId)
}
