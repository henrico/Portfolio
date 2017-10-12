package za.co.henrico.portfolio.colors.model;

import javax.persistence.Entity;
import javax.persistence.Basic;
import org.springframework.data.jpa.domain.AbstractPersistable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Transient;

@Entity
public class Color extends AbstractPersistable<Long>{

	@Basic
	private Integer r,g,b;

	public Color() {
		super();
	}

	public Color(int r,int g, int b) {
		this();
		this.r=r;
		this.g=g;
		this.b=b;
	}

	public Color(double r, double g, double b){
		this((int)(r*255), (int)(g*255), (int)(b*255));
	}

	public Integer getR() {
		return r;
	}

	public void setR(Integer r) {
		this.r = r;
	}

	public Integer getG() {
		return g;
	}

	public void setG(Integer g) {
		this.g = g;
	}

	public Integer getB() {
		return b;
	}

	public void setB(Integer b) {
		this.b = b;
	}

	@JsonIgnore
	@Transient
	public double getNormalizedR(){
		return ((double)r)/255;
	}

	@JsonIgnore
	@Transient
	public double getNormalizedG(){
		return ((double)g)/255;
	}

	@JsonIgnore
	@Transient
	public double getNormalizedB(){
		return ((double)b)/255;
	}
}
