package za.co.henrico.portfolio;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import za.co.henrico.portfolio.exactcoverproblem.Solution;
import za.co.henrico.portfolio.exactcoverproblem.dancinglinksimpl.DancingLinksSolutionFactory;

public class test {

	public static void main(String[] args) {

		DancingLinksSolutionFactory<DummySolutionObject> f = new DancingLinksSolutionFactory<DummySolutionObject>();

		Collection<DummySolutionObject> c = new LinkedList<DummySolutionObject>();

//		c.add(new DummySolutionObject("A", new Boolean[] { true, false, false, true, false, false, true }));
//
//		c.add(new DummySolutionObject("B", new Boolean[] { true, false, false, true, false, false, false }));
//
//		c.add(new DummySolutionObject("C", new Boolean[] { false, false, false, true, true, false, true }));
//
//		c.add(new DummySolutionObject("D", new Boolean[] { false, false, true, false, true, true, false }));
//
//		c.add(new DummySolutionObject("E", new Boolean[] { false, true, true, false, false, true, true }));
//
//		c.add(new DummySolutionObject("F", new Boolean[] { false, true, false, false, false, false, true }));
//
//		c.add(new DummySolutionObject("G", new Boolean[] { true, true, true, true, false, false, false }));
//
//		c.add(new DummySolutionObject("H", new Boolean[] { false, false, false, false, true, true, true }));
		
		for (int r=0;r<8000;r++) {
			Boolean[] m = new Boolean[8000];
			for (int k=0;k<8000;k++) {
				m[k] = new Boolean(r==k);
				if (k>=1 && r==k) {
					m[k-1] = true;
				}
			}
			c.add(new DummySolutionObject(""+r, m));
//			m = new Boolean[9000];
//			for (int k=0;k<9000;k++) {
//				m[k] = new Boolean(r==k);
//			}
//			c.add(new DummySolutionObject("-"+r, m));
			
		}
		
		for (int r=0;r<8000;r++) {
			Boolean[] m = new Boolean[8000];
			for (int k=0;k<8000;k++) {
				m[k] = new Boolean(Math.random()<0.5);
			}
			c.add(new DummySolutionObject(""+r, m));
//			m = new Boolean[9000];
//			for (int k=0;k<9000;k++) {
//				m[k] = new Boolean(r==k);
//			}
//			c.add(new DummySolutionObject("-"+r, m));
			
		}

		System.out.println(new Date());
		Collection<Solution<DummySolutionObject>> s = f.createAlgorithm(c, 8000).getSolution();
		System.out.println(new Date());

		for (Solution<DummySolutionObject> current : s) {
			System.out.println(current);
		}

	}

}
