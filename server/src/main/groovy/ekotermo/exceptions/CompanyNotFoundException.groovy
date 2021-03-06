package ekotermo.exceptions

class CompanyNotFoundException extends RuntimeException{

    String localizeMessage

    CompanyNotFoundException(String s) {
        super(s)
    }

    CompanyNotFoundException(String s, String localizeMessage) {
        super(s)
        this.localizeMessage = localizeMessage
    }

    CompanyNotFoundException(String s, Throwable throwable) {
        super(s, throwable)
    }
}
