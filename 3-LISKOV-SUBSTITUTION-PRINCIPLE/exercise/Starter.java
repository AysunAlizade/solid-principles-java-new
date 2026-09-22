// LSP Practice Exercise — starter code. Square silently breaks Rectangle's
// contract. Redesign so substituting Square for Rectangle can never surprise
// a caller. See README.md in this folder for the full task and requirements.

class Rectangle {
    protected int width;
    protected int height;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }
}

class Square extends Rectangle {
    @Override
    public void setWidth(int width) {
        this.width = width;
        this.height = width; // forces height to match width
    }

    @Override
    public void setHeight(int height) {
        this.width = height;
        this.height = height;
    }
}

public class Starter {
    public static void main(String[] args) {
        Rectangle rect = new Square();
        rect.setWidth(5);
        rect.setHeight(10);

        // A caller of Rectangle expects independent width/height -> area 50.
        // A Square can't honor that -> area is actually 100.
        System.out.println("Expected 50, got: " + rect.getArea());
    }
}
