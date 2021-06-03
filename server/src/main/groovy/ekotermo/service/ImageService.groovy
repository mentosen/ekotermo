package ekotermo.service

import ekotermo.data.domain.FlatType
import ekotermo.data.domain.Image
import ekotermo.data.domain.User
import ekotermo.data.service.FlatTypeDataService
import ekotermo.data.service.ImageDataService
import ekotermo.data.service.UserDataService
import ekotermo.dto.CheckDuplicateUserDto
import ekotermo.dto.CheckDuplicateUserResponseDto
import ekotermo.dto.ImageDto
import ekotermo.dto.RegistrationRequestDto
import ekotermo.dto.UserResponseDto
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ByteArrayResource
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Slf4j
@Service
class ImageService {

    @Autowired private ImageDataService imageDataService
    @Autowired private FlatTypeDataService flatTypeDataService

    ImageDto uploadImage(String userId, String entityId, MultipartFile multipartImage){

        FlatType flatType = flatTypeDataService.findByIdAndUserId(entityId, userId)

        Image image = new Image(
                name: multipartImage.filename,
                content: multipartImage.getBytes(),
                userId: userId,
                entityId: entityId
        )

        image = imageDataService.save(image)

        flatType.image = image
        flatTypeDataService.save(flatType)

        return ImageDto.buildFromDomain(image)
    }

    ByteArrayResource downloadImage(String userId, String flatTypeId, MultipartFile multipartImage){

        byte[] image = imageDataService.findById(imageId).content

        return new ByteArrayResource(image);
    }

    void deleteImages(String userId, List<String> entityIds){
        List<FlatType> flatTypes = flatTypeDataService.findAllByIdsAndUserId(entityIds, userId)

        imageDataService.deleteImages(userId, flatTypes.image.id)

        flatTypes.each {it.image = null}
        flatTypeDataService.saveAll(flatTypes)
    }
}
