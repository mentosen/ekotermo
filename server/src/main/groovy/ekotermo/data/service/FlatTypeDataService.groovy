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

    List<FlatType> findAllByIdsAndUserId(List<String> ids, String userId){

        log.debug("Find flat by ids [${ids}] and user ID [${userId}]")

        List<FlatType> flatTypes = flatRepository.findByIdInAndUserId(ids, userId)

        if(!flatTypes){
            log.debug("Flat not found by ID [$ids]")
            return []
        }

        return flatTypes
    }

    List<FlatType> findAllByBuildingIdAndUserId(String buildingId, String userId){

        log.debug("Find flat by buildingId [${buildingId}] and user ID [${userId}]")

        List<FlatType> flatTypes = flatRepository.findByBuildingIdAndUserId(buildingId, userId)

        if(!flatTypes){
            log.debug("Flat not found by buildingId [$buildingId]")
            return []
        }

        return flatTypes
    }

    FlatType save(FlatType flat) {

        log.debug('Saving building')

        return flatRepository.save(flat)
    }

    List<FlatType> saveAll(List<FlatType> flatTypes) {

        log.debug('Saving flatTypes')

        return flatRepository.saveAll(flatTypes)
    }
}
