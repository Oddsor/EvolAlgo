package evolalgo.problem.ctrnn;

import java.util.ArrayList;

/**
 *
 * @author Odd
 */
public class MotorNode extends AbNode implements INode{
    
    public MotorNode(double gain, double timeConstant, double selfWeight){
        this.gain = gain;
        this.timeConstant = timeConstant;
        this.selfWeight = selfWeight;
        this.connections = new ArrayList<Object[]>();
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
        double s = 0.0;
        
        for(Object[] connection: connections){
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