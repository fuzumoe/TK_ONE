


/**
 *
 * @authors Group C
 *
 */
public class Coordinates {

    //x axis pos cordinate
    private int x;
    // y axis pos  coridinate
    private int y;

    /**
     * Coordinates::construct object with x,y coordinate
     *
     * @param x :: x axis pos coordinate
     * @param y :: y axis pos coordinate
     */
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @return :: get x axis pos coordinate
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @param x :: set x axis pos coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @return :: get y axis pos coordinate
     */
    public int getY() {
        return y;
    }

    /**
     *
     * @param y :: set y axis pos coordinate
     */
    public void setY(int y) {
        this.y = y;
    }
     /**
     * 
     * @return object properties
     */
    @Override
    public String toString(){
        return "x"+x+", y:"+y;
    }
}
