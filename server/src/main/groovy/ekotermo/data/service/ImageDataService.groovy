package ekotermo.data.service

import ekotermo.data.domain.Image
import ekotermo.data.repository.ImageRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class ImageDataService {

    @Autowired private ImageRepository imageRepository

    Image findById(String id){
        return imageRepository.findById(id).get()
    }

    Image save(Image image) {
        return imageRepository.save(image)
    }

    void deleteImages(String userId, List<String> imageId){
        List<Image> images = imageRepository.findAllByIdInAndUserId(imageId, userId)
        imageRepository.deleteAll(images)
    }
}
