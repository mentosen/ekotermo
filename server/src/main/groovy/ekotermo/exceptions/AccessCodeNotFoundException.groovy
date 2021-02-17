package ekotermo.exceptions

class AccessCodeNotFoundException extends RuntimeException{

    String localizeMessage

    AccessCodeNotFoundException(String s) {
        super(s)
    }

    AccessCodeNotFoundException(String s, String localizeMessage) {
        super(s)
        this.localizeMessage = localizeMessage
    }

    AccessCodeNotFoundException(String s, Throwable throwable) {
        super(s, throwable)
    }
}
