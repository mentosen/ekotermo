package ekotermo.service

import ekotermo.data.domain.Building
import ekotermo.data.service.BuildingDataService
import ekotermo.dto.BuildingDto
import ekotermo.dto.FlatTypeDto
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class BuildingService {

    @Autowired private BuildingDataService buildingDataService

    void create(String userId, BuildingDto buildingDto){

        log.info("Saving building ${buildingDto.region}, ${buildingDto.area}, ${buildingDto.city}, ${buildingDto.street}, ${buildingDto.buildingNumber}")

        Building building = new Building(
                region: buildingDto.region,
                area: buildingDto.area,
                city: buildingDto.city,
                street: buildingDto.street,
                buildingNumber: buildingDto.buildingNumber,

                category: buildingDto.category,

                entranceCount: buildingDto.entranceCount,
                flatCount: buildingDto.flatCount,
                floorCount: buildingDto.floorCount,
                flatMax: buildingDto.flatMax,
                flatMin: buildingDto.flatMin,

                meterings: buildingDto.meterings,

                ownerName: buildingDto.ownerName,
                position: buildingDto.position,
                phone: buildingDto.phone,

                userId: userId
        )

        buildingDataService.save(building)
    }

    void edit(String userId, BuildingDto buildingDto){

        log.info("Edit building ${buildingDto.region}, ${buildingDto.area}, ${buildingDto.city}, ${buildingDto.street}, ${buildingDto.buildingNumber}")

        Building building = buildingDataService.findByIdAndUserId(buildingDto.id, userId)

        buildingDto.region = building.region
        buildingDto.area = building.area
        buildingDto.city = building.city
        buildingDto.street = building.street
        buildingDto.buildingNumber = building.buildingNumber

        buildingDto.category = building.category

        buildingDto.entranceCount = building.entranceCount
        buildingDto.flatCount = building.flatCount
        buildingDto.floorCount = building.floorCount
        buildingDto.flatMax = building.flatMax
        buildingDto.flatMin = building.flatMin

        buildingDto.meterings = building.meterings

        buildingDto.ownerName = building.ownerName
        buildingDto.position = building.position
        buildingDto.phone = building.phone

        buildingDataService.save(building)
    }

    void delete(){
        //А надо ли физически удалять, это ж готовая инфа ???
    }
}
