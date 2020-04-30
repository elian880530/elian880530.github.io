package Clases;

import java.io.Serializable;

public class Perro extends Mascota implements Serializable{
	private String raza;
	public Perro(int cantM,String paisP,String colorP,String Cod,String raza){		
		super(cantM,paisP,colorP,Cod);
	this.raza=raza;
	}
	public String getRaza() {
		return raza;
	}
	public void setRaza(String raza) {
		this.raza = raza;
	}
	public double precio(){
		double prec=precios();
		if(raza.equals("chow chow"))
			return prec+30;
		else
		if(raza.equals("dalmata"))
			return prec+50;
		else
			return prec;
	}
}