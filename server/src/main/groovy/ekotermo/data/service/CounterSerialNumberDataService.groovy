package ekotermo.data.service

import ekotermo.data.domain.AccessCode
import ekotermo.data.domain.CounterSerialNumber
import ekotermo.data.enums.AccessCodeStatus
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

    CounterSerialNumber findByNumber(String number){

        log.debug("Find access code by code [${number}]")

        CounterSerialNumber counterSerialNumber = counterSerialNumberRepository.findByNumber(number)

        if(!number){
            log.debug("Serial number not found by number [$number]")
            throw new AccessCodeNotFoundException("Serial number number [${number}] not found")
        }

        return counterSerialNumber
    }

    void makeDisabled(CounterSerialNumber serialNumber){
        serialNumber.status = AccessCodeStatus.DISABLED
        counterSerialNumberRepository.save(serialNumber)
    }
}
