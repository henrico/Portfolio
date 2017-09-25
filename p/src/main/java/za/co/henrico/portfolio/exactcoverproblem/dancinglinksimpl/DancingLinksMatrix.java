package za.co.henrico.portfolio.exactcoverproblem.dancinglinksimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import za.co.henrico.portfolio.exactcoverproblem.ExactCoverMatrix;
import za.co.henrico.portfolio.exactcoverproblem.ExactCoverRow;
import za.co.henrico.portfolio.exactcoverproblem.PartialSolutionObject;
import za.co.henrico.portfolio.exactcoverproblem.Solution;
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

	private Collection<ExactCoverRow<E>> partialSolutionRows = new LinkedList<ExactCoverRow<E>>();
	private Collection<DancingLinksLinkedRow<E>> removedRows = new LinkedList<DancingLinksLinkedRow<E>>();
	private Collection<DancingLinksLinkedRow<E>> matrixRows = new ArrayList<DancingLinksLinkedRow<E>>();
	private int columnCount;
	private SolutionListener<E> solutionListener;
	private Map<Integer, Boolean> allRemovedColumns = new HashMap<Integer, Boolean>();

	/**
	 * Creates a matrix given a list of rows and the column count.
	 * 
	 * @param exactCoverRows
	 *            The rows that will be in this matrix.
	 * @param columnCount
	 *            The number of columns in this matrix.
	 */
	public DancingLinksMatrix(Collection<DancingLinksLinkedRow<E>> matrixRows, int columnCount) {
		this(columnCount);
		this.matrixRows = matrixRows;
	}

	/**
	 * Creates a matrix given a list of rows, existing partial solution, removed
	 * rows and the column count.
	 * 
	 * @param exactCoverRows
	 *            The rows that will be in this matrix.
	 * @param partialSolutionRows
	 *            The existing partial solution.
	 * @param columnCount
	 *            The number of columns in this matrix.
	 */
	public DancingLinksMatrix(Collection<DancingLinksLinkedRow<E>> matrixRows,
			Collection<ExactCoverRow<E>> partialSolutionRows, Collection<DancingLinksLinkedRow<E>> removedRows,
			Map<Integer, Boolean> allRemovedColumns, int columnCount) {
		this(matrixRows, columnCount);
		this.partialSolutionRows = partialSolutionRows;
		this.removedRows = removedRows;
		this.allRemovedColumns = allRemovedColumns;
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

	public void solve() {

		try {
			int column = findFirstColumnWithLeastAmountOf1s();

			Collection<DancingLinksLinkedRow<E>> solver = new LinkedList<DancingLinksLinkedRow<E>>();

			for (DancingLinksLinkedRow<E> current : matrixRows) {
				solver.add(current);
			}

			for (DancingLinksLinkedRow<E> current : solver) {
				if (current.hasColumn(column)) {
					solveForRow(current);
				}
			}
		} catch (SolutionUnsuccessfulException e) {
		}

	}

	private void solveForRow(DancingLinksLinkedRow<E> row) {

		partialSolutionRows.add(row);

		Map<Integer, Boolean> removedColumns = new HashMap<Integer, Boolean>();
		for (Integer current : row) {
			removedColumns.put(current, true);
			allRemovedColumns.put(current, true);
		}

		Collection<DancingLinksLinkedRow<E>> currentRemovedRows = new LinkedList<DancingLinksLinkedRow<E>>();

		for (Integer column : removedColumns.keySet()) {

			Iterator<DancingLinksLinkedRow<E>> i = matrixRows.iterator();
			while (i.hasNext()) {
				DancingLinksLinkedRow<E> current = i.next();
				if (current.hasColumn(column)) {
					i.remove();
					removedRows.add(current);
					currentRemovedRows.add(current);
				}
			}

		}

		if (matrixRows.isEmpty() && allRemovedColumns.size() == columnCount) {
			solutionListener.addSolution(new Solution<E>(partialSolutionRows));
			System.out.println("Solution");
		} else {
			solve();
		}

		for (DancingLinksLinkedRow<E> current : currentRemovedRows) {
			removedRows.remove(current);
			matrixRows.add(current);
		}

		for (Integer current : removedColumns.keySet()) {
			allRemovedColumns.remove(current);
		}

		partialSolutionRows.remove(row);

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
			if (allRemovedColumns.get(i) == null) {
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

	public void setSolutionListener(SolutionListener<E> solutionListener) {
		this.solutionListener = solutionListener;
	}

}
