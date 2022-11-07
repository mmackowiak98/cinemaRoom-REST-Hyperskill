package cinema.service;

import cinema.exceptions.RowOrColumnOutOfBound;
import cinema.exceptions.SeatNotAvailable;
import cinema.exceptions.WrongPasswordException;
import cinema.exceptions.WrongTokenException;
import cinema.model.*;
import cinema.repository.CinemaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CinemaService {

    CinemaRepository cinemaRepository;


    public CinemaService(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    public Cinema getCinema() {
        if (cinemaRepository.getSeatsCollection().isEmpty()) {
            fillCinema();
        }
        return cinemaRepository.getCinema();
    }

    public Stats getStats() {
            return cinemaRepository.getStats();
    }

    public ReturnedTicket returnTicket(UUID token) {
        final Ticket ticket = findTicketByUUID(token);
        cinemaRepository.addSeat(ticket.getTicket());
        cinemaRepository.getStats().setCurrentIncome(cinemaRepository.getStats().getCurrentIncome() - ticket.getTicket().getPrice());
        cinemaRepository.getStats().setNumberOfAvailableSeats(cinemaRepository.getStats().getNumberOfAvailableSeats() + 1);
        cinemaRepository.removeTicket(ticket);
        cinemaRepository.getStats().setNumberOfPurchasedTickets(cinemaRepository.getStats().getNumberOfPurchasedTickets() - 1);
        return new ReturnedTicket(ticket.getTicket());
    }


    public Ticket purchase(Seat seat) {
        if (outOfBounds(seat.getRow(), seat.getColumn())) {
            throw new RowOrColumnOutOfBound();
        }
        if (cinemaRepository.isAvailable(seat)) {
            cinemaRepository.removeSeat(seat);
            cinemaRepository.getStats().setNumberOfAvailableSeats(cinemaRepository.getStats().getNumberOfAvailableSeats() - 1);
            final Seat properSeat = checkPrice(seat);
            cinemaRepository.getStats().setCurrentIncome(cinemaRepository.getStats().getCurrentIncome() + properSeat.getPrice());
            final Ticket ticket = new Ticket(UUID.randomUUID(), properSeat);
            cinemaRepository.addTicket(ticket);
            cinemaRepository.getStats().setNumberOfPurchasedTickets(cinemaRepository.getStats().getNumberOfPurchasedTickets() + 1);
            return ticket;
        }
        throw new SeatNotAvailable();
    }


    private Ticket findTicketByUUID(UUID token) {
        final Ticket ticket = cinemaRepository.getCollectionOfTickets().stream()
                .filter(t -> t.getToken().equals(token))
                .findFirst().orElseThrow(WrongTokenException::new);
        return ticket;
    }

    private void fillCinema() {

        for (int i = 1; i <= cinemaRepository.getCinema().getTotalRows(); i++) {
            for (int j = 1; j <= cinemaRepository.getCinema().getTotalColumns(); j++) {
                cinemaRepository.addSeat(checkPrice(new Seat(i, j)));
            }
        }
        cinemaRepository.getStats().setNumberOfAvailableSeats(cinemaRepository.getSeatsCollection().size());
    }

    private Seat checkPrice(Seat seat) {
        if (seat.getRow() <= 4) {
            seat.setPrice(10);
        } else {
            seat.setPrice(8);
        }
        return seat;
    }


    private boolean outOfBounds(int row, int column) {
        if (row > cinemaRepository.getCinema().getTotalRows() || row < 1 || column > cinemaRepository.getCinema().getTotalColumns() || column < 1) {
            return true;
        }
        return false;
    }

}
