package ekotermo.exceptions

class NotFoundException extends RuntimeException{

    String localizeMessage

    NotFoundException(String s) {
        super(s)
    }

    NotFoundException(String s, String localizeMessage) {
        super(s)
        this.localizeMessage = localizeMessage
    }

    NotFoundException(String s, Throwable throwable) {
        super(s, throwable)
    }
}
