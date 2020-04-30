package Clases;

import java.io.Serializable;

public class Gato extends Mascota implements Serializable{
	private String colorOjos;
	private String sexo;
	private boolean pedigri;
	public Gato(int cantM,String paisP,String colorP,String Cod,String colorO,String sex,boolean pedig){
		super(cantM,paisP,colorP,Cod);
		colorOjos=colorO;
		sexo=sex;
		pedigri=pedig;
	}
	public double precio(){
		if(pedigri)
			return super.precios()*2;
		else 
			return super.precios();
	}
	public String getColorOjos() {
		return colorOjos;
	}
	public void setColorOjos(String colorOjos) {
		this.colorOjos = colorOjos;
	}
	public boolean isPedigri() {
		return pedigri;
	}
	public void setPedigri(boolean pedigri) {
		this.pedigri = pedigri;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
}
