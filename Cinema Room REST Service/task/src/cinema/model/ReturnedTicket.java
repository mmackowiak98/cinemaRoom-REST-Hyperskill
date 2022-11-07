package cinema.model;

public class ReturnedTicket {
    Seat returnedTicket;

    public Seat getReturnedTicket() {
        return returnedTicket;
    }

    public ReturnedTicket(Seat seat) {
        this.returnedTicket = seat;
    }
}
