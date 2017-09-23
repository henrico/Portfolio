package za.co.henrico.portfolio.exactcoverproblem;

/**
 * A possible partial solution to an
 * <a href="https://en.wikipedia.org/wiki/Exact_cover">Exact Cover Problem</a>.
 * Can be converted to a
 * {@link za.co.henrico.portfolio.exactcoverproblem.ExactCoverRow}. This is
 * essentially a bridge between the domain solution and a the row object
 * represented in the matrix.
 * 
 * @author Henrico.Robinson
 *
 */
public interface PartialSolutionObject extends Cloneable {

	PartialSolutionObject clone();

	/**
	 * This method is used to convert the domain partial solution to a usable format
	 * for an
	 * <a href="https://en.wikipedia.org/wiki/Knuth%27s_Algorithm_X">Algorithm X</a>
	 * Matrix.
	 * 
	 * @return a list of boolean values, true means 1 and false 0 in the <a href=
	 *         "https://en.wikipedia.org/wiki/Knuth%27s_Algorithm_X">Algorithm X</a>
	 *         Matrix.
	 */
	Boolean[] getRow();

}
