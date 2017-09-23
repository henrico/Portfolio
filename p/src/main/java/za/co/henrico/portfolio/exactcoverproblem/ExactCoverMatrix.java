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

	/**
	 * Moves this matrix to it's next valid steps.
	 * 
	 * @return Collection of ExactCoverMatrixs that represents the next valid
	 *         branches of this matrix. If the matrix is invalid an empty list is
	 *         returned.
	 * @throws SolutionUnsuccessfulException
	 *             if no solution can be derived from this matrix;
	 */
	Collection<ExactCoverMatrix<E>> moveToNextBranch() throws SolutionUnsuccessfulException;

	/**
	 * Gets the solution for this Matrix, if this matrix isn't the final solution on
	 * it's branch it will retrieve the partial solution.
	 * 
	 * @return A list of rows making up the solution or partial solution.
	 */
	Collection<ExactCoverRow<E>> getSolution();

	boolean isValid();

}
