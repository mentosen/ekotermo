package ekotermo.exceptions

class FlatNotFoundException extends RuntimeException{

    String localizeMessage

    FlatNotFoundException(String s) {
        super(s)
    }

    FlatNotFoundException(String s, String localizeMessage) {
        super(s)
        this.localizeMessage = localizeMessage
    }

    FlatNotFoundException(String s, Throwable throwable) {
        super(s, throwable)
    }
}
