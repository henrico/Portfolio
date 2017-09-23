package za.co.henrico.portfolio.exactcoverproblem;

import java.util.Collection;
import java.util.LinkedList;

/**
 * A solution to an <a href="https://en.wikipedia.org/wiki/Exact_cover">Exact
 * Cover Problem</a>. The solution is given as a collection of partial
 * solutions. The solution is immutable.
 * 
 * @author Henrico.Robinson
 *
 * @param <E>
 *            The partial solution specific to the problem domain.
 */
public final class Solution<E extends PartialSolutionObject> {

	private final Collection<E> partialSolutionObjects;

	@SuppressWarnings("unchecked")
	public Solution(Collection<ExactCoverRow<E>> rows) {
		partialSolutionObjects = new LinkedList<E>();
		for (ExactCoverRow<E> row : rows) {
			partialSolutionObjects.add((E) row.getPartialSolutionObject().clone());
		}
	}

	/**
	 * Gets a collection of partial solutions that together form a complete
	 * solution. Returns a new collection with cloned elements.
	 * 
	 * @return the collection of partial solutions.
	 */
	@SuppressWarnings("unchecked")
	public Collection<E> getPartialSolutionObjects() {
		Collection<E> partialSolutionObjects = new LinkedList<E>();
		for (E current : this.partialSolutionObjects) {
			partialSolutionObjects.add((E) current.clone());
		}
		return partialSolutionObjects;
	}

	public String toString() {
		String n = "";
		for (E current : partialSolutionObjects) {
			n += current.toString() + " ,";
		}

		return n;
	}

}
