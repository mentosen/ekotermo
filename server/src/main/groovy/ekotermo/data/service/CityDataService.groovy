package ekotermo.data.service

import ekotermo.data.domain.address.City
import ekotermo.data.repository.CityRepository
import ekotermo.exceptions.NotFoundException
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class CityDataService {

    @Autowired private CityRepository cityRepository

    List<City> findAll(){

        log.debug("Find City")

        List<City> cities = cityRepository.findAll()

        if(!cities){
            log.debug("City not found")
            throw new NotFoundException("City not found")
        }

        return cities
    }

    City save(City city) {

        log.debug('Saving City')

        return cityRepository.save(city)
    }
}
