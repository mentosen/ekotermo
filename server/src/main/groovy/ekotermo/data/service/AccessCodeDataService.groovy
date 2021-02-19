package ekotermo.data.service

import ekotermo.data.domain.AccessCode
import ekotermo.data.domain.User
import ekotermo.data.enums.AccessCodeStatus
import ekotermo.data.repository.AccessCodeRepository
import ekotermo.exceptions.AccessCodeNotFoundException
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class AccessCodeDataService {

    @Autowired private AccessCodeRepository accessCodeRepository

    AccessCode findByCodeAndStatus(String code, AccessCodeStatus status){

        log.debug("Find access code by code [${code}]")

        AccessCode accessCode = accessCodeRepository.findByCodeAndStatus(code, status)

        if(!accessCode){
            log.debug("Access code not found by code [$code]")
            throw new AccessCodeNotFoundException("Access code code [${code}] not found")
        }

        return accessCode
    }

    void makeDisabled(User user, AccessCode accessCode){
        accessCode.user = user
        accessCode.status = AccessCodeStatus.DISABLED
        accessCodeRepository.save(accessCode)
    }

    void saveAll(List<AccessCode> accessCodes){
        accessCodeRepository.saveAll(accessCodes)
    }
}
