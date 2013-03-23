package evolalgo.problem.ctrnn;

/**
 * A neuron is a part of the neural network. A neuron generally outputs a value
 * based on an input.
 * @author Odd
 */
public interface INeuron {
    /**
     * The output of the neuron based on its state.
     * @return 
     */
    public double getOutput();
    
    /**
     * Update the internal variable <i>y</i> with the value calculated in 
     * calculateNextY().
     */
    public void updateY();
    
    /**
     * Calculate the new value for y and store internally in the node.
     * @param shadowSensors 
     */
    public void calculateNextY(boolean[] shadowSensors);
    
    /**
     * Connect a neuron to this neuron with an associated weight representing
     * the strength of the connection.
     * @param neuron
     * @param weight 
     */
    public void addArc(INeuron neuron, double weight);
}
