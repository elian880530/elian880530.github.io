package Arbol;

import Arbol.*;

public interface Visitor
{
    Object VisitDeclaraciones(Declaraciones declaraciones, Object arg);
    Object VisitGramatica(Gramatica gramatica, Object arg);
    Object VisitCadena(Cadena cadena, Object arg);
    Object VisitProducciones(Producciones producciones, Object arg);
    Object VisitOperaciones(Operaciones operaciones, Object arg);
    Object VisitRead(Read read, Object arg);
    Object VisitClasifica(Clasifica clasifica, Object arg);
    Object VisitSuma(Suma suma, Object arg);
    Object VisitMultiplicacion(Multiplicacion multiplicacion, Object arg);
    Object VisitPertenencia(Pertenencia pertenencia, Object arg);
    Object VisitProd(Prod prod, Object arg);
    
}
