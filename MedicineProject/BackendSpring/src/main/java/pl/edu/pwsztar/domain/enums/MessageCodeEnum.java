package pl.edu.pwsztar.domain.enums;

public enum MessageCodeEnum {
    REGISTRATION_SUCCESS(0),
    EMAIL_IN_USE(1),
    UNEXPECTED_ERROR(2),
    EMAIL_NOT_REGISTERED(3),
    NOT_ACTIVATED_EMAIL(4),
    LOGIN_SUCCESS(5),
    EMAIL_CONFIRMATION_SUCCESS(6),
    EMAIL_CONFIRMATION_ERROR(7);

    private final int value;

    MessageCodeEnum(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
