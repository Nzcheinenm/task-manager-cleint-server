package ru.t1.dkononov.tm.exception.field;

public final class UserNotFoundException extends AbstractFieldException {

    public UserNotFoundException() {
        super("Error! This User not found...");
    }

}
