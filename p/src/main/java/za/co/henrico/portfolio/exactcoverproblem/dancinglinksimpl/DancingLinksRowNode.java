package za.co.henrico.portfolio.exactcoverproblem.dancinglinksimpl;

/**
 * A doubly linked node used by a row in
 * <a href="https://en.wikipedia.org/wiki/Dancing_Links">Dancing Links</a>
 * implementation of
 * <a href="https://en.wikipedia.org/wiki/Knuth%27s_Algorithm_X">Algorithm
 * X</a>.
 * 
 * @author Henrico.Robinson
 *
 */
class DancingLinksRowNode {

	int column;
	DancingLinksRowNode next;
	DancingLinksRowNode previous;

	/**
	 * Creates the node with the given column number.
	 * 
	 * @param column
	 */
	public DancingLinksRowNode(int column) {
		this.column = column;
	}

	public DancingLinksRowNode() {
	}

}
