package ekotermo.service

import ekotermo.data.domain.Flat
import ekotermo.data.service.FlatDataService
import ekotermo.dto.FlatDto
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class FlatService {

    @Autowired private FlatDataService flatDataService

    void create(String userId, FlatDto flatDto){

        log.info("Saving flat ${flatDto.toString()}")

        Flat flat = new Flat(
                number: flatDto.number,
                entranceNumber: flatDto.entranceNumber,
                floorNumber: flatDto.floorNumber,
                counterNumber: flatDto.counterNumber,
                firstDataCounter: flatDto.firstDataCounter,
                coefficient: flatDto.coefficient,
                userId: userId,
                buildingId: flatDto.buildingId,
                flatTypeId: flatDto.flatTypeId
        )

        flatDataService.save(flat)
    }

    void edit(String userId, FlatDto flatDto){

        log.info("Edit flat ${flatDto.toString()}")

        Flat flat = flatDataService.findByIdAndUserId(flatDto.id, userId)

        flat.number = flatDto.number
        flat.entranceNumber = flatDto.entranceNumber
        flat.floorNumber = flatDto.floorNumber
        flat.counterNumber = flatDto.counterNumber
        flat.firstDataCounter = flatDto.firstDataCounter
        flat.coefficient = flatDto.coefficient

        flatDataService.save(flat)
    }

    void delete(){
        //А надо ли физически удалять, это ж готовая инфа ???
    }
}
