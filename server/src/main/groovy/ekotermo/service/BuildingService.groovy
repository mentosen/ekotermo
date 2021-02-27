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

        log.info("Saving building ${buildingDto.region}, ${buildingDto.area}, ${buildingDto.cities}, ${buildingDto.street}, ${buildingDto.buildingNumber}")

        Building building = new Building(
                region: buildingDto.region,
                area: buildingDto.area,
                city: buildingDto.cities,
                street: buildingDto.street,
                buildingNumber: buildingDto.buildingNumber,

                buildingCategory: buildingDto.buildingCategory,

                entranceCount: buildingDto.entranceCount,
                flatsCount: buildingDto.flatsCount,
                floorsCount: buildingDto.floorsCount,
                numerableFlatsFrom: buildingDto.numerableFlatsFrom,
                numerableFlatsTo: buildingDto.numerableFlatsTo,

                generalCounterValue: buildingDto.generalCounterValue.toDouble(),

                firstName: buildingDto.firstName,
                lastName: buildingDto.lastName,
                thirdName: buildingDto.thirdName,
                personPosition: buildingDto.personPosition,
                personPhoneNumFirst: buildingDto.personPhoneNumFirst,
                personPhoneNumSecond: buildingDto.personPhoneNumSecond,

                userId: userId
        )

        buildingDataService.save(building)
    }

    void edit(String userId, BuildingDto buildingDto){

        log.info("Edit building ${buildingDto.region}, ${buildingDto.area}, ${buildingDto.city}, ${buildingDto.street}, ${buildingDto.buildingNumber}")

        Building building = buildingDataService.findByIdAndUserId(buildingDto.id, userId)

        building.region = buildingDto.region
        building.area = buildingDto.area
        building.city = buildingDto.cities
        building.street = buildingDto.street
        building.buildingNumber = buildingDto.buildingNumber

        building.buildingCategory = buildingDto.buildingCategory

        building.entranceCount = buildingDto.entranceCount
        building.flatsCount = buildingDto.flatsCount
        building.floorsCount = buildingDto.floorsCount
        building.numerableFlatsFrom = buildingDto.numerableFlatsFrom
        building.numerableFlatsTo = buildingDto.numerableFlatsTo

        building.generalCounterValue = buildingDto.generalCounterValue.toDouble()

        building.firstName = buildingDto.firstName
        building.lastName = buildingDto.lastName
        building.thirdName = buildingDto.thirdName
        building.personPosition = buildingDto.personPosition
        building.personPhoneNumFirst = buildingDto.personPhoneNumFirst
        building.personPhoneNumSecond = buildingDto.personPhoneNumSecond

        buildingDataService.save(building)
    }

    void delete(){
        //А надо ли физически удалять, это ж готовая инфа ???
    }
}
