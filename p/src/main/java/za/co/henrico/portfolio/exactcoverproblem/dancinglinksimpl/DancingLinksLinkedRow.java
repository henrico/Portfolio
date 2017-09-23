package za.co.henrico.portfolio.exactcoverproblem.dancinglinksimpl;

import java.util.Iterator;

import za.co.henrico.portfolio.exactcoverproblem.ExactCoverRow;
import za.co.henrico.portfolio.exactcoverproblem.PartialSolutionObject;

/**
 * A double linked row used in a
 * <a href="https://en.wikipedia.org/wiki/Dancing_Links">Dancing Links</a>
 * implementation of
 * <a href="https://en.wikipedia.org/wiki/Knuth%27s_Algorithm_X">Algorithm
 * X</a>.
 * 
 * @author Henrico.Robinson
 *
 * @param <E>
 *            The solution object that is represented by this row.
 */
public final class DancingLinksLinkedRow<E extends PartialSolutionObject> extends ExactCoverRow<E>
		implements Iterable<Integer>, Cloneable {

	private DancingLinksRowNode first;

	/**
	 * {@inheritDoc}
	 */
	private DancingLinksLinkedRow(E partialSolutionObject) {
		super(partialSolutionObject);
	}

	/**
	 * Creates a row from a list of true/false values. True represents a 1 in the
	 * matrix and false a 0.
	 * 
	 * @param partialSolutionObject
	 *            The domain partial solution represented by this row.
	 * @param columns
	 *            The columns that will produce this row.
	 */
	public DancingLinksLinkedRow(E partialSolutionObject, Boolean[] columns) {
		this(partialSolutionObject);

		DancingLinksRowNode node = null;
		DancingLinksRowNode previous = null;

		for (int column = 0; column < columns.length; column++) {
			if (columns[column]) {
				node = new DancingLinksRowNode(column);
				node.previous = previous;
				if (previous != null) {
					previous.next = node;
				} else {
					first = node;
				}
				previous = node;
			}
		}
	}

	/**
	 * Creates a row from an existing first node.
	 * 
	 * @param partialSolutionObject
	 *            The domain partial solution represented by this row.
	 * @param newNode
	 *            The existing first node in this row.
	 */
	private DancingLinksLinkedRow(E partialSolutionObject, DancingLinksRowNode newNode) {
		this(partialSolutionObject);
		first = newNode;
	}

	/**
	 * {@inheritDoc}
	 */
	public Iterator<Integer> iterator() {
		return new DancingLinksRowNodeIterator(first);
	}

	/**
	 * {@inheritDoc}
	 */
	public DancingLinksLinkedRow<E> clone() {
		return new DancingLinksLinkedRow<E>(partialSolutionObject, first.clone(null));
	}

	/**
	 * Check if this row has a 1 in the given column.
	 * 
	 * @param column
	 *            the column to check.
	 * @return true if the column is 1, false if it is 0.
	 */
	boolean hasColumn(int column) {
		for (int current : this) {
			if (current == column) {
				return true;
			}
		}
		return false;
	}

}
