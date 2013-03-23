package evolalgo.problem.ctrnn;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represents neurons in the hidden layer. The only difference is how
 * <i>s</i> is calculated; <b>s()</b> accepts an array of shadow sensors that is
 * multiplied by a double[] object representing the weight of each sensor's connection
 * to this neuron.
 * 
 * @author Odd
 */
public class HiddenNeuron extends AbNode implements INeuron{
    
    private double[] sensorWeights;
    
    public HiddenNeuron(double gain, double timeConstant, double bias, double selfWeight, double... sensorWeights){
        this.gain = gain;
        this.timeConstant = timeConstant;
        this.selfWeight = selfWeight;
        this.connections = new ArrayList<Object[]>();
        
        if(sensorWeights.length != 5) try {
            throw new Exception("Wrong amount of weights passed to constructor!");
        } catch (Exception ex) {
            Logger.getLogger(HiddenNeuron.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        this.sensorWeights = sensorWeights;
    }    

    /**
     * This function multiplies each connection's weight with its connected 
     * neuron's output. The shadowSensorInputs is an array representing the
     * input layer; the internal array <i>sensorWeights</i> is multiplied by
     * the output in the corresponding positions.
     * 
     * Since out bias node is abstract we also add that value.
     * @param shadowSensorInputs
     * @return 
     */
    @Override
    double s(boolean[] shadowSensorInputs) {
        double s = 0.0;
        for (int i = 0; i < shadowSensorInputs.length; i++){
            if(shadowSensorInputs[i]) s += 1 * sensorWeights[i];
        }
        for (Object[] connection: connections){
            INeuron node = (INeuron) connection[0];
            double weight = Double.parseDouble(connection[1].toString());
            s += node.getOutput() * weight;
        }
        s += bias;
        
        s += getOutput() * selfWeight;
        return s;
    }
}