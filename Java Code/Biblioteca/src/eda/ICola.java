
package eda;

public interface ICola <T>
{
    void Adicionar(T x);
    T Frente()throws Exception;
    T Fondo()throws Exception;
    T Extraer()throws Exception;
    boolean Vacia();
}
