package ekotermo.data.service

import ekotermo.data.domain.AccessCode
import ekotermo.data.domain.CounterSerialNumber
import ekotermo.data.domain.User
import ekotermo.data.enums.AccessCodeStatus
import ekotermo.data.enums.CounterSerialNumberStatus
import ekotermo.data.repository.AccessCodeRepository
import ekotermo.data.repository.CounterSerialNumberRepository
import ekotermo.exceptions.AccessCodeNotFoundException
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class CounterSerialNumberDataService {

    @Autowired private CounterSerialNumberRepository counterSerialNumberRepository

    CounterSerialNumber findByNumberAndStatusWithoutCheck(String number, CounterSerialNumberStatus status){

        log.debug("Find access code by code [${number}]")

        CounterSerialNumber counterSerialNumber = counterSerialNumberRepository.findByNumberAndStatus(number, status)

        return counterSerialNumber
    }

    CounterSerialNumber findByNumberAndStatus(String number, CounterSerialNumberStatus status){

        log.debug("Find access code by code [${number}]")

        CounterSerialNumber counterSerialNumber = counterSerialNumberRepository.findByNumberAndStatus(number, status)

        if(!counterSerialNumber){
            log.debug("Serial number not found by number [$number]")
            throw new AccessCodeNotFoundException("Serial number number [${number}] not found")
        }

        return counterSerialNumber
    }

    void makeDisabled(User user, CounterSerialNumber serialNumber){
        serialNumber.user = user
        serialNumber.status = CounterSerialNumberStatus.DISABLED
        counterSerialNumberRepository.save(serialNumber)
    }

    void saveAll(List<CounterSerialNumber> counterSerialNumbers){
        counterSerialNumberRepository.saveAll(counterSerialNumbers)
    }
}
