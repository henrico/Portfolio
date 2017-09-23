package za.co.henrico.portfolio.exactcoverproblem.dancinglinksimpl;

import java.util.Collection;
import java.util.LinkedList;

import za.co.henrico.portfolio.exactcoverproblem.AbstractExactCoverSolutionFactory;
import za.co.henrico.portfolio.exactcoverproblem.AlgorithmX;
import za.co.henrico.portfolio.exactcoverproblem.PartialSolutionObject;

/**
 * A factory to create a
 * <a href="https://en.wikipedia.org/wiki/Dancing_Links">Dancing Links</a>
 * implementation of
 * <a href="https://en.wikipedia.org/wiki/Knuth%27s_Algorithm_X">Algorithm
 * X</a>.
 * 
 * @author Henrico.Robinson
 *
 * @param <E>
 *            Domain representation of a partial solution.
 */
public class DancingLinksSolutionFactory<E extends PartialSolutionObject>
		implements AbstractExactCoverSolutionFactory<E> {

	/**
	 * Generates a row for a exact cover matrix, based on the given partial
	 * solution. The partial solution is simply a potential solution and won't
	 * necessarily be part of the final solution.
	 * 
	 * @param partialSolutionObject
	 *            The potential partial solution.
	 * @return A row that will represent this solution in the matrix.
	 */
	private DancingLinksLinkedRow<E> getRowFromSolution(E partialSolutionObject) {

		return new DancingLinksLinkedRow<E>(partialSolutionObject, partialSolutionObject.getRow());
	}

	/**
	 * Creates a matrix from a list of rows. This should be done once all potential
	 * partial solutions have been converted to rows.
	 * 
	 * @param exactCoverRows
	 *            The complete list of potential partial solutions.
	 * @return A starter matrix.
	 */
	private DancingLinksMatrix<E> createMatrixFromRows(Collection<DancingLinksLinkedRow<E>> exactCoverRows,
			int columnCount) {
		return new DancingLinksMatrix<E>(exactCoverRows, columnCount);
	}

	/**
	 * Creates and instance of the Algorithm to solve the complete solution.
	 * 
	 * @param exactCoverMatrix
	 *            The starter matrix consisting of potential partial solutions.
	 * @return An algorithm that will solve for the complete solution.
	 */
	private AlgorithmX<E> createAlgorithmFromMatrix(DancingLinksMatrix<E> exactCoverMatrix) {
		return new DancingLinks<E>(exactCoverMatrix);
	}

	/**
	 * {@inheritDoc}
	 */
	public AlgorithmX<E> createAlgorithm(Collection<E> allPosiblePartialSolutions, int columnCount) {

		Collection<DancingLinksLinkedRow<E>> exactCoverRows = new LinkedList<DancingLinksLinkedRow<E>>();
		for (E current : allPosiblePartialSolutions) {
			exactCoverRows.add(getRowFromSolution(current));
		}

		return createAlgorithmFromMatrix(createMatrixFromRows(exactCoverRows, columnCount));
	}

}
