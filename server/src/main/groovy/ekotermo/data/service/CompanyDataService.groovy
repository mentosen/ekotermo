package ekotermo.data.service


import ekotermo.data.domain.Company
import ekotermo.data.repository.CompanyRepository
import ekotermo.exceptions.CompanyNotFoundException
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class CompanyDataService {

    @Autowired private CompanyRepository companyRepository

    Company findByUserId(String userId){

        log.debug("Find company by userId [${userId}]")

        Company company = companyRepository.findByUserId(userId)

        if(!company){
            log.debug("Company not found by userId [${userId}]")
            return new Company()
        }

        return company
    }

    Company getByUserId(String userId){

        log.debug("Get company by userId [${userId}]")

        Company company = companyRepository.findByUserId(userId)

        return company
    }

    Company findByIdAndUserId(String id, String userId){

        log.debug("Find company by ID [${id}] by userId [${userId}]")

        Company company = companyRepository.findByIdAndUserId(id, userId)

        if(!company){
            log.debug("Company not found by ID [${id}] by userId [${userId}]")
            throw new CompanyNotFoundException("Company by ID [${id}] by userId [${userId}] not found")
        }

        return company
    }

    Company save(Company company) {

        log.debug('Saving company')

        return companyRepository.save(company)
    }
}
