package evoalgo.problem.ctrnn.trackerSim;

public interface IEffortAwarder {

	double pointsForEffort(boolean[] sv, int move);
}
