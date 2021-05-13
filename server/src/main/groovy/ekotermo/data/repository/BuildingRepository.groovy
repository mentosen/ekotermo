package ekotermo.data.repository

import ekotermo.data.domain.Building
import org.springframework.data.mongodb.repository.MongoRepository

interface BuildingRepository extends MongoRepository<Building, String>{

    Building findByIdAndUserId(String id, String userId)
    List<Building> findAllByUserId(String userId)
}
