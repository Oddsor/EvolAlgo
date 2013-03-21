package evolalgo.problem.ctrnn;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Odd
 */
public class HiddenNode extends AbNode implements INode{
    
    private double[] sensorWeights;
    
    public HiddenNode(double gain, double timeConstant, double selfWeight, double... sensorWeights){
        this.gain = gain;
        this.timeConstant = timeConstant;
        this.selfWeight = selfWeight;
        this.connections = new ArrayList<Object[]>();
        
        if(sensorWeights.length != 5) try {
            throw new Exception("Wrong amount of weights passed to constructor!");
        } catch (Exception ex) {
            Logger.getLogger(HiddenNode.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        this.sensorWeights = sensorWeights;
    }    
    
    @Override
    public double getOutput() {
        return output();
    }

    @Override
    public void addArc(INode node, double weight) {
        Object[] ob = {node, weight};
        connections.add(ob);
    }

    @Override
    double s(boolean[] sensorInputs) {
        int I = 0;
        for(int i = 0; i < sensorInputs.length; i++){
            if(sensorInputs[i]) I++;
        }
        double s = 0.0;
        s += I;
        for (int i = 0; i < sensorInputs.length; i++){
            if(sensorInputs[i]) s += 1 * sensorWeights[i];
        }
        for (Object[] connection: connections){
            INode node = (INode) connection[0];
            double weight = Double.parseDouble(connection[1].toString());
            s += node.getOutput() * weight;
        }
        
        s += output() * selfWeight;
        return s;
    }

    @Override
    public void updateY(boolean[] sensorInputs) {
        y += dy(s(sensorInputs));
    }
}