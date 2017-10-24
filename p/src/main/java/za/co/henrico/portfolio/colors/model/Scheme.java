package za.co.henrico.portfolio.colors.model;

import java.awt.Color;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import org.neuroph.core.data.DataSetRow;
import org.springframework.data.jpa.domain.AbstractPersistable;
import static java.lang.Math.abs;
import java.util.Iterator;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Scheme extends AbstractPersistable<Long>{

	@OneToMany(cascade = CascadeType.ALL)
	private List<za.co.henrico.portfolio.colors.model.Color> colors;

	public Scheme(){
		super();
	}

	public Scheme(double[] v){
		super();

		colors = new LinkedList<za.co.henrico.portfolio.colors.model.Color>();

		for (int i=0; i<v.length; i+=3) {

			  colors.add(new za.co.henrico.portfolio.colors.model.Color(v[i], v[i+1], v[i+2]));

		  }
	}

	private static Scheme varyN(int n){

		Scheme s = new Scheme();

		int r,g,b;

		Random rand = new Random();


		r = rand.nextInt(255);
		g = rand.nextInt(255);
		b = rand.nextInt(255);

		s.colors = new LinkedList<za.co.henrico.portfolio.colors.model.Color>();

		s.colors.add(new za.co.henrico.portfolio.colors.model.Color(r,g,b));

		float[] hsbVals = new float[3];
		hsbVals = Color.RGBtoHSB(r,g,b,hsbVals);

		int mod = 1;

		if (hsbVals[n]>0.5) {
			  mod = -1;
		  }



		for (int i=0; i<4; i++) {
			  //hsbVals[2] += (1-hsbVals[2])*((rand.nextFloat()/2)*mod);
			  hsbVals[n]+=(0.1*mod);
			  Color cur = Color.getHSBColor(hsbVals[0], hsbVals[1], hsbVals[2]);
			  s.colors.add(new za.co.henrico.portfolio.colors.model.Color(cur.getRed(),cur.getGreen(),cur.getBlue()));
		  }

		orderColorsByRed(s.colors);

		return s;

	}

	public static Scheme createTintsAndShades(){

		return varyN(2);

	}

	public static Scheme createTones(){

		return varyN(1);

	}

	public static Scheme createRandomColorWheel(){

		Scheme s = new Scheme();

		int r,g,b;

		Random rand = new Random();


		r = rand.nextInt(255);
		g = rand.nextInt(255);
		b = rand.nextInt(255);

		s.colors = new LinkedList<za.co.henrico.portfolio.colors.model.Color>();

		s.colors.add(new za.co.henrico.portfolio.colors.model.Color(r,g,b));

		float[] hsbVals = new float[3];
		hsbVals = Color.RGBtoHSB(r,g,b,hsbVals);



		int spacing = rand.nextInt(70) + 2;

		Color left2,left1,right1,right2;

		left2 = Color.getHSBColor(hsbVals[0]-(((float)((rand.nextInt(spacing)+spacing)))/255),hsbVals[1],hsbVals[2]);
		left1 = Color.getHSBColor(hsbVals[0]-(((float)((rand.nextInt(spacing))))/255),hsbVals[1],hsbVals[2]);
		right1 = Color.getHSBColor(hsbVals[0]+(((float)((rand.nextInt(spacing))))/255),hsbVals[1],hsbVals[2]);
		right2 = Color.getHSBColor(hsbVals[0]+(((float)((rand.nextInt(spacing)+spacing)))/255),hsbVals[1],hsbVals[2]);

		if (rand.nextBoolean()) {
			  while(rand.nextBoolean()) {
				    left2 = left2.brighter();
			    }

			  while(rand.nextBoolean()) {
				    left1 = left1.brighter();
			    }

			  while(rand.nextBoolean()) {
				    right1 = right1.brighter();
			    }

			  while(rand.nextBoolean()) {
				    right2 = right2.brighter();
			    }
		  } else {

			  while(rand.nextBoolean()) {
				    left2 = left2.darker();
			    }

			  while(rand.nextBoolean()) {
				    left1 = left1.darker();
			    }

			  while(rand.nextBoolean()) {
				    right1 = right1.darker();
			    }

			  while(rand.nextBoolean()) {
				    right2 = right2.darker();
			    }

		  }

		s.colors.add(new za.co.henrico.portfolio.colors.model.Color(left2.getRed(),left2.getGreen(),left2.getBlue()));
		s.colors.add(new za.co.henrico.portfolio.colors.model.Color(left1.getRed(),left1.getGreen(),left1.getBlue()));
		s.colors.add(new za.co.henrico.portfolio.colors.model.Color(right1.getRed(),right1.getGreen(),right1.getBlue()));
		s.colors.add(new za.co.henrico.portfolio.colors.model.Color(right2.getRed(),right2.getGreen(),right2.getBlue()));

		orderColorsByRed(s.colors);

		return s;

	}

	private static void orderColorsByRed(List<za.co.henrico.portfolio.colors.model.Color> input){
		Collections.sort(input,  new Comparator<za.co.henrico.portfolio.colors.model.Color>(){
		                         public int compare(za.co.henrico.portfolio.colors.model.Color o1, za.co.henrico.portfolio.colors.model.Color o2){
		                                 return o1.getR()-o2.getR();
					 }
				 });
	}

	@JsonIgnore
	@Transient
	private double getRedVariance(){

		double avg = 0;

		Iterator<za.co.henrico.portfolio.colors.model.Color> i = colors.iterator();
		za.co.henrico.portfolio.colors.model.Color prev = i.next();
		while(i.hasNext()) {
			  za.co.henrico.portfolio.colors.model.Color cur = i.next();
			  avg+=(cur.getR()-prev.getR());
		  }

		return (avg/4)/(255);

	}

	@Transient
	@JsonIgnore
	public List<DataSetRow> getDatasetRows(){

		List<DataSetRow> ret = new LinkedList<DataSetRow>();

		double var = getRedVariance();

		double[] output = new double[(3*5)];

		for (int i=0; i<5; i++) {
			  output[i*3] = colors.get(i).getNormalizedR();
			  output[1+(i*3)] = colors.get(i).getNormalizedG();
			  output[2+(i*3)] = colors.get(i).getNormalizedB();
		  }

		for (int i=0; i<5; i++) {

			  double[] input = new double[(1 + (3*5) + 5)];

			  input[0] = var;
			  input[1+(i*4)] = 1;
			  input[2+(i*4)] = colors.get(i).getNormalizedR();
			  input[3+(i*4)] = colors.get(i).getNormalizedG();
			  input[4+(i*4)] = colors.get(i).getNormalizedB();

			  ret.add(new DataSetRow(input,output));

		  }

		return ret;

	}

	public List<za.co.henrico.portfolio.colors.model.Color> getColors() {
		return colors;
	}

	public void setColors(List<za.co.henrico.portfolio.colors.model.Color> colors) {
		this.colors = colors;
	}
}
