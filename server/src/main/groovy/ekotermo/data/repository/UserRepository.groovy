package ekotermo.data.repository

import ekotermo.data.domain.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository extends MongoRepository<User, String>{

    User findByName(String username)
    User findDuplicateUserByNameOrEmailOrPhone(String name, String email, String phone)
}