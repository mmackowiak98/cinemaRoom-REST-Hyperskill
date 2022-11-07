package cinema.repository;

import cinema.model.Cinema;
import cinema.model.Seat;
import cinema.model.Stats;
import cinema.model.Ticket;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

@Repository
public class CinemaRepository {

    private Collection<Seat> collectionOfSeats = new ArrayList<>();
    private Collection<Ticket> collectionOfTickets = new ArrayList<>();
    private Stats stats = new Stats(0,0,0);
    private Cinema cinema = new Cinema(9,9,collectionOfSeats);

    public boolean isAvailable(Seat seat){
        return collectionOfSeats.contains(seat);
    }

    public boolean removeSeat(Seat seat){
        return collectionOfSeats.remove(seat);
    }

    public boolean removeTicket(Ticket ticket){
        return collectionOfTickets.remove(ticket);
    }

    public boolean addSeat(Seat seat){
        return collectionOfSeats.add(seat);
    }

    public boolean addTicket(Ticket ticket){
        return collectionOfTickets.add(ticket);
    }



    public Cinema getCinema(){
        return cinema;
    }



    public Stats getStats() {
        return stats;
    }
    public Collection<Seat> getSeatsCollection(){
        return collectionOfSeats;
    }
    public Collection<Ticket> getCollectionOfTickets() {
        return collectionOfTickets;
    }
}
