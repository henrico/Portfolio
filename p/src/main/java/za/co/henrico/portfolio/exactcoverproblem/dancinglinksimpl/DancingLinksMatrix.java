package za.co.henrico.portfolio.exactcoverproblem.dancinglinksimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import za.co.henrico.portfolio.exactcoverproblem.ExactCoverMatrix;
import za.co.henrico.portfolio.exactcoverproblem.ExactCoverRow;
import za.co.henrico.portfolio.exactcoverproblem.PartialSolutionObject;
import za.co.henrico.portfolio.exactcoverproblem.SolutionUnsuccessfulException;

/**
 * A matrix used in a
 * <a href="https://en.wikipedia.org/wiki/Dancing_Links">Dancing Links</a>
 * implementation of
 * <a href="https://en.wikipedia.org/wiki/Knuth%27s_Algorithm_X">Algorithm
 * X</a>.
 * 
 * @author Henrico Robinson
 *
 * @param <E>
 *            The solution object that is represented by this row.
 */
public final class DancingLinksMatrix<E extends PartialSolutionObject> implements ExactCoverMatrix<E> {

	private Collection<DancingLinksLinkedRow<E>> partialSolutionRows = new LinkedList<DancingLinksLinkedRow<E>>();
	private Collection<DancingLinksLinkedRow<E>> matrixRows = new ArrayList<DancingLinksLinkedRow<E>>();
	private int columnCount;
	private Map<Integer, Boolean> removedColumns = new HashMap<Integer, Boolean>();

	/**
	 * Creates a matrix given a list of rows and the column count.
	 * 
	 * @param exactCoverRows
	 *            The rows that will be in this matrix.
	 * @param columnCount
	 *            The number of columns in this matrix.
	 */
	public DancingLinksMatrix(Collection<DancingLinksLinkedRow<E>> exactCoverRows, int columnCount) {
		this(columnCount);
		for (DancingLinksLinkedRow<E> current : exactCoverRows) {
			matrixRows.add(current.clone());
		}
	}

	/**
	 * Creates a matrix with the given number of columns.
	 * 
	 * @param columnCount
	 *            The number of columns in this matrix.
	 */
	private DancingLinksMatrix(int columnCount) {
		this.columnCount = columnCount;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isEmpty() {
		return matrixRows.size() == 0;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isValid() {
		return removedColumns.keySet().size() == columnCount;
	}

	/**
	 * {@inheritDoc}
	 */
	public Collection<ExactCoverMatrix<E>> moveToNextBranch() throws SolutionUnsuccessfulException {

		int column = findFirstColumnWithLeastAmountOf1s();

		Collection<ExactCoverMatrix<E>> ret = new LinkedList<ExactCoverMatrix<E>>();

		for (DancingLinksLinkedRow<E> current : matrixRows) {
			if (current.hasColumn(column)) {
				ret.add(deriveNewMatrixFromRow(current));
			}
		}

		return ret;

	}

	/**
	 * {@inheritDoc}
	 */
	public Collection<ExactCoverRow<E>> getSolution() {
		Collection<ExactCoverRow<E>> ret = new LinkedList<ExactCoverRow<E>>();
		for (DancingLinksLinkedRow<E> current : partialSolutionRows) {
			ret.add(current.clone());
		}
		return ret;
	}

	/**
	 * Does the calculations to move this matrix into the next step of the
	 * algorithm.
	 * 
	 * @param row
	 *            the row that was selected to add to the solution.
	 * @return a collection of Matrixes that represent possible next steps to
	 *         finding a complete solution.
	 */
	private DancingLinksMatrix<E> deriveNewMatrixFromRow(DancingLinksLinkedRow<E> row) {
		Collection<DancingLinksLinkedRow<E>> newPartialSolutionRows = new LinkedList<DancingLinksLinkedRow<E>>();
		Collection<DancingLinksLinkedRow<E>> newMatrixRows = new LinkedList<DancingLinksLinkedRow<E>>();

		for (DancingLinksLinkedRow<E> current : partialSolutionRows) {
			newPartialSolutionRows.add(current.clone());
		}

		newPartialSolutionRows.add(row.clone());

		addRows: for (DancingLinksLinkedRow<E> current : matrixRows) {
			for (int column : current) {

				for (int removeColumn : row) {

					if (column == removeColumn) {
						continue addRows;
					}

				}

			}
			newMatrixRows.add(current.clone());

		}

		DancingLinksMatrix<E> ret = new DancingLinksMatrix<E>(columnCount);
		ret.matrixRows = newMatrixRows;
		ret.partialSolutionRows = newPartialSolutionRows;

		for (Integer current : removedColumns.keySet()) {
			ret.removedColumns.put(current, true);
		}

		for (Integer column : row) {
			ret.removedColumns.put(column, true);
		}

		return ret;
	}

	/**
	 * Finds the first column that has the least amount of 1's.
	 * 
	 * @return the column number with he least amount of 1'1.
	 * @throws SolutionUnsuccessfulException
	 *             if a column exists that has no 1's.
	 */
	private int findFirstColumnWithLeastAmountOf1s() throws SolutionUnsuccessfulException {
		Map<Integer, Integer> counter = new HashMap<Integer, Integer>();

		for (int i = 0; i < columnCount; i++) {
			if (removedColumns.get(i) == null) {
				counter.put(i, 0);
			}
		}

		for (DancingLinksLinkedRow<E> current : matrixRows) {
			for (int column : current) {

				counter.put(column, counter.get(column) + 1);

			}
		}

		int smallest = -1;

		for (int column : counter.keySet()) {
			if (smallest == -1 || counter.get(column) < counter.get(smallest)) {
				smallest = column;
			}
		}

		if (counter.get(smallest) == 0)
			throw new SolutionUnsuccessfulException();

		return smallest;
	}

}
