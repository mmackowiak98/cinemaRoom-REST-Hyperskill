package cinema.exceptions;

public class RowOrColumnOutOfBound extends RuntimeException{
    public RowOrColumnOutOfBound() {
        super("The number of a row or a column is out of bounds!");
    }
}
