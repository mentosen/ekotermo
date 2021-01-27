package ekotermo.exceptions

class BuildingNotFoundException extends RuntimeException{

    String localizeMessage

    BuildingNotFoundException(String s) {
        super(s)
    }

    BuildingNotFoundException(String s, String localizeMessage) {
        super(s)
        this.localizeMessage = localizeMessage
    }

    BuildingNotFoundException(String s, Throwable throwable) {
        super(s, throwable)
    }
}
