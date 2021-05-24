package ekotermo.data.repository

import ekotermo.data.domain.FlatType
import org.springframework.data.mongodb.repository.MongoRepository

interface FlatTypeRepository extends MongoRepository<FlatType, String>{

    FlatType findByIdAndUserId(String id, String userId)
    List<FlatType> findByBuildingIdAndUserId(String buildingId, String userId)
    List<FlatType> findByIdInAndUserId(List<String> ids,  String userI)
}
