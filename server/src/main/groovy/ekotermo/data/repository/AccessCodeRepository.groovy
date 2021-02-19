package ekotermo.data.repository

import ekotermo.data.domain.AccessCode
import ekotermo.data.enums.AccessCodeStatus
import org.springframework.data.mongodb.repository.MongoRepository

interface AccessCodeRepository extends MongoRepository<AccessCode, String>{

    AccessCode findByCode(String code)
    AccessCode findByCodeAndStatus(String code, AccessCodeStatus status)
}
