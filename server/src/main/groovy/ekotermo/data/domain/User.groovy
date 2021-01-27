package ekotermo.data.domain

import ekotermo.data.enums.Role
import groovy.transform.ToString
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = 'users')
@ToString(includeFields = true, includeNames = true, includePackage = false)
class User extends BaseDomain{

    String name
    String password
    String city
    String street
    int building
    int apartment
    String phone
    String email
    String serialNumber

    List<Role> roles = []
}
