package cinema.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Objects;
import java.util.UUID;

public class Ticket {

    private UUID token;
    private Seat ticket;

    public Ticket() {
    }

    public Ticket(UUID token, Seat ticket) {
        this.token =  token;
        this.ticket = ticket;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public Seat getTicket() {
        return ticket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket1 = (Ticket) o;
        return Objects.equals(token, ticket1.token) && Objects.equals(ticket, ticket1.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, ticket);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "token=" + token +
                ", ticket=" + ticket +
                '}';
    }
}
