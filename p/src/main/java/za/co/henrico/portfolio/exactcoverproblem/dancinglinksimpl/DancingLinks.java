package za.co.henrico.portfolio.exactcoverproblem.dancinglinksimpl;

import java.util.Collection;
import java.util.LinkedList;

import za.co.henrico.portfolio.exactcoverproblem.AlgorithmX;
import za.co.henrico.portfolio.exactcoverproblem.ExactCoverMatrix;
import za.co.henrico.portfolio.exactcoverproblem.PartialSolutionObject;
import za.co.henrico.portfolio.exactcoverproblem.Solution;
import za.co.henrico.portfolio.exactcoverproblem.SolutionUnsuccessfulException;

/**
 * A <a href="https://en.wikipedia.org/wiki/Dancing_Links">Dancing Links</a>
 * implementation of
 * <a href="https://en.wikipedia.org/wiki/Knuth%27s_Algorithm_X">Algorithm
 * X</a>.
 * 
 * @author Henrico.Robinson
 *
 * @param <E>
 *            Domain representation of a partial solution.
 */
public class DancingLinks<E extends PartialSolutionObject> implements AlgorithmX<E> {

	private DancingLinksMatrix<E> startMatrix;

	/**
	 * Creates the Algorithm from the given starter matrix.
	 * 
	 * @param startMatrix
	 *            The start matrix that represents all possible partial solutions.
	 */
	public DancingLinks(DancingLinksMatrix<E> startMatrix) {
		this.startMatrix = startMatrix;
	}

	/**
	 * {@inheritDoc}
	 */
	public Collection<Solution<E>> getSolution() {
		Collection<Solution<E>> solutions = new LinkedList<Solution<E>>();

		if (startMatrix.isEmpty()) {
			solutions.add(new Solution<E>(startMatrix.getSolution()));
		} else {
			Collection<ExactCoverMatrix<E>> next;
			try {
				next = startMatrix.moveToNextBranch();
			} catch (SolutionUnsuccessfulException e1) {
				return solutions;
			}

			while (!next.isEmpty()) {

				Collection<ExactCoverMatrix<E>> buildList = new LinkedList<ExactCoverMatrix<E>>();

				for (ExactCoverMatrix<E> current : next) {
					if (current.isEmpty() && current.isValid()) {
						solutions.add(new Solution<E>(current.getSolution()));
					} else {
						try {
							buildList.addAll(current.moveToNextBranch());
						} catch (SolutionUnsuccessfulException e) {
						}
					}

				}

				next = buildList;
			}
		}

		return solutions;

	}

}
