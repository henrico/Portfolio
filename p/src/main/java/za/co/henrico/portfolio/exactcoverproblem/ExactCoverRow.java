package za.co.henrico.portfolio.exactcoverproblem;

/**
 * A row in a matrix used by an implementation of
 * <a href="https://en.wikipedia.org/wiki/Knuth%27s_Algorithm_X">Algorithm X</a>
 * to solve an <a href="https://en.wikipedia.org/wiki/Exact_cover">Exact Cover
 * Problem</a>.
 * 
 * @author Henrico.Robinson
 *
 * @param <E>
 *            The solution object that is represented by this row.
 */
public abstract class ExactCoverRow<E extends PartialSolutionObject> {

	protected E partialSolutionObject;

	/**
	 * Constructor for ExactCoverRow.
	 * 
	 * @param partialSolutionObject
	 *            The object that is represented as a partial solution by this row.
	 */
	public ExactCoverRow(E partialSolutionObject) {
		this.partialSolutionObject = partialSolutionObject;
	}

	/**
	 * Gets the partial solution object represented by this row.
	 * 
	 * @return the represented object.
	 */
	public E getPartialSolutionObject() {
		return partialSolutionObject;
	}

}
