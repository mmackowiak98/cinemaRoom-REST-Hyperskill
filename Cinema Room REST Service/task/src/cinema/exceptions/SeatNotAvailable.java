package cinema.exceptions;

public class SeatNotAvailable extends RuntimeException{
    public SeatNotAvailable() {
        super("The ticket has been already purchased!");
    }
}
