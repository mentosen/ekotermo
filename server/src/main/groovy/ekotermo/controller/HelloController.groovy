package ekotermo.controller

import ekotermo.data.domain.AccessCode
import ekotermo.data.domain.CounterSerialNumber
import ekotermo.data.enums.AccessCodeStatus
import ekotermo.data.enums.CounterSerialNumberStatus
import ekotermo.data.service.AccessCodeDataService
import ekotermo.data.service.CounterSerialNumberDataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/init")
class HelloController {

    @Autowired CounterSerialNumberDataService counterSerialNumberDataService
    @Autowired AccessCodeDataService accessCodeDataService


    @RequestMapping('/hello')
    public String firstPage() {
        this.fillInCodes()
        return "Hello World";
    }

    void fillInCodes(){
        if(true){
            Random random = new Random()
            List<AccessCode> accessCodes = []
            List<CounterSerialNumber> counterSerialNumbers = []

            for (int i = 0; i < 10; i++) {
                AccessCode accessCode = new AccessCode(
                        code: random.nextInt(1000).toString(),
                        status: AccessCodeStatus.FREE
                )

                accessCodes.add(accessCode)

                CounterSerialNumber counterSerialNumber = new CounterSerialNumber(
                        number: random.nextInt(1000).toString(),
                        status: CounterSerialNumberStatus.FREE
                )

                counterSerialNumbers.add(counterSerialNumber)
            }

            accessCodeDataService.saveAll(accessCodes)
            counterSerialNumberDataService.saveAll(counterSerialNumbers)
        }
    }
}
