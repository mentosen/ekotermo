package ekotermo.service

import ekotermo.data.domain.FlatType
import ekotermo.data.service.FlatTypeDataService
import ekotermo.dto.FlatTypeDto
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class FlatTypeService {

    @Autowired private FlatTypeDataService flatTypeDataService

    void create(String userId, FlatTypeDto flatDto){

        log.info("Saving flat ${flatDto.toString()}")

        FlatType flat = new FlatType(
                roomType: flatDto.roomType,
                numberType: flatDto.numberType,
                fullSpace: flatDto.fullSpace,
                heatingSpace: flatDto.heatingSpace,
                buildingId: flatDto.buildingId,
                userId: userId
        )

        flatTypeDataService.save(flat)
    }

    void edit(String userId, FlatTypeDto flatDto){

        log.info("Edit flat ${flatDto.toString()}")

        FlatType flat = flatDataService.findByIdAndUserId(flatDto.id, userId)

        flat.roomType = flatDto.roomType
        flat.numberType = flatDto.numberType
        flat.fullSpace = flatDto.fullSpace
        flat.heatingSpace = flatDto.heatingSpace

        flatTypeDataService.save(flat)
    }

    void delete(){
        //А надо ли физически удалять, это ж готовая инфа ???
    }
}
