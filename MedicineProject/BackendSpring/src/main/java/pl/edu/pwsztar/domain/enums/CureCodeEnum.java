package pl.edu.pwsztar.domain.enums;

public enum CureCodeEnum {
    CURE_CREATED(0),
    CURE_DELETED(1),
    ADDING_CURE_ERROR(2),
    CURE_ADDED(3),
    CURE_DELETE_ERROR(4);


    private final int value;

    CureCodeEnum(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
