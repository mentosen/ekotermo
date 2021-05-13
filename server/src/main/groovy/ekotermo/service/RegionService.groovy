package ekotermo.service

import ekotermo.data.domain.address.Region
import ekotermo.data.service.RegionDataService
import ekotermo.dto.RegionDto
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class RegionService {

    @Autowired private RegionDataService regionDataService

    List<RegionDto> getRegions(){

        List<Region> regions = regionDataService.findAll()

        List<RegionDto> regionDtos = regions.collect{RegionDto.buildFromDomain(it)}

        return regionDtos
    }

    void create(){
    }

    void edit(){
    }
}
