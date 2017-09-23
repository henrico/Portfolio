package za.co.henrico.portfolio.exactcoverproblem;

import java.util.Collection;

/**
 * An implementation of
 * <a href="https://en.wikipedia.org/wiki/Knuth%27s_Algorithm_X">Algorithm X</a>
 * to calculate all possible solutions for an
 * <a href="https://en.wikipedia.org/wiki/Exact_cover">Exact Cover Problem</a>.
 * 
 * @author Henrico.Robinson
 *
 * @param <E>
 *            Domain representation of a partial solution.
 */
public interface AlgorithmX<E extends PartialSolutionObject> {

	/**
	 * Runs the algorithm and returns all possible solutions. Blocks the current
	 * thread until all solutions have been calculated.
	 * 
	 * @return A collection of all valid solutions. An empty list is returned if
	 *         there are no solutions.
	 */
	Collection<Solution<E>> getSolution();

}
