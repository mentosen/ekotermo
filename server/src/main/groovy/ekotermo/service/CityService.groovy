package ekotermo.service

import ekotermo.data.domain.address.City
import ekotermo.data.service.CityDataService
import ekotermo.dto.CityDto
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class CityService {

    @Autowired private CityDataService cityDataService

    List<CityDto> getCities(){

        List<City> cities = cityDataService.findAll()

        List<CityDto> cityDtos = cities.collect {CityDto.buildFromDomain(it)}

        return cityDtos
    }

    void create(){
    }

    void edit(){
    }
}
