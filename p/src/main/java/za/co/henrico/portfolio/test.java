package za.co.henrico.portfolio;

import java.util.Collection;
import java.util.LinkedList;

import za.co.henrico.portfolio.exactcoverproblem.Solution;
import za.co.henrico.portfolio.exactcoverproblem.dancinglinksimpl.DancingLinksSolutionFactory;

public class test {

	public static void main(String[] args) {

		DancingLinksSolutionFactory<DummySolutionObject> f = new DancingLinksSolutionFactory<DummySolutionObject>();

		Collection<DummySolutionObject> c = new LinkedList<DummySolutionObject>();

		c.add(new DummySolutionObject("A", new Boolean[] { true, false, false, true, false, false, true }));

		c.add(new DummySolutionObject("B", new Boolean[] { true, false, false, true, false, false, false }));

		c.add(new DummySolutionObject("C", new Boolean[] { false, false, false, true, true, false, true }));

		c.add(new DummySolutionObject("D", new Boolean[] { false, false, true, false, true, true, false }));

		c.add(new DummySolutionObject("E", new Boolean[] { false, true, true, false, false, true, true }));

		c.add(new DummySolutionObject("F", new Boolean[] { false, true, false, false, false, false, true }));

		c.add(new DummySolutionObject("G", new Boolean[] { true, true, true, true, false, false, false }));

		c.add(new DummySolutionObject("H", new Boolean[] { false, false, false, false, true, true, true }));

		Collection<Solution<DummySolutionObject>> s = f.createAlgorithm(c, 7).getSolution();

		for (Solution<DummySolutionObject> current : s) {
			System.out.println(current);
		}

	}

}
