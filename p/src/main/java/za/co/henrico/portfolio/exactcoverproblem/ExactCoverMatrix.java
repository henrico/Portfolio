package za.co.henrico.portfolio.exactcoverproblem;

import java.util.Collection;

/**
 * A matrix used by an implementation of
 * <a href="https://en.wikipedia.org/wiki/Knuth%27s_Algorithm_X">Algorithm X</a>
 * to solve an <a href="https://en.wikipedia.org/wiki/Exact_cover">Exact Cover
 * Problem</a>.
 * 
 * @author Henrico.Robinson
 * 
 * @param <E>
 *            The solution object that is represented by this row.
 */
public interface ExactCoverMatrix<E extends PartialSolutionObject> {

	/**
	 * Checks if the matrix is empty. An empty array indicates a valid solution.
	 * 
	 * @return true if empty, else false.
	 */
	boolean isEmpty();

}
