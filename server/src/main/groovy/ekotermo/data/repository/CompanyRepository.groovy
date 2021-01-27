package ekotermo.data.repository

import ekotermo.data.domain.BillingCompany
import org.springframework.data.mongodb.repository.MongoRepository

interface CompanyRepository extends MongoRepository<BillingCompany, String>{

}