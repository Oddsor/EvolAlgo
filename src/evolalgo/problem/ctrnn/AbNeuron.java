package evolalgo.problem.ctrnn;

import java.util.List;

/**
 * This class contains some of the common functions used in both HiddenNode and
 * MotorNode. In practise the only difference between the two is that 
 * HiddenNode-objects contain an abstract representation of the input layer and
 * thus needs to calculate the standard equation <i>s</i> slightly differently.
 * @author Odd Andreas (osorseth@gmail.com)
 */
abstract class AbNeuron {
    double gain;
    double timeConstant;
    double bias;
    double selfWeight;
    
    List<Object[]> connections;
    
    double y = 0.0;
    double nextY = Double.NaN;
    
    /**
     * The simple equation that outputs a derivative of y. This value is added
     * to the y-value.
     * @param s
     * @return 
     */
    double dy(double s){
        return (1 / timeConstant) * (-y + s + bias);
    }
    
    abstract double s(boolean[] sensorInputs);
    
    /**
     * The output <i>o</i> from a neuron.
     * @return 
     */
    public double getOutput(){
    	return 1 / (1 + Math.pow(Math.E, -gain * y));
    }
    
    /**
     * Once we're done with a layer we call this function so that we can get
     * updated values from the neuron. Pulls the new y-value from a stored variable
     */
    public void updateY() {
        if(nextY != Double.NaN){
            y = nextY;
            nextY = Double.NaN;
        }
    }
    
    /**
     * This function is used to temporarily store a new y-value for the next
     * timestep. The idea is that we don't update values that getOutput() depends
     * on before we're ready to move on to the next layer, otherwise we risk
     * a form of synchronization bug where the second neuron in a layer is fed
     * updated outputs from the first neuron.
     * @param sensorInputs 
     */
    public void calculateNextY(boolean[] sensorInputs) {
        nextY = dy(s(sensorInputs));
    }
    
    /**
     * Connects a neuron (node) to this neuron with an associated weight.
     * @param node
     * @param weight 
     */
    public void addArc(INeuron node, double weight) {
        Object[] ob = {node, weight};
        connections.add(ob);
    }
}