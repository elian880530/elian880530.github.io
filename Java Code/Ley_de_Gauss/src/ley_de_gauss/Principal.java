
package ley_de_gauss;

/**
 * Facultad 4 Grupo 4202
 * Elián González Hernández
 * eghernandez@estudiantes.uci.cu
 * Ejercicio de Campo Magnético
 */

public class Principal {

 

    public Principal() {
    }
 

   //Campo magnético con J negativa
        
        public double CMag_J_Negativa(double r,double a,double b,double J)throws Exception{
            double PI=Math.PI;
            double Mo=4*PI*Math.pow(10, -7);
            double B=0;
            if(a==b || r==b ||r==a)  
            throw new Exception("Los radios no pueden ser iguales");
            if(r<a)
            throw new Exception("El radio del campo magnético no puede ser menor que el de la figura ");    
	    else if(a<r && r<b)
			B=-1*((Mo*J*(Math.pow(r,2)-Math.pow(a,2)))/(2*r));
	    else {
			B=-1*((Mo*J*(Math.pow(b,2)-Math.pow(a,2)))/(2*r));
		 } 
	return B;
	}
        
         //Campo magnético con J positiva
        
        public double CMag_J_Positiva(double r,double a,double b,double J)throws Exception{
            double PI=Math.PI;
            double Mo=4*PI*Math.pow(10, -7);
            double B=0;
            if(a==b || r==b ||r==a)  
            throw new Exception("Los radios no pueden ser iguales");
            if(r<a)
            throw new Exception("El radio del campo magnético no puede ser menor que el de la figura ");    
	    else if(a<r && r<b)
			B=(Mo*J*(Math.pow(r,2)-Math.pow(a,2)))/(2*r);
	    else {
			B=(Mo*J*(Math.pow(b,2)-Math.pow(a,2)))/(2*r);
		 } 
	return B;
	}
        
        
         //Campo magnético con I negativa
        
        public double CMag_I_Negativa(double r,double a,double b,double I)throws Exception{
            double PI=Math.PI;
            double Mo=4*PI*Math.pow(10, -7);
            double B=0;
            if(a==b || r==b ||r==a)  
            throw new Exception("Los radios no pueden ser iguales");
            if(r<a)
            throw new Exception("El radio del campo magnético no puede ser menor que el de la figura ");    
	    else if(a<r && r<b)
			B= -1*(I/(2*PI*r));
	    else {
			B=-1*((Mo*I)/(2*PI*r));
		 } 
	return B;
	}
        
          //Campo magnético con I negativa
        
        public double CMag_I_Positiva(double r,double a,double b,double I)throws Exception{
            double PI=Math.PI;
            double Mo=4*PI*Math.pow(10, -7);
            double B=0;
            if(a==b || r==b ||r==a)  
            throw new Exception("Los radios no pueden ser iguales");
            if(r<a)
            throw new Exception("El radio del campo magnético no puede ser menor que el de la figura ");    
	    else if(a<r && r<b)
			B= I/(2*PI*r);
	    else {
			B=(Mo*I)/(2*PI*r);
		 } 
	return B;
	}
        
        //Cilindro cargado superficialmente.
        
        public double Csup(double Zigma,double R,double r)throws Exception{
            double PI=Math.PI;
            double Exilo=8.85*Math.pow(10, -12);
            double E=0;
            if(R==r)
            throw new Exception("Los radios no pueden ser iguales");
	    if(R>r)
			E=0;
	    else {
			E=(Zigma*R)/(Exilo*r);
		 } 
	return E;
	}
        
        //Hilo cargado linealmente con el cilindro cargado volumetricamente.
        
	public double HCvol(double RO,double Landa,double R,double r)throws Exception{
            double PI=Math.PI;
            double Exilo=8.85*Math.pow(10, -12);
            double E=0;
            if(R==r)
            throw new Exception("Los radios no pueden ser iguales");
	    else if(R>r)
			E=(Landa)/(Exilo*2*PI*r);
	    else {
			E=(RO*PI*Math.pow(R,2)+Landa)/(Exilo*2*PI*r);
		 } 
	return E;
	}
	
        //Hilo cargado linealmente con el cilindro cargado superficialmente.
        
	public double HCsup(double Zigma,double R,double r,double Landa)throws Exception{
		double PI=Math.PI;
                double Exilo=8.85*Math.pow(10, -12);
		double E=0;
                if(R==r)
                throw new Exception("Los radios no pueden ser iguales");
                else if(R>r)
			E=(Landa)/(Exilo*2*PI*r);
		else {
			E=(Zigma*2*PI*R+Landa)/(Exilo*2*PI*r);
		     }
	return E;
	}
	
        //Una sola esfera cargada superficialmente.
	
	public double Esup(double Zigma,double R1,double r) throws Exception{
		double PI=Math.PI;
                double Exilo=8.85*Math.pow(10, -12);
		double E=0;
                if(R1==r){
                throw new Exception("Los radios no pueden ser iguales");
                }
                else if(R1>r){
		E=0;
                }
                else{ 
		E=(Zigma*Math.pow(R1,2))/(Exilo*Math.pow(r,2));
                }
	return E;
	}
         
        //Una sola esfera cargada volumetricamente.
	
	public double Evol(double RO1,double R1,double r) throws Exception{
		double PI=Math.PI;
                double Exilo=8.85*Math.pow(10, -12);
		double E=0;
                if(R1==r){
                throw new Exception("Los radios no pueden ser iguales");
                }
                else if(R1>r){
		E=(RO1*(4/3)*PI*r)/(Exilo*PI*4);
                }
                else{ 
		E=(RO1*(4/3)*Math.pow(R1,3))/(Exilo*4*Math.pow(r,2));
                }
	return E;
	}
        
	//Esfera de adentro y afuera cargadas superficialmente.
                
	public double EdentroSup_EfueraSup(double Zigma,double R1,double r,double R2)throws Exception{
		double PI=Math.PI;
                double Exilo=8.85*Math.pow(10, -12);
		double E=0;
                if(R1==r || R2==r || R1==R2){
                throw new Exception("Los radios no pueden ser iguales");
                }
                else if(R1>r){
		E=0;
                }
		else if(R1<r && r<R2){
		E=(Zigma*Math.pow(R1,2))/(Exilo*Math.pow(r,2));
		}
		else {
		E=(Zigma*(Math.pow(R1,2)+Math.pow(R2, 2)))/(Exilo*Math.pow(r,2));
		}
	return E;
	}
        
        //Esfera de adentro y afuera cargadas volumetricamente.
	
	public double EdentroVol_EfueraVol(double RO1,double RO2,double R1,double r,double R2)throws Exception{
		double PI=Math.PI;
                double Exilo=8.85*Math.pow(10, -12);
		double E=0;
                if(R1==r || R2==r || R1==R2)
                        throw new Exception("Los radios no pueden ser iguales");
                if(RO1==RO2)
                        throw new Exception("Las RO no pueden ser iguales");
                else if(R1>r){
			     E=(RO1*(4/3)*PI*Math.pow(r,3))/(Exilo*4*PI*Math.pow(r,2));
                             }
		else if(R1<1 && r<R2){
                                       E=((RO1*(4/3)*PI*Math.pow(R1,3))+(RO2*(4/3)*PI*(Math.pow(r,3)-Math.pow(R1,3))))/(Exilo*4*PI*Math.pow(r,2));
			             }
		else {
		     E=((RO1*(4/3)*PI*Math.pow(R1,3))+(RO2*(4/3)*PI*(Math.pow(R2,3)-Math.pow(R1,3))))/(Exilo*4*PI*Math.pow(r,2));
		     }
	return E;
	}
	
        //Esfera de adentro cargada volumetricamente y la de afuera cargada superficialmente.
        
        public double EdentroVol_EfueraSup(double RO1,double Zigma,double R1,double r,double R2)throws Exception{
		double PI=Math.PI;
                double Exilo=8.85*Math.pow(10, -12);
		double E=0;
                if(R1==r || R2==r || R1==R2)
                throw new Exception("Los radios no pueden ser iguales");
                else if(R1>r){
		E=(RO1*(4/3)*PI*Math.pow(r,3))/(Exilo*4*PI*Math.pow(r,2));
                }
		else if(R1<1 && r<R2){
                E=(RO1*(4/3)*PI*Math.pow(R1,3))/(Exilo*4*PI*Math.pow(r,2));
		}
		else {
		E=((Zigma*4*PI*Math.pow(R2,2))+(RO1*(4/3)*PI*Math.pow(R1,3)))/(Exilo*4*PI*Math.pow(r,2));
		}
	return E;
	}
        
         //Esfera de adentro cargada superficialmente y la de afuera cargada volumetricamente.
        
        public double EdentroSup_EfueraVol(double RO1,double Zigma,double R1,double r,double R2)throws Exception{
		double PI=Math.PI;
                double Exilo=8.85*Math.pow(10, -12);
		double E=0;
                if(R1==r || R2==r || R1==R2)
                throw new Exception("Los radios no pueden ser iguales");
                else if(R1>r){
		E=0;
                }
		else if(R1<1 && r<R2){
                E=(Zigma*4*PI*Math.pow(R1,2))/(Exilo*4*PI*Math.pow(r,2));
		}
		else {
		E=((RO1*(4/3)*PI*(Math.pow(r,3)-Math.pow(R1,3)))+(Zigma*4*PI*Math.pow(R1,2)))/(Exilo*4*PI*Math.pow(r,2));
		}
	return E;
	}

 }



