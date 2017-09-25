package za.co.henrico.portfolio.exactcoverproblem.dancinglinksimpl;

import java.util.Collection;
import java.util.LinkedList;

import za.co.henrico.portfolio.exactcoverproblem.AlgorithmX;
import za.co.henrico.portfolio.exactcoverproblem.ExactCoverMatrix;
import za.co.henrico.portfolio.exactcoverproblem.PartialSolutionObject;
import za.co.henrico.portfolio.exactcoverproblem.Solution;
import za.co.henrico.portfolio.exactcoverproblem.SolutionUnsuccessfulException;

/**
 * A <a href="https://en.wikipedia.org/wiki/Dancing_Links">Dancing Links</a>
 * implementation of
 * <a href="https://en.wikipedia.org/wiki/Knuth%27s_Algorithm_X">Algorithm
 * X</a>.
 * 
 * @author Henrico.Robinson
 *
 * @param <E>
 *            Domain representation of a partial solution.
 */
public class DancingLinks<E extends PartialSolutionObject> implements AlgorithmX<E>, SolutionListener<E> {

	private DancingLinksMatrix<E> startMatrix;
	private Collection<Solution<E>> solutions = new LinkedList<Solution<E>>();

	/**
	 * Creates the Algorithm from the given starter matrix.
	 * 
	 * @param startMatrix
	 *            The start matrix that represents all possible partial solutions.
	 */
	public DancingLinks(DancingLinksMatrix<E> startMatrix) {
		this.startMatrix = startMatrix;
		startMatrix.setSolutionListener(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public Collection<Solution<E>> getSolution() {
		startMatrix.solve();
		return solutions;
	}

	public void addSolution(Solution<E> solution) {
		solutions.add(solution);
	}

}
