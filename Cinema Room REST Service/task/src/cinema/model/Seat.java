package cinema.model;


import java.util.Objects;

public class Seat {

    int row;
    int column;
    public int price;

    public Seat() {
    }

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seats = (Seat) o;
        return row == seats.row && column == seats.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
