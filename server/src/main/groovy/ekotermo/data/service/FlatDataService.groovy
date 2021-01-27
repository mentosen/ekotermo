package ekotermo.data.service

import ekotermo.data.domain.Flat
import ekotermo.data.repository.FlatRepository
import ekotermo.exceptions.FlatNotFoundException
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class FlatDataService {

    @Autowired private FlatRepository flatRepository

    Flat findByIdAndUserId(String id, String userId){

        log.debug("Find flat by ID [${id}] and user ID [${userId}]")

        Flat flat = flatRepository.findByIdAndUserId(id, userId)

        if(!flat){
            log.debug("Flat not found by ID [$id]")
            throw new FlatNotFoundException("Flat ID [${id}] not found")
        }

        return flat
    }

    Flat save(Flat flat) {

        log.debug('Saving building')

        return flatRepository.save(flat)
    }
}
