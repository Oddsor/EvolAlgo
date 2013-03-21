
package evolalgo.problem.ctrnn;

/**
 *
 * @author Oddsor
 */
public interface ITracker {
    /**
     * Based on shadows, move in a direction from -5 to 5.
     * @param shadowSensors
     * @return 
     */
    public int getMovement(boolean[] shadowSensors);
}