package evolalgo.problem.ctrnn;

import java.util.ArrayList;

/**
 * This class represents neurons in the output-layer; the so-called motors that
 * we use as a basis for determining movement. This is the 'normal' implementation
 * that closely follows the description of how a CTRNN-neuron should work.
 * 
 * <i>s</i> is calculated by multiplying each connected neuron's output with the
 * weight of the connection and the shadow sensor input is ignored.
 * @author Odd
 */
public class MotorNeuron extends AbNeuron implements INeuron{
    
    public MotorNeuron(double gain, double timeConstant, double bias, double selfWeight){
        this.gain = gain;
        this.timeConstant = timeConstant;
        this.bias = bias;
        this.selfWeight = selfWeight;
        this.connections = new ArrayList<Object[]>();
    }

    @Override
    double s(boolean[] shadowInput) {
        double s = 0.0;
        
        for(Object[] connection: connections){
            INeuron node = (INeuron) connection[0];
            double weight = Double.parseDouble(connection[1].toString());
            s += node.getOutput() * weight;
        }
        s += getOutput() * selfWeight;
        s += bias;
        return s;
    }
}