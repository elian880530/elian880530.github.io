package Clases;

import java.io.Serializable;

public abstract class Mascota implements Serializable{
	protected int cantMeses;
	protected String paisProced;
	protected String colorPredom;
	protected String Code;
	public Mascota(int cantM,String paisP,String colorP,String Cod){
		cantMeses=cantM;		
		paisProced=paisP;		
		colorPredom=colorP;	
		Code=Cod;
	}
	public double precios(){
		if(cantMeses<=24)
			return 1000/cantMeses;
	else return 1000/35;
	}
	public int getCantMeses() {
		return cantMeses;
	}
	public void setCantMeses(int cantMeses) {
		this.cantMeses = cantMeses;
	}
	public String getPaisProced() {
		return paisProced;
	}
	public void setPaisProced(String paisProced) {
		this.paisProced = paisProced;
	}
	public String getColorPredom() {
		return colorPredom;
	}
	public void setColorPredom(String colorPredom) {
		this.colorPredom = colorPredom;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
}
