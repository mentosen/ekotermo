package ekotermo.model

import ekotermo.data.domain.User
import groovy.transform.ToString

@ToString(includePackage = false, includeNames = true)
class CredentialViewModel {

    /**
     * JWT access token
     */
    String accessToken

    /**
     *  JWT refresh token
     */
    String refreshToken

    /**
     * ID of user
     */
    String userId

    /**
     * Имя
     */
    String name

    static final CredentialViewModel instance(User user, String accessToken, String refreshToken){
        return new CredentialViewModel(
                accessToken: accessToken,
                refreshToken: refreshToken,
                userId: user.id,
                name: user.name,
        )
    }

}
