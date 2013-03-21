package evolalgo.problem.ctrnn;

/**
 *
 * @author Odd
 */
public class BiasNode implements INode{
    public BiasNode(){
        
    }

    @Override
    public double getOutput() {
        return 1.0;
    }

    @Override
    public void addArc(INode node, double weight) {
        throw new UnsupportedOperationException("Unused, no connections to Bias node");
    }

    @Override
    public void updateY(boolean[] sensorInputs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
