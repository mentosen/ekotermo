package ekotermo.data.repository

import ekotermo.data.domain.Company
import org.springframework.data.mongodb.repository.MongoRepository

interface CompanyRepository extends MongoRepository<Company, String>{

    Company findByUserId(String userId)
    Company findByIdAndUserId(String id, String userId)
}
