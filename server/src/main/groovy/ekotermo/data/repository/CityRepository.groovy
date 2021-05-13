package ekotermo.data.repository


import ekotermo.data.domain.address.City
import org.springframework.data.mongodb.repository.MongoRepository

interface CityRepository extends MongoRepository<City, String>{

}
