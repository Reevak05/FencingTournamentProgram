import java.util.HashMap;

public class Fencer {

    private final int number;
    private final String name;

    public Fencer(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }


    public boolean equals(Fencer otherFencer) {
        return (this.number == otherFencer.getNumber());
    }

    @Override
    public String toString() {
        return "name: " + this.name + ", number: " + this.number;
    }


}
