package cinema.model;


import lombok.Value;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Value
public class Cinema {

    int totalRows;
    int totalColumns;
    Collection<Seat> availableSeats;

    public Cinema(int totalRows, int totalColumns, Collection<Seat> availableSeats) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.availableSeats = availableSeats;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public Collection<Seat> getAvailableSeats() {
        return availableSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cinema cinema = (Cinema) o;
        return totalRows == cinema.totalRows && totalColumns == cinema.totalColumns && Objects.equals(availableSeats, cinema.availableSeats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalRows, totalColumns, availableSeats);
    }
}
