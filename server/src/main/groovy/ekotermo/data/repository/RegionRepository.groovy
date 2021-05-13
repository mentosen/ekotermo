package ekotermo.data.repository

import ekotermo.data.domain.address.Region
import org.springframework.data.mongodb.repository.MongoRepository

interface RegionRepository extends MongoRepository<Region, String>{

}
