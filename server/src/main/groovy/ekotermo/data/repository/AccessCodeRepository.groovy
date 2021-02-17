package ekotermo.data.repository

import ekotermo.data.domain.AccessCode
import org.springframework.data.mongodb.repository.MongoRepository

interface AccessCodeRepository extends MongoRepository<AccessCode, String>{

    AccessCode findByCode(String code)
}
