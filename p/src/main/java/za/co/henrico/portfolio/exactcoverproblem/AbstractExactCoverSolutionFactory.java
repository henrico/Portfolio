package za.co.henrico.portfolio.exactcoverproblem;

import java.util.Collection;

/**
 * A factory that creates objects related to a solution for an
 * <a href="https://en.wikipedia.org/wiki/Exact_cover">Exact Cover Problem</a>
 * 
 * @author Henrico.Robinson
 *
 * @param <E>
 *            Domain representation of a partial solution.
 */
public interface AbstractExactCoverSolutionFactory<E extends PartialSolutionObject> {

	/**
	 * Creates and algorithm given a list of all possible partial solutions. Partial
	 * solutions are combined to form an exact solution.
	 * 
	 * @param allPosiblePartialSolutions
	 *            A list of all possible partial solutions.
	 * @param columnCount
	 *            Number of columns making up the partial solution.
	 * @return An Instance of <a href=
	 *         "https://en.wikipedia.org/wiki/Knuth%27s_Algorithm_X">Algorithm X</a>
	 *         to solve for the exact solution.
	 */
	AlgorithmX<E> createAlgorithm(Collection<E> allPosiblePartialSolutions, int columnCount);

}
