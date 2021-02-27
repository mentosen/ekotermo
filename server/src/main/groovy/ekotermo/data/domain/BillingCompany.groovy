package ekotermo.data.domain

import groovy.transform.ToString
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = 'companies')
@ToString(includeFields = true, includeNames = true, includePackage = false)
class BillingCompany extends BaseDomain {

    String name
    String accessCode
    String phone

    @DBRef(lazy = true)
    List<Building> buildings
}
