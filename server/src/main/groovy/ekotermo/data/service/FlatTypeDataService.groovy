package ekotermo.data.service

import ekotermo.data.domain.FlatType
import ekotermo.data.repository.FlatTypeRepository
import ekotermo.exceptions.FlatNotFoundException
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class FlatTypeDataService {

    @Autowired private FlatTypeRepository flatRepository

    FlatType findByIdAndUserId(String id, String userId){

        log.debug("Find flat by ID [${id}] and user ID [${userId}]")

        FlatType flat = flatRepository.findByIdAndUserId(id, userId)

        if(!flat){
            log.debug("Flat not found by ID [$id]")
            throw new FlatNotFoundException("Flat ID [${id}] not found")
        }

        return flat
    }

    FlatType save(FlatType flat) {

        log.debug('Saving building')

        return flatRepository.save(flat)
    }
}
