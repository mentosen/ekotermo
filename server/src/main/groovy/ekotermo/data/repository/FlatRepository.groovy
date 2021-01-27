package ekotermo.data.repository

import ekotermo.data.domain.Flat
import org.springframework.data.mongodb.repository.MongoRepository

interface FlatRepository extends MongoRepository<Flat, String>{

    Flat findByIdAndUserId(String id, String userId)
}