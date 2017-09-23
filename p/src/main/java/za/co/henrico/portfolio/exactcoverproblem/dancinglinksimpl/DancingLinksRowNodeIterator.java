package za.co.henrico.portfolio.exactcoverproblem.dancinglinksimpl;

import java.util.Iterator;

public class DancingLinksRowNodeIterator implements Iterator<Integer> {

	DancingLinksRowNode next;

	DancingLinksRowNodeIterator(DancingLinksRowNode node) {
		next = node;
	}

	public boolean hasNext() {
		return next != null;
	}

	public Integer next() {
		int column = next.column;
		next = next.next;
		return column;
	}

}
