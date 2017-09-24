package za.co.henrico.portfolio.exactcoverproblem.dancinglinksimpl;

import za.co.henrico.portfolio.exactcoverproblem.PartialSolutionObject;
import za.co.henrico.portfolio.exactcoverproblem.Solution;

public interface SolutionListener<E extends PartialSolutionObject> {
	void addSolution(Solution<E> solution);
}
