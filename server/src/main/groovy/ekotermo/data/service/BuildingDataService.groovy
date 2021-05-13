package ekotermo.data.service

import ekotermo.data.domain.Building
import ekotermo.data.repository.BuildingRepository
import ekotermo.exceptions.BuildingNotFoundException
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class BuildingDataService {

    @Autowired private BuildingRepository buildingRepository

    Building findByIdAndUserId(String id, String userId){

        log.debug("Find building by ID [${id}] and user ID [${userId}]")

        Building building = buildingRepository.findByIdAndUserId(id, userId)

        if(!building){
            log.debug("Not found building by ID [$id]")
            throw new BuildingNotFoundException("Building ID [${id}] not found")
        }

        return building
    }

    List<Building> findAllByUserId(String userId){

        log.debug("Find building by user ID [${userId}]")

        List<Building> buildings = buildingRepository.findAllByUserId(userId)

        if(!buildings){
            log.debug("Not found buildings by userID [$userId]")
            return []
        }

        return buildings
    }

    Building save(Building building) {

        log.debug('Saving building')

        return buildingRepository.save(building)
    }
}
