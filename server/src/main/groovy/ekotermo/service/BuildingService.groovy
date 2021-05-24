package ekotermo.service

import ekotermo.data.domain.Building
import ekotermo.data.enums.BuildingStatus
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

    BuildingDto findByIdAndUserId(String buildingId, String userId){

        Building building = buildingDataService.findByIdAndUserId(buildingId, userId)

        return BuildingDto.buildFromDomain(building)
    }

    void create(String userId, BuildingDto buildingDto){

        log.info("Saving building ${buildingDto.region}, ${buildingDto.area}, ${buildingDto.city}, ${buildingDto.street}, ${buildingDto.buildingNumber}")

        Building building = new Building(
                region: buildingDto.region,
                area: buildingDto.area,
                city: buildingDto.city,
                street: buildingDto.street,
                buildingNumber: buildingDto.buildingNumber.toInteger(),

                buildingCategory: buildingDto.buildingCategory,
                status: BuildingStatus.NEW,

                entranceCount: buildingDto.entranceCount.toInteger(),
                flatsCount: buildingDto.flatsCount.toInteger(),
                floorsCount: buildingDto.floorsCount.toInteger(),
                numerableFlatsFrom: buildingDto.numerableFlatsFrom.toInteger(),
                numerableFlatsTo: buildingDto.numerableFlatsTo.toInteger(),

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
        building.city = buildingDto.city
        building.street = buildingDto.street
        building.buildingNumber = buildingDto.buildingNumber

        building.buildingCategory = buildingDto.buildingCategory
        building.status = buildingDto.status

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
