package ekotermo.data.domain

import groovy.transform.ToString
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate

@ToString(includeFields = true, includeNames = true, includePackage = false)
class BaseDomain {
    @Id
    String id

    /**
     * Дата создания записи
     */
    @CreatedDate
    Date createDate

    /**
     * Дата обновления записи
     */
    @LastModifiedDate
    Date lastModifiedDate
}
