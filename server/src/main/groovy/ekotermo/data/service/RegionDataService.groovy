package ekotermo.data.service


import ekotermo.data.domain.address.Region
import ekotermo.data.repository.RegionRepository
import ekotermo.exceptions.NotFoundException
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class RegionDataService {

    @Autowired private RegionRepository regionRepository

    List<Region> findAll(){

        log.debug("Find Region")

        List<Region> regions = regionRepository.findAll()

        if(!regions){
            log.debug("Region not found")
            throw new NotFoundException("Region not found")
        }

        return regions
    }

    Region save(Region region) {

        log.debug('Saving Region')

        return regionRepository.save(region)
    }
}
