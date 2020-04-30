package grafo;

public abstract class GrafoPonderado<T> extends Grafo<T>{
  //aqui debe ir el metodo modificar peso, pero como no es objetivo del laboratorio
  //pues no se pone ni se implementa como mismo pasa con el resto que falta en la 
  //clase Grafo
  public abstract void insertaArco(Arco<T> a, int peso);
}