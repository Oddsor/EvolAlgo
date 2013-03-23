
package evolalgo.problem.ctrnn;

/**
 * A tracker acts on information received from the simulation environment in the
 * form of a defined amount of shadow sensors. Based on the input from these 
 * sensors the tracker moves in a direction.
 * @author Oddsor
 */
public interface ITracker {
    /**
     * Based on input from shadow sensors, move in a direction from -4 to 4.
     * @param shadowSensors
     * @return 
     */
    public int getMovement(boolean[] shadowSensors);
    
    /**
     * How wide is the tracker? Or in other words; how many sensors does it have.
     * @return Number of shadow sensors
     */
    public int getWidth();
}