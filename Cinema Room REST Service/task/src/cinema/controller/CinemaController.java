package cinema.controller;

import cinema.exceptions.RowOrColumnOutOfBound;
import cinema.exceptions.SeatNotAvailable;

import cinema.exceptions.WrongPasswordException;
import cinema.exceptions.WrongTokenException;
import cinema.model.*;

import cinema.model.Error;
import cinema.service.CinemaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class CinemaController {

    @Autowired
    CinemaService cinemaService;


    @GetMapping("/seats")
    public Cinema getSeats() {
        return cinemaService.getCinema();
    }

    @PostMapping("/purchase")
    public Ticket purchaseSeat(@RequestBody Seat seat) {
        return cinemaService.purchase(seat);
    }

    @PostMapping("/return")
    public ReturnedTicket returnTicket(@RequestBody Ticket token){
        final UUID properUUID = token.getToken();
        return cinemaService.returnTicket(properUUID);
    }

    @PostMapping("/stats")
    public Stats getStatistics(@RequestParam(name = "password", required = false) String password){
        if(password == null){
            throw new WrongPasswordException();
        }
        if(password.equals("super_secret")){
            return cinemaService.getStats();
        }else {
            throw new WrongPasswordException();
        }
    }

    @ExceptionHandler(SeatNotAvailable.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<Error> seatNotAvailableHandler(SeatNotAvailable e){
        final Error error = new Error(e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
    @ExceptionHandler(RowOrColumnOutOfBound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<Error> outOfBoundHandler(RowOrColumnOutOfBound e){
        final Error error = new Error(e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(WrongTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<Error> WrongTokenExceptionHandler(WrongTokenException e){
        final Error error = new Error(e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(WrongPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ResponseEntity<Error> WrongPasswordExceptionHandler(WrongPasswordException e){
        final Error error = new Error(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

}

