package za.co.henrico.portfolio.exactcoverproblem.dancinglinksimpl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

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
		implements Iterable<Integer> {

	private DancingLinksRowNode header;
	private DancingLinksRowNode tail;
	private Map<Integer,DancingLinksRowNode> removed = new HashMap<Integer,DancingLinksRowNode>();

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
		
		header = new DancingLinksRowNode(-1);
		tail = new DancingLinksRowNode(-1);

		DancingLinksRowNode node = null;
		DancingLinksRowNode previous = header;

		for (int column = 0; column < columns.length; column++) {
			if (columns[column]) {
				node = new DancingLinksRowNode(column);
				node.previous=previous;
				previous.next=node;
				previous=node;
			}
			
		}
		
		previous.next = tail; 
	}
	
	public void removeColumns(Map<Integer,Boolean> columns) {
		Collection<DancingLinksRowNode> toRemove = new LinkedList<DancingLinksRowNode>();
		DancingLinksRowNode current = header;
		do {
			current = current.next;
			if (columns.get(current.column)!=null) {
				toRemove.add(current);
			}
		} while (current.next!=null);
		
		for (DancingLinksRowNode currentRemove: toRemove) {
			removed.put(currentRemove.column, currentRemove);
			currentRemove.previous.next = currentRemove.next;
			currentRemove.next.previous = current.previous;
		}
	}
	
	public void addBackColumn(int column) {
		DancingLinksRowNode node = removed.get(column);
		node.previous.next=node;
		node.next.previous = node;
		removed.remove(column);
	}

	/**
	 * {@inheritDoc}
	 */
	public Iterator<Integer> iterator() {
		return new DancingLinksRowNodeIterator(header.next);
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
