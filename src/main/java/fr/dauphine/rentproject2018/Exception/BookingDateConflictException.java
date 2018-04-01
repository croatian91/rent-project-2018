package fr.dauphine.rentproject2018.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BookingDateConflictException extends RuntimeException {

    public BookingDateConflictException(String message) {
        super(message);
    }
}
