package pl.edu.pwsztar.domain.enums;

public enum AcceptingCureEnum {
    ACCEPTING_CURE_IN_TIME(0),
    ACCEPTING_CURE_LATE(1),
    ACCEPTING_CURE_DELAYED(2),
    ACCEPTING_CURE_ACCEPTED(3);


    private final int value;

    AcceptingCureEnum(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
