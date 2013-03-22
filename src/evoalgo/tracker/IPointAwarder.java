package evoalgo.tracker;

public interface IPointAwarder {

	int awardPoints(boolean[] sv,int o);

	double pointsForEffort(boolean[] sv, int move);
}
