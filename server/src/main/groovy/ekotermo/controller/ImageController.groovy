package ekotermo.controller

import ekotermo.dto.CheckDuplicateUserDto
import ekotermo.dto.ImageDto
import ekotermo.dto.UserResponseDto
import ekotermo.service.ImageService
import ekotermo.service.security.model.UserPrincipal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/image")
@Secured(['ROLE_ADMIN', 'ROLE_COMPANY'])
class ImageController {

    @Autowired ImageService imageService

    @GetMapping("/{imageId}")
    Resource findDuplicateUser(@PathVariable String imageId) {

        return imageService.downloadImage(new CheckDuplicateUserDto(name, email, phone))
    }

    @PostMapping("/")
    ImageDto uploadImage(@AuthenticationPrincipal UserPrincipal principal,
                         @RequestParam('scan') MultipartFile multipartImage,
                         @RequestParam('entityId') String entityId) {

        return imageService.uploadImage(principal.userId, entityId, multipartImage)
    }

    @DeleteMapping("/")
    void deleteImage(@AuthenticationPrincipal UserPrincipal principal,
                     @RequestBody List<String> entityIds) {

        imageService.deleteImages(principal.userId, entityIds)
    }
}
