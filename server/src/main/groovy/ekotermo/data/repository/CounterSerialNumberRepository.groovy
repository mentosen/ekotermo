package ekotermo.data.repository

import ekotermo.data.domain.CounterSerialNumber
import org.springframework.data.mongodb.repository.MongoRepository

interface CounterSerialNumberRepository extends MongoRepository<CounterSerialNumber, String>{

    CounterSerialNumber findByNumber(String number)
}
