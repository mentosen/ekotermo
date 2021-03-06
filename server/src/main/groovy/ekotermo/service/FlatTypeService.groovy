package ekotermo.service

import ekotermo.data.domain.Building
import ekotermo.data.domain.FlatType
import ekotermo.data.service.BuildingDataService
import ekotermo.data.service.FlatTypeDataService
import ekotermo.dto.FlatTypeDto
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class FlatTypeService {

    @Autowired private FlatTypeDataService flatTypeDataService
    @Autowired private BuildingDataService buildingDataService

    List<FlatTypeDto> findAllTypeByBuildingId(String userId, String buildingId){
        List<FlatType> flatTypes = flatTypeDataService.findAllByBuildingIdAndUserId(buildingId, userId)

        return flatTypes.collect{FlatTypeDto.buildFromDomain(it)}
    }

    FlatTypeDto create(String userId, FlatTypeDto flatDto){

        log.info("Saving flat ${flatDto.toString()}")

        Building building = buildingDataService.findByIdAndUserId(flatDto.buildingId, userId)

        FlatType flatType = new FlatType(
                roomType: flatDto.roomType,
                numberType: flatDto.numberType,
                fullSpace: flatDto.fullSpace,
                heatingSpace: flatDto.heatingSpace,
                buildingId: flatDto.buildingId,
                userId: userId
        )

        flatType = flatTypeDataService.save(flatType)

        building.flatTypes.add(flatType)
        buildingDataService.save(building)

        return FlatTypeDto.buildFromDomain(flatType)
    }

    void edit(String userId, List<FlatTypeDto> flatTypeDtos){

        log.info("Edit flatType ${flatTypeDtos.id}")

        List<FlatType> flatTypes = flatTypeDataService.findAllByIdsAndUserId(flatTypeDtos.id, userId)

        FlatTypeDto flatTypeDto
        flatTypes.each {
            flatTypeDto = flatTypeDtos.find {item -> item.id == it.id}
            it.fullSpace = flatTypeDto.fullSpace
            it.heatingSpace = flatTypeDto.heatingSpace
        }

        flatTypeDataService.saveAll(flatTypes)
    }

    void delete(){
        //А надо ли физически удалять, это ж готовая инфа ???
    }
}
