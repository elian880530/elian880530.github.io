#Librerias

rm(list = ls(all = TRUE))

library(combinat) 
library(datasets) 
library(base)
library(stats)
library(graphics)
library(lattice)
library(MASS)
library(coda)
library(mcsm)
library(ggplot2)


# Multiple plot function

multiplot <- function(..., plotlist=NULL, file, cols=1, layout=NULL) {
  library(grid)
  
  # Make a list from the ... arguments and plotlist
  plots <- c(list(...), plotlist)
  
  numPlots = length(plots)
  
  # If layout is NULL, then use 'cols' to determine layout
  if (is.null(layout)) {
    # Make the panel
    # ncol: Number of columns of plots
    # nrow: Number of rows needed, calculated from # of cols
    layout <- matrix(seq(1, cols * ceiling(numPlots/cols)),
                     ncol = cols, nrow = ceiling(numPlots/cols))
  }
  
  if (numPlots==1) {
    print(plots[[1]])
    
  } else {
    # Set up the page
    grid.newpage()
    pushViewport(viewport(layout = grid.layout(nrow(layout), ncol(layout))))
    
    # Make each plot, in the correct location
    for (i in 1:numPlots) {
      # Get the i,j matrix positions of the regions that contain this subplot
      matchidx <- as.data.frame(which(layout == i, arr.ind = TRUE))
      
      print(plots[[i]], vp = viewport(layout.pos.row = matchidx$row,
                                      layout.pos.col = matchidx$col))
    }
  }
}


#########################################################################################################
######################Hoja 1 Ejercicio 1. Monte Carlos Con Gotas de Lluvia ##############################
#########################################################################################################

#--------------------------------------------------------------------------------------------------------
#Inciso a) N=100 ----------------------------------------------------------------------------------------
#--------------------------------------------------------------------------------------------------------

## Para N = 100: Donde N es el numero de punto que voy a crear
# Se escoge el intervalo -1,1 pq es elque esta en el ejemplo de clases
sample.x.100 <- runif(100,-1,1) # Genera aleatoriamente la uniforme
sample.y.100 <- runif(100,-1,1) # The same

accept = c()

# Esta es la formula para crear la circuferencia que me servira de frontera
F = function(x,y){x^2+y^2} # La función de la circumferencia de centro 0,0 y radio unidad f(x) = x^2+y^2

# El objetivo de este for ver los puntos que no cumplan con esta condicion la f(x)= x^2 + y^2 = 1  osea los = 1 
# estan encima de la frontera y se aceptan, los menores que 1 estan dentro de la frontera y se aceptan
# y los mayores que 1 estan fuera de la circunferencia y se rechazan

for(i in 1:length(sample.x.100)){ # Para cada elemento de la muestra:
  if(F(sample.x.100[i],sample.y.100[i]) <= 1){ # Si la imagen de esta función está dentro del circulo:
    accept[i] = 1 # Lo aceptamos, le damos el valor 1
  } 
  else if(F(sample.x.100[i],sample.y.100[i]) > 1){ # Si no, caen fuera:
    accept[i] = 0 # Por lo tanto, no las aceptamos
  }
}

# Calculando la probabilidad de que una gota de agua este dentro del circulo donde 
# P= (area del circulo/area del cuadrado)
phat.100 = sum(accept)/100 #Esto es p
phat.100 # La probabilidad de que caiga dentro es de 0.79 

#En el paso de arriba calculamos probabilidad pero el ejercicio me pide calcular pi
#Sabemos que p = pi/4 pq se utiliza una circunferencia de radio 1 y un cuadrado de longitud 2
#Por lo tanto despejando pi = 4 * p
Zhat.100 = 4*sum(accept)/100 # 4 * el número de gotas dentro de la circumferencia (los 0 no afectan), y los dividimos por los 100 elementos generados
Zhat.100 # Nos da 3.16, no se acerca mucho a pi

#--------------------------------------------------------------------------------------------------------
#Inciso b) N=500 ----------------------------------------------------------------------------------------
#--------------------------------------------------------------------------------------------------------

## Para N = 500:
sample.x.500 <- runif(500,-1,1) # Genera aleatoriamente la uniforme
sample.y.500 <- runif(500,-1,1) # The same

accept = c()

F = function(x,y){x^2+y^2} # La función de la circumferencia de centro 0,0 y radio unidad f(x) = x^2+y^2

for(i in 1:length(sample.x.500)){ # Para cada elemento de la muestra:
  if(F(sample.x.500[i],sample.y.500[i]) <= 1){ # Si la imagen de esta función está dentro del círculo:
    accept[i] = 1 # Lo aceptamos, le damos el valor 1
  } 
  else if(F(sample.x.500[i],sample.y.500[i]) > 1){ # Si no, caen fuera:
    accept[i] = 0 # Por lo tanto, no las aceptamos
  }
}

Zhat.500 = 4*sum(accept)/500 # 4 * el número de gotas dentro de la circumferencia (los 0 no afectan), y los dividimos por los 100 elementos generados
Zhat.500 # Nos da 3.064

phat.500 = sum(accept)/500
phat.500 # La probabilidad de que caiga dentro es de 0.766

#---------------------------------------------------------------------------------------------------------
#Inciso c) N=1000 ----------------------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------------

## Para N = 1000:
sample.x.1000 <- runif(1000,-1,1) # Genera aleatoriamente la uniforme
sample.y.1000 <- runif(1000,-1,1) # The same

accept = c()

F = function(x,y){x^2+y^2} # La función de la circumferencia de centro 0,0 y radio unidad f(x) = x^2+y^2

for(i in 1:length(sample.x.1000)){ # Para cada elemento de la muestra:
  if(F(sample.x.1000[i],sample.y.1000[i]) <= 1){ # Si la imagen de esta función está dentro del círculo:
    accept[i] = 1 # Lo aceptamos, le damos el valor 1
  } 
  else if(F(sample.x.1000[i],sample.y.1000[i]) > 1){ # Si no, caen fuera:
    accept[i] = 0 # Por lo tanto, no las aceptamos
  }
}

Zhat.1000 = 4*sum(accept)/1000 # 4 * el número de gotas dentro de la circumferencia (los 0 no afectan), y los dividimos por los 100 elementos generados
Zhat.1000 # Nos da 3.136

phat.1000 = sum(accept)/1000
phat.1000 # La probabilidad de que caiga dentro es de 0.784


#----------------------------------------------------------------------------------------------------------------
#Inciso a) Calculamos los IC = 90% en N=100  --------------------------------------------------------------------
#----------------------------------------------------------------------------------------------------------------


# En la formula del IC lo unico que hay que calcular aqui es el Z sub 1 - alfa/2 esto es mi qnorm 
#(la formula completa esta en la diapo con pagina 30)

# IC = 90%
## Alpha = 0.1 (10%) -> alpha/2 = 0.05
qnorm(0.05)   ##its inverse (q, since the result is a quantile). Esta es la inversa de la normal, seria el valor z_0.25, que es -1.64
qnorm(1-0.05) ## Lo mismo, pero al del otro lado, 1.64
#Con todo esto calculamos la z de la formula ahora queda el resto

# Para N = 100:
# Calculamos el Intervalo de confianza para la probabilidad
CI.90.100 = c(phat.100 + qnorm(0.05)*sqrt(phat.100*(1-phat.100)/100), phat.100 + qnorm(1-0.05)*sqrt(phat.100*(1-phat.100)/100))
CI.90.100

# El intervalo de confianza para la pi
CIpi.90.100 = CI.90.100*4 
CIpi.90.100

#----------------------------------------------------------------------------------------------------------------
#Inciso b) Calculamos los IC = 90% en N=500 ---------------------------------------------------------------------
#----------------------------------------------------------------------------------------------------------------

# Para N = 500:
CI.90.500 = c(phat.500 + qnorm(0.05)*sqrt(phat.500*(1-phat.500)/500), phat.500 + qnorm(1-0.05)*sqrt(phat.500*(1-phat.500)/500)) # Calculamos el Intervalo de confianza para la probabilidad
CI.90.500

CIpi.90.500 = CI.90.500*4 # El intervalo de confianza para la pi
CIpi.90.500

#----------------------------------------------------------------------------------------------------------------
#Inciso c) Calculamos los IC = 90% en N=1000 --------------------------------------------------------------------
#----------------------------------------------------------------------------------------------------------------

# Para N = 1000:
CI.90.1000 = c(phat.1000 + qnorm(0.05)*sqrt(phat.1000*(1-phat.1000)/1000), phat.1000 + qnorm(1-0.05)*sqrt(phat.1000*(1-phat.1000)/1000)) # Calculamos el Intervalo de confianza para la probabilidad
CI.90.1000

CIpi.90.1000 = CI.90.1000*4 # El intervalo de confianza para la pi
CIpi.90.1000

#----------------------------------------------------------------------------------------------------------------
#Inciso a) Calculamos los IC = 95% en N=100 ---------------------------------------------------------------------
#----------------------------------------------------------------------------------------------------------------

# Para N = 100:
## Alpha = 0.05 (5% -> 5/100) -> alpha/2 = 0.025
qnorm(0.025)   ##its inverse (q, since the result is a quantile). Esta es la inversa de la normal, sería el valor z_0.25, que es -1.64
qnorm(1-0.025) ## Lo mismo, pero al del otro lado, 1.64

# Para N = 100:
# En esta formula calcula solo el intervalo de confianza solo para p
CI.95.100 = c(phat.100 + qnorm(0.025)*sqrt(phat.100*(1-phat.100)/100), phat.100 + qnorm(1-0.025)*sqrt(phat.100*(1-phat.100)/100)) # Calculamos el Intervalo de confianza para la probabilidad
CI.95.100

# En esta formula solo calculo el IC de pi al multiplicar * 4 para calcular el IC ya que pi es 4 * p como calculamos anteriormente
# En este ejercicio como especificaron trabajar con pi pues se calcula primero la p y luego se obtiene la pi.
CIpi.95.100 = CI.95.100*4 # El intervalo de confianza para la pi
CIpi.95.100

#----------------------------------------------------------------------------------------------------------------
#Inciso b) Calculamos los IC = 95% en N=500 ---------------------------------------------------------------------
#----------------------------------------------------------------------------------------------------------------

# Para N = 500:
CI.95.500 = c(phat.500 + qnorm(0.025)*sqrt(phat.500*(1-phat.500)/500), phat.500 + qnorm(1-0.025)*sqrt(phat.500*(1-phat.500)/500)) # Calculamos el Intervalo de confianza para la probabilidad
CI.95.500

CIpi.95.500 = CI.95.500*4 # El intervalo de confianza para la pi
CIpi.95.500

#----------------------------------------------------------------------------------------------------------------
#Inciso c) Calculamos los IC = 95% en N=1000 --------------------------------------------------------------------
#----------------------------------------------------------------------------------------------------------------

# Para N = 1000:
CI.95.1000 = c(phat.1000 + qnorm(0.025)*sqrt(phat.1000*(1-phat.1000)/1000), phat.1000 + qnorm(1-0.025)*sqrt(phat.1000*(1-phat.1000)/1000)) # Calculamos el Intervalo de confianza para la probabilidad
CI.95.1000

CIpi.95.1000 = CI.95.1000*4 # El intervalo de confianza para la pi
CIpi.95.1000

#----------------------------------------------------------------------------------------------------------------
#Inciso a) Calculamos los IC = 99% en N=100 ---------------------------------------------------------------------
#----------------------------------------------------------------------------------------------------------------

# IC = 99%
## Alpha = 0.01 (1% -> 1/100) -> alpha/2 = 0.005
qnorm(0.005)   ##its inverse (q, since the result is a quantile). Esta es la inversa de la normal
qnorm(1-0.005) ## Lo mismo, pero al otro lado

# Para N = 100:
CI.99.100 = c(phat.100 + qnorm(0.005)*sqrt(phat.100*(1-phat.100)/100), phat.100 + qnorm(1-0.005)*sqrt(phat.100*(1-phat.100)/100)) # Calculamos el Intervalo de confianza para la probabilidad
CI.99.100

CIpi.99.100 = CI.99.100*4 # El intervalo de confianza para la pi
CIpi.99.100

#----------------------------------------------------------------------------------------------------------------
#Inciso b) Calculamos los IC = 99% en N=500 ---------------------------------------------------------------------
#----------------------------------------------------------------------------------------------------------------

# Para N = 500:
CI.99.500 = c(phat.500 + qnorm(0.005)*sqrt(phat.500*(1-phat.500)/500), phat.500 + qnorm(1-0.005)*sqrt(phat.500*(1-phat.500)/500)) # Calculamos el Intervalo de confianza para la probabilidad
CI.99.500

CIpi.99.500 = CI.99.500*4 # El intervalo de confianza para la pi
CIpi.99.500

#----------------------------------------------------------------------------------------------------------------
#Inciso c) Calculamos los IC = 99% en N=1000 --------------------------------------------------------------------
#----------------------------------------------------------------------------------------------------------------

# Para N = 1000:
CI.99.1000 = c(phat.1000 + qnorm(0.005)*sqrt(phat.1000*(1-phat.1000)/1000), phat.1000 + qnorm(1-0.005)*sqrt(phat.1000*(1-phat.1000)/1000)) # Calculamos el Intervalo de confianza para la probabilidad
CI.99.1000

CIpi.99.1000 = CI.99.1000*4 # El intervalo de confianza para la pi
CIpi.99.1000

#----------------------------------------------------------------------------------------------------------------
#Vizualidando todos los valores----------------------------------------------------------------------------------
#----------------------------------------------------------------------------------------------------------------

## Matriz para visualizarlo todo:
result.100 = cbind(CIpi.90.100[1],CIpi.90.100[2], CIpi.95.100[1], CIpi.95.100[2], CIpi.99.100[1], CIpi.99.100[2])
result.500 = cbind(CIpi.90.500[1],CIpi.90.500[2], CIpi.95.500[1], CIpi.95.500[2], CIpi.99.500[1], CIpi.99.500[2])
result.1000 = cbind(CIpi.90.1000[1],CIpi.90.1000[2], CIpi.95.1000[1], CIpi.95.1000[2], CIpi.99.1000[1], CIpi.99.1000[2])
Salida = rbind(result.100, result.500, result.1000)
rownames(Salida) = c("N = 100", "N = 500", "N = 1000")
colnames(Salida) = c("Min. 90%", "Max. 90%", "Min. 95%", "Max. 95%", "Min. 99%", "Max. 99%")
Salida


#########################################################################################################
###################### Hoja 1 Ejercicio 3. Distribucion Inversa #########################################
#########################################################################################################

# En este ejercico se utiliza el metodo de distribucion inversa que no es mas que
# encontrar la inversa del las funciones que te piden y aplicarla sobre la uniforme con intervalo [0,1]

# Nsim es la cantidad de punto que quiero simular, en este caso decidi escoger 10^5 
# pero siempre debo trabajar con numeros grandes pq puede salir una basura
Nsim = 10^6 # Numero de variables
# Aqui como en el ejercicio 1 generamos una muestra uniforme para todos los puntos de Nsim utilizando runif() 
# y si no se le especifican intervalos el escoge por defeco[0,1]
U = runif(Nsim) # Cogemos la funcion uniforme de 10^5 elementos

#----------------------------------------------------------------------------------------------------------------
#Inciso a) Exponencial con Parametros Lambda = 0.5 --------------------------------------------------------------
#----------------------------------------------------------------------------------------------------------------

# Primero tengo que buscar en www.wolframalpha.com esto: exponential distribution lambda = 0.5 
# Escogemos la que dice Función de distribución acumulativa (CDF):
# y este me devuelve la formula de su exponencial: 1-e^(-0.5 x) 
#Ahora buscamos la inversa y esta la escribimos como: solve[U=1-e^(-0.5 x)] 
# esto es para obtener la inversa en funcion de U. El resultado que se debe observar es el que que dice solucion real y si no 
# aparece escogemos el result pero sin el numero imaginario osea lo que contiene i. 
# Obtenemos x = 2 log (1 / (1 - U))  y lo que debemos utilizar en R es: 2*log(1/(1-U))

X.05 = 2*log(1/(1-U)) #Con esto ya esta finalizado el ejercicio pq ya esta calculada la inversa pero 
#compruebo que esta correctocomparando lo que salio con lo que saca R

# Con esta formula compruebo con R
Y.05 = rexp(Nsim,0.5) # Generamos aleatoriamente la variable Y mediante la exponencial.
#Es decir, esto sería el resultado real que tendría que salir


# Este sera mediante la funcion inversa
pg2 <- ggplot() + aes(X.05)+ geom_density(colour="blue", fill="lightblue") +
  ggtitle("Exp from Uniform")
print(pg2)

# Este sera mediante la distribucion exponencial real:
pg <- ggplot() + aes(Y.05)+ geom_density(colour="blue", fill="lightblue") +
  ggtitle("Exp from R")
print(pg)

# Aqui lo dibuja todo junto
multiplot(pg, pg2, cols=2)


#----------------------------------------------------------------------------------------------------------------
#Inciso a) Exponencial con Parametros Lambda = 1 ----------------------------------------------------------------
#----------------------------------------------------------------------------------------------------------------

# Primero tengo que buscar en www.wolframalpha.com esto: exponential distribution lambda = 1
# Escogemos la que dice Función de distribución acumulativa (CDF):
# y este me devuelve la formula de su exponencial: 1-e^(-x) 
#Ahora buscamos la inversa y esta la escribimos como: solve[U=1-e^(-x)] 
# esto es para obtener la inversa en funcion de U. El resultado que se debe observar es el que que dice solucion real y si no 
# aparece escogemos el result pero sin el numero imaginario osea lo que contiene i. 
# Obtenemos x = log (1 / (1 - U)) y lo que debemos utilizar en R es: log(1/(1-U))


X.1 = log(1/(1-U))  #Con esto ya esta finalizado el ejercicio pq ya esta calculada la inversa pero 
#compruebo que esta correctocomparando lo que salio con lo que saca R

# Con esta formula compruebo con R
Y.1 = rexp(Nsim,1) # Generamos aleatoriamente la variable Y mediante la exponencial.
#Es decir, esto sería el resultado real que tendría que salir

# Este sera mediante la funcion inversa
pg2 <- ggplot() + aes(X.1)+ geom_density(colour="blue", fill="lightblue") +
  ggtitle("Exp from Uniform")
print(pg2)

# Este seria mediante la distribucion exponencial real:
pg <- ggplot() + aes(Y.1)+ geom_density(colour="blue", fill="lightblue") +
  ggtitle("Exp from R")
print(pg)

# Aqui lo dibuja todo junto
multiplot(pg, pg2, cols=2)

#----------------------------------------------------------------------------------------------------------------
#Inciso a) Exponencial con Parametros Lambda = 1.5 --------------------------------------------------------------
#----------------------------------------------------------------------------------------------------------------

# Primero tengo que buscar en www.wolframalpha.com esto: exponential distribution lambda = 1.5
# Escogemos la que dice Cumulative distribution function (CDF)
# y este me devuelve la formula de su exponencial: 1-e^(-1.5x) 
#Ahora buscamos la inversa y esta la escribimos como: solve[U=1-e^(-1.5x)] 
# esto es para obtener la inversa en funcion de U. El resultado que se debe observar es el que que dice solucion real y si no 
# aparece escogemos el result pero sin el numero imaginario osea lo que contiene i. 
# Obtenemos x = 2/3 log (1 / (1 - U)) y lo que R interpreta es x = 2/3*log(1/(1-U))
 

X.15 = 2/3*log(1/(1-U)) #Con esto ya esta finalizado el ejercicio pq ya esta calculada la inversa pero 
#compruebo que esta correctocomparando lo que salio con lo que saca R

# Con esta formula compruebo con R
Y.15 = rexp(Nsim,1.5) # Generamos aleatoriamente la variable Y mediante la exponencial.
#Es decir, esto sería el resultado real que tendría que salir

# Este sera mediante la funcion inversa
pg2 <- ggplot() + aes(X.15)+ geom_density(colour="blue", fill="lightblue") +
  ggtitle("Exp from Uniform")
print(pg2)

# Este sería mediante la distribución exponencial real:
pg <- ggplot() + aes(Y.15)+ geom_density(colour="blue", fill="lightblue") +
  ggtitle("Exp from R")
print(pg)

# Aqui lo dibuja todo junto
multiplot(pg, pg2, cols=2)

#----------------------------------------------------------------------------------------------------------------
#Inciso b) Logistica con (location) = 5 y (scale) = 2 -----------------------------------------------------------
#----------------------------------------------------------------------------------------------------------------


# Nsim es la cantidad de punto que quiero simular, en este caso decidi escoger 10^5 
# pero siempre debo trabajar con numeros grandes pq puede salir una basura
Nsim = 10^6 # Numero de variables
# Aqui como en el ejercicio 1 generamos una muestra uniforme para todos los puntos de Nsim utilizando runif() 
# y si no se le especifican intervalos el escoge por defeco[0,1]
U = runif(Nsim) # Cogemos la función uniforme de 10^5 elementos


# Primero tengo que buscar en www.wolframalpha.com esto: logistic distribution mean= 5, scale=2
# Escogemos la que dice Cumulative distribution function (CDF): 
# y este me devuelve la formula de su exponencial: 1/(e^((5 - x)/2) + 1) 
#Ahora buscamos la inversa y esta la escribimos como: solve[U=1/(e^((5 - x)/2) + 1)] 
# esto es para obtener la inversa en funcion de U. El resultado que se debe observar es el que que dice solucion real y si no 
# aparece escogemos el result pero sin el numero imaginario osea lo que contiene i. 
# Obtenemos x= 2 log (- (e ^ (5/2) U) / (U - 1)) 
#simplificamos la funcion pq esta demasiado complicada de la sgte manera: simplify[2 log (- (e ^ (5/2) U) / (U - 1))]
#Este es el resultado despues de simplificar y traducirlo a R: 2*log(-U/(U - 1)) + 5


X.5.2 = 2*log(-U/(U - 1)) + 5 # Aplicamos la función inversa de la logística

Y.5.2 = rlogis(Nsim, location = 5, scale = 2) # Generamos aleatoriamente la variable Y mediante la exponencial. Es decir, esto sería el resultado real que tendría que salir

# Este sería mediante la función inversa
pg2 <- ggplot() + aes(X.5.2)+ geom_density(colour="blue", fill="lightblue") +
  ggtitle("Exp from Uniform")
print(pg2)

# Este sería mediante la distribución exponencial real:
pg <- ggplot() + aes(Y.5.2)+ geom_density(colour="blue", fill="lightblue") +
  ggtitle("Exp from R")
print(pg)

# Aqui lo dibuja todo junto
multiplot(pg, pg2, cols=2)

#----------------------------------------------------------------------------------------------------------------
#Inciso b) Logistica con (location) = 6 y (scale) = 2 -----------------------------------------------------------
#----------------------------------------------------------------------------------------------------------------

# Primero tengo que buscar en www.wolframalpha.com esto: logistic distribution mean= 6, scale=2
# Escogemos la que dice Cumulative distribution function (CDF): 
# y este me devuelve la formula de su exponencial: 1/(e^((6 - x)/2) + 1) 
#Ahora buscamos la inversa y esta la escribimos como: solve[U=1/(e^((6 - x)/2) + 1)] 
# esto es para obtener la inversa en funcion de U. El resultado que se debe observar es el que que dice solucion real y si no 
# aparece escogemos el result pero sin el numero imaginario osea lo que contiene i. 
# Obtenemos x = 2 log (-U / (U - 1)) + 6
#simplificamos la funcion pq esta demasiado complicada de la sgte manera: simplify[2 log (-U / (U - 1)) + 6]
#Este es el resultado despues de simplificar y traducirlo a R: 2*(log(-U/(U-1))+3)

X.6.2 = 2*(log(-U/(U-1))+3) # Aplicamos la función inversa de la logística
Y.6.2 = rlogis(Nsim, location = 6, scale = 2) # Generamos aleatoriamente la variable Y mediante la exponencial. Es decir, esto sería el resultado real que tendría que salir

# Este sería mediante la función inversa
pg2 <- ggplot() + aes(X.6.2)+ geom_density(colour="blue", fill="lightblue") +
  ggtitle("Exp from Uniform")
print(pg2)

# Este sería mediante la distribución exponencial real:
pg <- ggplot() + aes(Y.6.2)+ geom_density(colour="blue", fill="lightblue") +
  ggtitle("Exp from R")
print(pg)

# Aqui lo dibuja todo junto
multiplot(pg, pg2, cols=2)

#----------------------------------------------------------------------------------------------------------------
#Inciso b) Logistica con (location) = 9 y (scale) = 3 -----------------------------------------------------------
#----------------------------------------------------------------------------------------------------------------

# Primero tengo que buscar en www.wolframalpha.com esto: logistic distribution mean= 9, scale=3
# Escogemos la que dice Cumulative distribution function (CDF): 
# y este me devuelve la formula de su exponencial: 1 / (e ^ ((9 - x) / 3) + 1)
#Ahora buscamos la inversa y esta la escribimos como: solve[U=1 / (e ^ ((9 - x) / 3) + 1)] 
# esto es para obtener la inversa en funcion de U. El resultado que se debe observar es el que que dice solucion real y si no 
# aparece escogemos el result pero sin el numero imaginario osea lo que contiene i. 
# Obtenemos x = 3 log (-U / (U - 1)) + 9 
#simplificamos la funcion pq esta demasiado complicada de la sgte manera: simplify[3 log (-U / (U - 1)) + 9]
#Este es el resultado despues de simplificar y traducirlo a R: 3*(log(-U/(U-1))+3)


X.9.3 = 3*(log(-U/(U-1))+3) # Aplicamos la función inversa de la logística
Y.9.3 = rlogis(Nsim, location = 9, scale = 3) # Generamos aleatoriamente la variable Y mediante la exponencial. Es decir, esto sería el resultado real que tendría que salir

# Este sería mediante la función inversa
pg2 <- ggplot() + aes(X.9.3)+ geom_density(colour="blue", fill="lightblue") +
  ggtitle("Exp from Uniform")
print(pg2)

# Este sería mediante la distribución exponencial real:
pg <- ggplot() + aes(Y.9.3)+ geom_density(colour="blue", fill="lightblue") +
  ggtitle("Exp from R")
print(pg)

# Aqui lo dibuja todo junto
multiplot(pg, pg2, cols=2)


#----------------------------------------------------------------------------------------------------------------
#Inciso c) Weibull con (scale) = 1 y (shape) = 0.5  -------------------------------------------------------------
#----------------------------------------------------------------------------------------------------------------

# Nsim es la cantidad de punto que quiero simular, en este caso decidi escoger 10^6 
# pero siempre debo trabajar con numeros grandes pq puede salir una basura
Nsim = 10^6 # Numero de variables
# Aqui como en el ejercicio 1 generamos una muestra uniforme para todos los puntos de Nsim utilizando runif() 
# y si no se le especifican intervalos el escoge por defeco[0,1]
U = runif(Nsim) # Cogemos la función uniforme de 10^5 elementos


# Primero tengo que buscar en www.wolframalpha.com esto: weibull distribution  shape = 0.5, scale = 1
# Escogemos la que dice Cumulative distribution function (CDF): 
# y este me devuelve la formula de su exponencial:  1 - e ^ (- x ^ (0.5))
#Ahora buscamos la inversa y esta la escribimos como: solve[U= 1 - e ^ (- x ^ (0.5))] 
# esto es para obtener la inversa en funcion de U. El resultado que se debe observar es el que que dice solucion real y si no 
# aparece escogemos el result pero sin el numero imaginario osea lo que contiene i. 
# Obtenemos x = log ^ 2 (1 - U)
#simplificamos la funcion pq esta demasiado complicada de la sgte manera: simplify[log ^ 2 (1 - U)]
#Este es el resultado despues de simplificar y traducirlo a R: log ^ 2 (1 - U) Esto tiene un problema
#OJOOOOO cuando voy a traducirlo a R el cuadrado del logaritmo no puede estar al principio debe envolver 
#a toda la funcion logaritmica y por lo tanto queda de la sgte manera: (log(1 - U))^ 2

X.1.05 = (log(1 - U))^2 # Aplicamos la función inversa de la  función de Weibull
Y.1.05 = rweibull(Nsim, scale = 1, shape = 0.5) # Generamos aleatoriamente la variable Y mediante la exponencial. Es decir, esto sería el resultado real que tendría que salir

# Este sería mediante la función inversa
pg2 <- ggplot() + aes(X.1.05)+ geom_density(colour="blue", fill="lightblue") +
  ggtitle("Exp from Uniform")
print(pg2)

# Este sería mediante la distribución exponencial real:
pg <- ggplot() + aes(Y.1.05)+ geom_density(colour="blue", fill="lightblue") +
  ggtitle("Exp from R")
print(pg)

# Aqui lo dibuja todo junto
multiplot(pg, pg2, cols=2)


#----------------------------------------------------------------------------------------------------------------
#Inciso c) Weibull con (scale) = 1 y (shape) = 1  ---------------------------------------------------------------
#----------------------------------------------------------------------------------------------------------------

# Primero tengo que buscar en www.wolframalpha.com esto: weibull distribution  shape = 1, scale = 1
# Escogemos la que dice Cumulative distribution function (CDF): 
# y este me devuelve la formula de su exponencial:  1 - e ^ (- x)
#Ahora buscamos la inversa y esta la escribimos como: solve[U= 1 - e ^ (- x)] 
# esto es para obtener la inversa en funcion de U. El resultado que se debe observar es el que que dice solucion real y si no 
# aparece escogemos el result pero sin el numero imaginario osea lo que contiene i. 
# Obtenemos x = log (1 / (1 - U))
#simplificamos la funcion pq esta demasiado complicada de la sgte manera: simplify[log (1 / (1 - U))]
#Este es el resultado despues de simplificar y traducirlo a R: log(-1/(U - 1)) 

X.1.1 = log(-1/(U - 1)) # Aplicamos la función inversa de la  función de Weibull
Y.1.1 = rweibull(Nsim, scale = 1, shape = 1) # Generamos aleatoriamente la variable Y mediante la exponencial. Es decir, esto sería el resultado real que tendría que salir

# Este sería mediante la función inversa
pg2 <- ggplot() + aes(X.1.1)+ geom_density(colour="blue", fill="lightblue") +
  ggtitle("Exp from Uniform")
print(pg2)

# Este sería mediante la distribución exponencial real:
pg <- ggplot() + aes(Y.1.1)+ geom_density(colour="blue", fill="lightblue") +
  ggtitle("Exp from R")
print(pg)

# Aqui lo dibuja todo junto
multiplot(pg, pg2, cols=2)

#----------------------------------------------------------------------------------------------------------------
#Inciso c) Weibull con (scale) = 1 y (shape) = 1.5  -------------------------------------------------------------
#----------------------------------------------------------------------------------------------------------------

# Primero tengo que buscar en www.wolframalpha.com esto: weibull distribution  shape = 1.5, scale = 1
# Escogemos la que dice Cumulative distribution function (CDF): 
# y este me devuelve la formula de su exponencial:  1 - e ^ (- x^(1.5))
#Ahora buscamos la inversa y esta la escribimos como: solve[U= 1 - e ^ (- x^(1.5))] 
# esto es para obtener la inversa en funcion de U. El resultado que se debe observar es el que que dice solucion real y si no 
# aparece escogemos el result pero sin el numero imaginario osea lo que contiene i. 
# Obtenemos x = (-log(1 - U))^(2/3)
#simplificamos la funcion pq esta demasiado complicada de la sgte manera: simplify[(-log(1 - U))^(2/3)]
#Este es el resultado despues de simplificar y traducirlo a R: (-log(1 - U))^(2/3) 

X.1.15 = (-log(1 - U))^(2/3) # Aplicamos la función inversa de la  función de Weibull
Y.1.15 = rweibull(Nsim, scale = 1, shape = 1.5) # Generamos aleatoriamente la variable Y mediante la exponencial. Es decir, esto sería el resultado real que tendría que salir

# Este sería mediante la función inversa
pg2 <- ggplot() + aes(X.1.15)+ geom_density(colour="blue", fill="lightblue") +
  ggtitle("Exp from Uniform")
print(pg2)

# Este sería mediante la distribución exponencial real:
pg <- ggplot() + aes(Y.1.15)+ geom_density(colour="blue", fill="lightblue") +
  ggtitle("Exp from R")
print(pg)

# Aqui lo dibuja todo junto
multiplot(pg, pg2, cols=2)


#----------------------------------------------------------------------------------------------------------------
#Inciso c) Weibull con (scale) = 1 y (shape) = 5  ---------------------------------------------------------------
#----------------------------------------------------------------------------------------------------------------

# Primero tengo que buscar en www.wolframalpha.com esto: weibull distribution  shape = 5, scale = 1
# Escogemos la que dice Cumulative distribution function (CDF): 
# y este me devuelve la formula de su exponencial:  1 - e ^ (- x^(5))
#Ahora buscamos la inversa y esta la escribimos como: solve[U=  1 - e ^ (- x^(5))] 
# esto es para obtener la inversa en funcion de U. El resultado que se debe observar es el que que dice solucion real y si no 
# aparece escogemos el result pero sin el numero imaginario osea lo que contiene i. 
# Obtenemos x = (log (1 / (1 - U))) ^ (1/5) 
#simplificamos la funcion pq esta demasiado complicada de la sgte manera: simplify[(log (1 / (1 - U))) ^ (1/5)]
#Este es el resultado despues de simplificar y traducirlo a R: log(-1/(U - 1))^(1/5) 

X.1.5 = log(-1/(U - 1))^(1/5) # Aplicamos la función inversa de la  función de Weibull
Y.1.5 = rweibull(Nsim, scale = 1, shape = 5) # Generamos aleatoriamente la variable Y mediante la exponencial. Es decir, esto sería el resultado real que tendría que salir

# Este sería mediante la función inversa
pg2 <- ggplot() + aes(X.1.5)+ geom_density(colour="blue", fill="lightblue") +
  ggtitle("Exp from Uniform")
print(pg2)

# Este sería mediante la distribución exponencial real:
pg <- ggplot() + aes(Y.1.5)+ geom_density(colour="blue", fill="lightblue") +
  ggtitle("Exp from R")
print(pg)

# Aqui lo dibuja todo junto
multiplot(pg, pg2, cols=2)



#########################################################################################################
######################Hoja 2 Ejercicio 1. Aceptacion y Rechazo Simple ###################################
#########################################################################################################


#Trabajaremos con la funcion de densidad que es la que me dan en el problema en el intervalo de [0,1]
myf= function(x){
  20*x*(1-x)^3
}
#Escogemos una muestra de puntos de 100000 pq asi lo estime conveniente en el intervalo de [0,1]
sample.x = runif(100000,0,1)


#Primero antes de iterar por el for hay que encontrar el maximo de la funcion para comparar dentro del for
#La notacion que utilizamos para buscar en wolframalpha ------ max[20*x*(1-x)^3] in [0,1]
#Encontramos que el maximo de la funcion es 135/64 Escogemos el valor que se guarda en la variable maximo global  no el maximo local
accept = c()

for(i in 1:length(sample.x)){
  #Aqui saca un valor aleatorio entre [0,1]
  U = runif(1, 0, 1)
  
  #Aqui se recorre toda la muestra comprobando que el maximo * U es <= que la funcion
  if((135/64)*U <= myf(sample.x[i])) { # Si queda por debajo de la funcion lo aceptamos. El maximo es 135/64 = 2.11
    accept[i] = 'Yes' 
  } 
  else if((135/64)*U > myf(sample.x[i])) { # Si no, lo rechazamos
    accept[i] = 'No'
  }
}

#Aqui guardo el data frame de todos los aceptados y rechazados
T = data.frame(sample.x, accept = factor(accept, levels= c('Yes','No')))


#Estas lineas solo son para verificar los datos la solucion se muestra con el ggplot del final
head(T)
summary(T)
hist(T[,1][T$accept=='Yes'], breaks = seq(0,1,0.01), freq = FALSE, main = 'Histogram of X', xlab = 'X')

# Con estas lineas queda resuelto el ejercicio, 
# Ojoooo es importante mantener el intervalo que nos den al principio [0,1]

#El print es la aproximacion
print(qplot(sample.x, data = T, geom = 'density', color = accept))

#El ggplot es la funcion real
ggplot(data.frame(x=c(0, 1)), aes(x)) + stat_function(fun=myf)


#########################################################################################################
######################Hoja 2 Ejercicio 2. Aceptacion y Rechazo Simple ###################################
#########################################################################################################

#Esto es lo mismo que el ejercicio anterior. Solo que en este ejercicio hay que fijarse que si no me dieran 
#la funcion de densidad de Beta (3,5) habria que buscarla. esta es la manera en wolframalpha: Beta density alpha = 3 beta = 5
#Pero como ya la tengo solo debo nconcentrarme en buscar el max global.

# Definimos nuestra función:
myf= function(x){
  105*x^2*(1-x)^4
}

# Generamos la muestra:
sample.x = runif(100000,0,1)

# Inicializamos las aceptaciones
accept = c()

#Primero antes de iterar por el for hay que encontrar el maximo de la funcion para comparar dentro del for
#La notacion que utilizamos para buscar en wolframalpha ------ max[105*x^2*(1-x)^4] in [0,1]
#Encontramos que el maximo de la funcion es 560/243 Escogemos el valor que se guarda en la variable maximo global  no el maximo local

# Generamos el cálculo
for(i in 1:length(sample.x)){ # Para cada muestra:
  U = runif(1, 0, 1) # Dame una probabilidad uniforme
  if((560/243)*U <= myf(sample.x[i])) { # Si queda por debajo de la función lo aceptamos. El máximo es 560/243 (calculado en http://www.wolframalpha.com)
    accept[i] = 'Yes' 
  } 
  else if((560/243)*U > myf(sample.x[i])) { # Si no, lo rechazamos
    accept[i] = 'No'
  }
}

#Aqui guardo el data frame de todos los aceptados y rechazados
T = data.frame(sample.x, accept = factor(accept, levels= c('Yes','No')))

#Estas lineas solo son para verificar los datos la solucion se muestra con el ggplot del final
head(T)
summary(T)
hist(T[,1][T$accept=='Yes'], breaks = seq(0,1,0.01), freq = FALSE, main = 'Histogram of X', xlab = 'X')

# Con estas lineas queda resuelto el ejercicio, 
# Ojoooo es importante mantener el intervalo que nos den al principio [0,1]

#El print es la aproximacion
print(qplot(sample.x, data = T, geom = 'density', color = accept))

#El ggplot es la funcion real
ggplot(data.frame(x=c(0, 1)), aes(x)) + stat_function(fun=myf)



#########################################################################################################
######################Hoja 2 Ejercicio 3. Aceptacion y Rechazo General ###################################
#########################################################################################################


#En el metodo de aceptacion y rechazo general hay que hayar el maximo de la divicion de dos funciones de densidad 
#(siempre se escoge la funcion de densidad aqui),en este caso me piden hayar la:
#funcion density de la normal N(0,1) a partir de una Cauchy(0,1)

#Primero se hayan las dos funciones de densidad y luego se dividen para al final encontrar el maximo de esa divicion.
#Cuando se trata de buscar la funcion de densidad es mejor buscarla por la funsion de distribucion y ahi aparecen las dos.
#De esta forma se busca la normal: ---- normal distribution mean=0 , standard desviation=1
#y se escoge la de densidad ------ e ^ (- x ^ 2/2) / sqrt (2*π)
#De esta forma se busca la cauchy ---- cauchy distribution location=0 , scale=1
#y se escoge la de densidad ----- 1 / (π*(x ^ 2 + 1))
#Luego se busca el maximo de la divicion entre ella de la sgte forma: 
#Alertaaaa hay que encerrar cada funcion entre parentesis por separado pq sino no lo calcula bien
#Se escribiria de la sgte forma---- max[ (e ^ (- x ^ 2/2) / sqrt (2*π)) / (1 / (π*(x ^ 2 + 1))) ] 
#Se obtiene ---- sqrt ((2*π) / e) Alerta hay que cambiar el simbolo por pi y e por exp(1) pq R no los reconoce tal cual
#y por lo tanto queda transformado para utilizar en R de la sgte manera: (sqrt ((2*pi) / exp(1)))


#Este codigo inicializa una envuelve y luego se ira comprobando hasta que realmente lo envuelva 
x = seq(0,1,by=0.001) #Esto da un warning no preocuparse


#En estos pasos se utiliza pcauchy y pnorm para dibujar las funciones
y1 = pcauchy(x,0,1)
y2 = pnorm(x,0,1)
df <- data.frame(x,y1,y2)

g <- ggplot(df, aes(x))
g <- g + geom_line(aes(y=y1), colour="red")
g <- g + geom_line(aes(y=y2), colour="green")
g

#Aqui se escoge la cantidad de puntos de muestra
#ALERTA AQUI SE MANTIENE RCAUCHY PQ LA QUE ENVUELVE ES LA FUN cAUCHY
sample.x = rcauchy(100000,0,1)

#Aqui se restringe el intervalo entre -3 y 3 pq en cauchy el intervalo es de - infinito a + infinito.
sample.x<-subset(sample.x, sample.x>-3 & sample.x<3)

#Se inicializa el vector de aceptados
accept = c()

#Primero antes de iterar por el for se utiliza el maximo de la div de las funciones que se encontro anteriormente
#para comparar dentro del for y ya sale el resultado de la aproximacion solo queda mostralo.
#El valor del maximo es el siguiente: (sqrt ((2*pi) / exp(1)))
#ALERTAAA aqui se trabaja con dcauchy y dnorm pq son las funciones que se utilizan
#en el ejercicio SINO HAY QUE CAMBIARLO  
for(i in 1:length(sample.x)){
  U = runif(1, 0, 1)
  if(dcauchy(sample.x[i], 0, 1)*(sqrt ((2*pi) / exp(1)))*U <= dnorm(sample.x[i], 0, 1)) { 
    accept[i] = 'Yes' 
  } 
  else if(dcauchy(sample.x[i],0,1)*(sqrt ((2*pi) / exp(1)))*U > dnorm(sample.x[i], 0, 1)) {
    accept[i] = 'No'
  }
}

#Aqui guardo los resultados
T = data.frame(sample.x, accept = factor(accept, levels= c('Yes','No')))
summary(T)


#Con el print y el qplot ya queda resuelto el ejercicio
print(qplot(sample.x, data = T, geom = 'density', color = accept))
qplot(sample.x, data = T, geom = 'density', color = accept)  



#########################################################################################################
######################Hoja 2 Ejercicio 4. Aceptacion y Rechazo General ###################################
#########################################################################################################

#En el metodo de aceptacion y rechazo general hay que hayar el maximo de la divicion de dos funciones de densidad 
#(siempre es la funcion de densidad aqui),en este caso me piden hayar la fun density N(0,1) a partir de una logistic(0,1)

#Primero se hayan las dos funciones de densidad y luego se dividen para al final encontrar el maximo de esa divicion.
#Cuando se trata de buscar la funcion de densidad es mejor buscarla por la funsion de distribucion y ahi aparecen las dos.
#De esta forma se busca la normal: ---- normal distribution mean=0 , standard desviation=1
#y se escoge la de densidad ------ e ^ (- x ^ 2/2) / sqrt (2*π)
#De esta forma se busca la logistic ---- logistic distribution  mean=0 , scale=1
#y se escoge la de densidad ----- e^(-x)/(e^(-x) + 1)^2
#Luego se busca el maximo de la divicion entre ella de la sgte forma: 
#Alertaaaa hay que encerrar cada funcion entre parentesis por separado pq sino no lo calcula bien
#Se escribiria de la sgte forma---- max[ (e ^ (- x ^ 2/2) / sqrt (2*π)) / (e^(-x)/(e^(-x) + 1)^2) ] 
#Se obtiene ---- 2 * sqrt(2/π) Alerta hay que cambiar el simbolo por pi pq R no los reconoce tal cual


#Este codigo inicializa una envuelve y luego se ira comprobando hasta que realmente lo envuelva 
x = seq(0,1,by=0.001) #Esto da un warning no preocuparse

#En estos pasos se utiliza plogis y pnorm para dibujar las funciones
y1 = plogis(x,0,1) 
y2 = pnorm(x,0,1)
df <- data.frame(x,y1,y2)

g <- ggplot(df, aes(x))
g <- g + geom_line(aes(y=y1), colour="red")
g <- g + geom_line(aes(y=y2), colour="green")
g

#Aqui se escoge la cantidad de puntos de muestra
#ALERTA AQUI SE MANTIENE RLOGIS PQ LA QUE ENVUELVE ES LA FUN LOGISTICA
sample.x = rlogis(100000,0,1)

#Aqui se restringe el intervalo entre -3 y 3 pq en la fun logistic el intervalo es de - infinito a + infinito.
sample.x<-subset(sample.x, sample.x>-3 & sample.x<3)

#Se inicializa el vector de aceptados
accept = c()


#Primero antes de iterar por el for se utiliza el maximo de la div de las funciones que se encontro anteriormente
#para comparar dentro del for y ya sale el resultado de la aproximacion solo queda mostralo.
#El valor del maximo es el siguiente: 2 * sqrt(2/π)
#ALERTAAA aqui se trabaja con dlogis y dnorm pq son las funciones que se utilizan
#en el ejercicio SINO HAY QUE CAMBIARLO
for(i in 1:length(sample.x)){
  U = runif(1, 0, 1)
  if(dlogis(sample.x[i], 0, 1)*(2 * sqrt(2/pi))*U <= dnorm(sample.x[i], 0, 1)) { 
    accept[i] = 'Yes' 
  } 
  else if(dlogis(sample.x[i],0,1)*(2 * sqrt(2/pi))*U > dnorm(sample.x[i], 0, 1)) {
    accept[i] = 'No'
  }
}

#Aqui guardo los resultados
T = data.frame(sample.x, accept = factor(accept, levels= c('Yes','No')))
summary(T)


#Con el print y el qplot ya queda resuelto el ejercicio
print(qplot(sample.x, data = T, geom = 'density', color = accept))
qplot(sample.x, data = T, geom = 'density', color = accept)  


#########################################################################################################
######################Hoja 2 Ejercicio 5. Aceptacion y Rechazo General ###################################
#########################################################################################################

#En el metodo de aceptacion y rechazo general hay que hayar el maximo de la divicion de dos funciones de densidad 
#(siempre es la funcion de densidad aqui),en este caso me piden hayar la fun density Beta(2.7,6.3) a partir de una Beta(2,6)


#Primero se hayan las dos funciones de densidad y luego se dividen para al final encontrar el maximo de esa divicion.
#en este caso buscamos directamente la funcion de densidad
#De esta forma se busca la Beta: ---- Beta density alpha = 2.7 beta = 6.3
#y se escoge la fun de densidad resultante------ 129.339 (1 - x) ^ 5.3 x ^ 1.7
#De esta forma se busca la Beta: ---- Beta density alpha = 2 beta = 6
#y se escoge la fun de densidad resultante ----- 42 (1 - x) ^ 5 x
#Luego se busca el maximo de la divicion entre ella de la sgte forma: 
#Alertaaaa hay que encerrar cada funcion entre parentesis por separado pq sino no lo calcula bien
#Se escribiria de la sgte forma---- max[ (129.339 (1 - x) ^ 5.3 x ^ 1.7) / (42 (1 - x) ^ 5 x) ] 
#Se obtiene ---- 1.6718 


#Este codigo inicializa una envuelve y luego se ira comprobando hasta que realmente lo envuelva 
x = seq(0,1,by=0.001) #Esto da un warning no preocuparse

#En estos pasos se utiliza pbeta y pbeta para dibujar las funciones
y1 = pbeta(x,2.7,6.3) 
y2 = pbeta(x,2,6)
df <- data.frame(x,y1,y2)

g <- ggplot(df, aes(x))
g <- g + geom_line(aes(y=y1), colour="red")
g <- g + geom_line(aes(y=y2), colour="green")
g

#Aqui se escoge la cantidad de puntos de muestra
#ALERTA AQUI SE MANTIENE rbeta PQ LA QUE ENVUELVE ES LA FUN beta y se ponen los intervalos de la misma
sample.x = rbeta(100000,2,6)

#Aqui se restringe el intervalo entre -3 y 3 pq en la fun beta el intervalo es de - infinito a + infinito.
sample.x<-subset(sample.x, sample.x>-3 & sample.x<3)

#Se inicializa el vector de aceptados
accept = c()


#Primero antes de iterar por el for se utiliza el maximo de la div de las funciones que se encontro anteriormente
#para comparar dentro del for y ya sale el resultado de la aproximacion solo queda mostralo.
#El valor del maximo es el siguiente: 1.6718
#ALERTAAA aqui se trabaja con dbeta y dbeta pq son las funciones que se utilizan
#en el ejercicio SINO HAY QUE CAMBIARLO
for(i in 1:length(sample.x)){
  U = runif(1, 0, 1)
  if(dbeta(sample.x[i],2, 6 )*(1.6718)*U <= dbeta(sample.x[i],2.7, 6.3 )) { 
    accept[i] = 'Yes' 
  } 
  else if(dbeta(sample.x[i],2, 6)*(1.6718)*U > dbeta(sample.x[i],2.7, 6.3)) {
    accept[i] = 'No'
  }
}

#Aqui guardo los resultados
T = data.frame(sample.x, accept = factor(accept, levels= c('Yes','No')))
summary(T)


#Con el print y el qplot ya queda resuelto el ejercicio
print(qplot(sample.x, data = T, geom = 'density', color = accept))
qplot(sample.x, data = T, geom = 'density', color = accept)  


#########################################################################################################
######################Hoja 2 Ejercicio 6. Aceptacion y Rechazo General ###################################
#########################################################################################################

#--------------------------------------------------------------------------------------------------------
#Inciso a) ----------------------------------------------------------------------------------------------
#--------------------------------------------------------------------------------------------------------

#Primero introducimos la funcion y luego la dibujamos

#Funcion de densidad f(x)
myf= function(x){
  exp(-x^2/2)*((sin(6*x)^2) + (3*cos(x)^2) * (sin(4*x)^2)+1) 
}
#Aqui dibujamos la funcion guardada en myf
ggplot(data.frame(x=c(0, 1)), aes(x)) + stat_function(fun=myf, colour = 'red')


#Funcion de densidad normal estandar g(x)
myg= function(x){
  (exp(-x^2/2))/(sqrt(2*pi))
}
#Aqui dibujamos la funcion guardada en myf
ggplot(data.frame(x=c(0, 1)), aes(x)) + stat_function(fun=myg, colour = 'green')

#Dibujando las dos funciones
A = ggplot(data.frame(x=c(-3, 3)), aes(x)) + stat_function(fun=myf, colour = 'red')
A = A + stat_function(fun=myg, colour = 'green')
A


#Ahora buscamos el maximo de la division de las funciones de densidad
# ALERTAAA CUIDADO CON LOS PARENTESIS DEL NUMERADOR Y EL DENOMINADOR SIEMPRE TIENEN QUE ESTAR ENTE ()
# max[ (exp(-x^2/2)*((sin(6*x)^2) + (3*cos(x)^2) * (sin(4*x)^2)+1)) / (exp(-x^2/2)/(sqrt(2*pi))) ]
# EL RESULTADO ES 10.9403 PERO SE REDONDEA A 11 (EL REDONDEO SIEMPRE HACIA ARRIBA)

#Funcion de densidad normal estandar g(x) se multiplica por 11 porque es la que queremos subir y 11 es el maximo encontrado
mygRESULT1= function(x){
  (exp(-x^2/2))/(sqrt(2*pi)) * 11
}

#Dibujando las dos funciones y con esto terminamos el inciso
A = ggplot(data.frame(x=c(-3, 3)), aes(x)) + stat_function(fun=myf, colour = 'red')
A = A + stat_function(fun=mygRESULT1, colour = 'green')
A


#--------------------------------------------------------------------------------------------------------
#Inciso b) ----------------------------------------------------------------------------------------------
#--------------------------------------------------------------------------------------------------------


# Definimos nuestra función de densidad de la Normal[0,1]
myf= function(x){
  (exp(-x^(2/2))) / (sqrt(2*pi))
}

# Generamos la muestra:
sample.x = runif(2500,0,1)

# Inicializamos las aceptaciones
accept = c()


#Primero antes de iterar por el for hay que encontrar el maximo de la funcion para comparar dentro del for
#La notacion que utilizamos para buscar en wolframalpha ------ max[(exp(-x^(2/2))) / (sqrt(2*pi))] in [0,1]
#Encontramos que el maximo de la funcion es 1/(sqrt(2*pi))  Escogemos el valor que se guarda en la variable maximo global  no el maximo local

# Generamos el cálculo
for(i in 1:length(sample.x)){ # Para cada muestra:
  U = runif(1, 0, 1) # Dame una probabilidad uniforme
  if((1/(sqrt(2*pi)) )*U <= myf(sample.x[i])) { # Si queda por debajo de la función lo aceptamos. El máximo es 560/243 (calculado en http://www.wolframalpha.com)
    accept[i] = 'Yes' 
  } 
  else if((1/(sqrt(2*pi)) )*U > myf(sample.x[i])) { # Si no, lo rechazamos
    accept[i] = 'No'
  }
}

#Aqui guardo el data frame de todos los aceptados y rechazados
T = data.frame(sample.x, accept = factor(accept, levels= c('Yes','No')))

#Estas lineas solo son para verificar los datos la solucion se muestra con el ggplot del final
head(T)
summary(T)
hist(T[,1][T$accept=='Yes'], breaks = seq(0,1,0.01), freq = FALSE, main = 'Histogram of X', xlab = 'X')

# Con estas lineas queda resuelto el ejercicio, 
# Ojoooo es importante mantener el intervalo que nos den al principio [0,1]

#El print es la aproximacion
print(qplot(sample.x, data = T, geom = 'density', color = accept))

#El ggplot es la funcion real
ggplot(data.frame(x=c(0, 1)), aes(x)) + stat_function(fun=myf)


#########################################################################################################
######################Hoja 3 Ejercicio 1. Monte Carlos con Hit and Miss #################################
#########################################################################################################


#--------------------------------------------------------------------------------------------------------
#Inciso a) Integracion monte carlos utilizando hit and miss ---------------------------------------------
#--------------------------------------------------------------------------------------------------------

#Esta secuencia es para dibujar la funcion
x = seq(0,1,by=0.001)
f = function(x){20*x*(1-x)^3}
y = f(x)
df <- data.frame(x,y)

g <- ggplot(df, aes(x))
g <- g + geom_line(aes(y=y), colour="red")
g

#Creo una uniforme en el intervalo de la integral y escogi por que quiero una muestra de 100000
sample.x = runif(100000,0,1)
accept = c()
sample.accept = c()

#primero hayar el maximo de la funcion f = function(x){20*x*(1-x)^3} para utilizarlo dentro del for
#Alertaaaa hay que encerrar cada funcion entre parentesis por separado pq sino no lo calcula bien
#Se escribiria de la sgte forma---- max[20*x*(1-x)^3] in [0,1]
#Se obtiene ---- 135/64 redondeado es 2.11 como maximo

for(i in 1:length(sample.x)){
  #Genero la uniforme siempre en el intervalo[0,1] dentro del for
  U = runif(1, 0, 1)
  #Tomo los valores del sample en el mismo intervalo de la integral [0,1] y sustituyo el valor del max = (135/64)
  if(dunif(sample.x[i], 0, 1)*(2.11)*U <= f(sample.x[i])) { 
    accept[i] = 'Yes'
    sample.accept[i] = 1
  }
  #Tomo los valores del sample en el mismo intervalo de la integral [0,1] y sustituyo el valor del max = (135/64)
  else if(dunif(sample.x[i],0,1)*(2.11)*U > f(sample.x[i])) {
    accept[i] = 'No'
    sample.accept[i] = 0
  }
}

#Esta es la formula que me va a dar el valor de la integral I=c(b-a)p en hit and miss
#phat es mi p es todos los aceptados entre el tamano total de mi muestra
#c es el maximo de la funcion que se obtiene arriba en este caso es (2.11)
#(b-a) es la resta de los intervalos de la integral en este caso es (1-(0))
phat = sum(sample.accept)/100000
phat

#Aqui se aplica la formula
I = (2.11)*(1-(0))*phat
I  ## 0.9982832

#Aqui se compara con la integral que calcula R y como los dos cuando se redondean dan 1 entonces esta ok
Ireal = integrate(f,0,1)
Ireal  ##1 with absolute error < 1.1e-14

#Aqui se calcula la desviacion estandar por si la piden pero el ejercicio termina con lo anterior 
#desv estandar: (c(b-a)*sqrt(p*1-p))/sqrt(N)
#donde 2.11 es mi c,
#b-a osea 1-0 son la resta de mis intervalos que como da 1 en este caso no se pone
#p es mi phat
#N es la muestra 100000

SE = (2.11)*sqrt(phat*(1-phat))/sqrt(100000)
SE  #  0.003332373


#--------------------------------------------------------------------------------------------------------
#Inciso b) Integracion monte carlos utilizando hit and miss ---------------------------------------------
#--------------------------------------------------------------------------------------------------------


#Esta secuencia es para dibujar la funcion.Alerta debo fijarme en el intervalo de la integral
x = seq(-1,1,by=0.001)
f = function(x){(2/pi)*sqrt(1-x^2)}
y = f(x)
df <- data.frame(x,y)

g <- ggplot(df, aes(x))
g <- g + geom_line(aes(y=y), colour="red")
g

#Creo una uniforme en el intervalo de la integral y escogi por que quiero una muestra de 100000
sample.x = runif(100000,-1,1)
accept = c()
sample.accept = c()

#primero hayar el maximo de la funcion f = function(x){(2/pi)*sqrt(1-x^2)} para utilizarlo dentro del for
#Alertaaaa hay que encerrar cada funcion entre parentesis por separado pq sino no lo calcula bien
#Se escribiria de la sgte forma---- max[(2/pi)*sqrt(1-x^2)] in [-1,1]
#Se obtiene ---- (2/pi)

for(i in 1:length(sample.x)){
  #Genero la uniforme siempre en el intervalo[0,1] dentro del for
  U = runif(1, 0, 1)
  #Tomo los valores del sample en el mismo intervalo de la integral [-1,1] y sustituyo el valor del max = (2/pi)
  if(dunif(sample.x[i], -1,1)*(2/pi)*U <= f(sample.x[i])) { 
    accept[i] = 'Yes'
    sample.accept[i] = 1
  }
  #Tomo los valores del sample en el mismo intervalo de la integral [-1,1] y sustituyo el valor del max = (2/pi)
  else if(dunif(sample.x[i],-1,1)*(2/pi)*U > f(sample.x[i])) {
    accept[i] = 'No'
    sample.accept[i] = 0
  }
}

#Esta es la formula que me va a dar el valor de la integral I=c(b-a)p en hit and miss
#phat es mi p es todos los aceptados entre el tamano total de mi muestra
#c es el maximo de la funcion que se obtiene arriba en este caso es (2/pi)
#(b-a) es la resta de los intervalos de la integral en este caso es (1-(-1))
phat = sum(sample.accept)/100000
phat

#Aqui se aplica la formula
I = (2/pi)*(1-(-1))*phat
I  ## 1.218567

#Aqui se compara con la integral que calcula R y como los dos cuando se redondean dan 1 entonces esta ok
Ireal = integrate(f,-1,1)
Ireal  ##1 with absolute error < 1.1e-14

#Aqui se calcula la desviacion estandar por si la piden pero el ejercicio termina con lo anterior 
#desv estandar: (c(b-a)*sqrt(p*1-p))/sqrt(N)
#donde (2/pi) es mi c,
#b-a osea (1-(-1)) son la resta de mis intervalos que como da 1 en este caso no se pone
#p es mi phat
#N es la muestra 100000

SE = ((2/pi)*(1-(-1)))*sqrt(phat*(1-phat))/sqrt(100000)
SE  #  0.001772103


#--------------------------------------------------------------------------------------------------------
#Inciso c) Integracion monte carlos utilizando hit and miss ---------------------------------------------
#--------------------------------------------------------------------------------------------------------


#Esta secuencia es para dibujar la funcion.Alerta debo fijarme en el intervalo de la integral
x = seq(0,1,by=0.001)
f = function(x){cos((pi*x)/2)}
y = f(x)
df <- data.frame(x,y)

g <- ggplot(df, aes(x))
g <- g + geom_line(aes(y=y), colour="red")
g

#Creo una uniforme en el intervalo de la integral y escogi por que quiero una muestra de 100000
sample.x = runif(100000,0,1)
accept = c()
sample.accept = c()

#primero hayar el maximo de la funcion f = function(x){cos((pi*x)/2)} para utilizarlo dentro del for
#Alertaaaa hay que encerrar cada funcion entre parentesis por separado pq sino no lo calcula bien
#Se escribiria de la sgte forma---- max[(cos((pi*x)/2))] in [0,1]
#Se obtiene ---- 1

for(i in 1:length(sample.x)){
  #Genero la uniforme siempre en el intervalo[0,1] dentro del for
  U = runif(1, 0, 1)
  #Tomo los valores del sample en el mismo intervalo de la integral [0,1] y sustituyo el valor del max = 1
  if(dunif(sample.x[i], 0,1)*(2/pi)*U <= f(sample.x[i])) { 
    accept[i] = 'Yes'
    sample.accept[i] = 1
  }
  #Tomo los valores del sample en el mismo intervalo de la integral [0,1] y sustituyo el valor del max = 1
  else if(dunif(sample.x[i],0,1)*(2/pi)*U > f(sample.x[i])) {
    accept[i] = 'No'
    sample.accept[i] = 0
  }
}

#Esta es la formula que me va a dar el valor de la integral I=c(b-a)p en hit and miss
#phat es mi p es todos los aceptados entre el tamano total de mi muestra
#c es el maximo de la funcion que se obtiene arriba en este caso es 1
#(b-a) es la resta de los intervalos de la integral en este caso es (1-(0))
phat = sum(sample.accept)/100000
phat

#Aqui se aplica la formula
I = (1)*(1-(0))*phat
I  ## 0.79014

#Aqui se compara con la integral que calcula R y como los dos cuando se redondean dan 1 entonces esta ok
Ireal = integrate(f,0,1)
Ireal  ##0.6366198 with absolute error < 7.1e-15

#Aqui se calcula la desviacion estandar por si la piden pero el ejercicio termina con lo anterior 
#desv estandar: (c(b-a)*sqrt(p*1-p))/sqrt(N)
#donde (2/pi) es mi c,
#b-a osea (1-(-1)) son la resta de mis intervalos que como da 1 en este caso no se pone
#p es mi phat
#N es la muestra 100000

SE = ((1)*(1-(0)))*sqrt(phat*(1-phat))/sqrt(100000)
SE  #  0.001772103


#########################################################################################################
######################Hoja 3 Ejercicio 2. Integracion Monte Carlos con Distribucion Inversa #############
#########################################################################################################


#Inciso a) Integracion monte carlos utilizando distribución inversa ---------------------------------------------

#Creo una uniforme y escogi por que quiero una muestra de 10^6
Nsim=10^6 
#La uniforme no se escoge intervalo pq aqui siempre sera [0,1]
U=runif(Nsim)
#En este metodo tengo que partir la funcion en dos partes la de euler siempre hay que hayarle la inversa 
#y se guarda en mi X
#la inversa se busca en wolframalpha de la sgte manera: ---- solve[U=e^(-x)]
#el resultado que da la inversa es: -----  x = log(1/U))
X=log(1/U) 
#En la G siempre guardo el resto de la funcion
#Alertaaaa aqui la funcion trabaja con la X mayusculas que guardan el result de la inversa
G = function(X){sqrt(X)*cos(X)}
Y = G(X)
#Esto es el calculo de la integral
Int = sum(Y/Nsim)
Int   ## 0.2012767

#Aqui calculo la integral mediante R para comparar resultados
#Alertaaa aqui se escribe la funcion completa junto con la parte del exp
f = function(x){ 
  sqrt(x)*cos(x)*exp(-x)
}

#Cuando integramos vemos que los dos valores son muy parecidos
#ya con esto esta resuelto
integrate(f,0, Inf) ### 0.2016564 with absolute error < 2.1e-05

#Aqui dibujamos los graficos 
hist(X,freq=F,main="Exp from Uniform")
hist(Y,freq=F,main="Exp from R")

H = function(x){sqrt(x)*cos(x)*exp(-x)}
Y = H(X)
hist(Y,freq=F,main="Exp from R")

pg2 <- ggplot() + aes(X)+ geom_density(colour="blue", fill="lightblue") + ggtitle("Exp from R")
pg2


#--------------------------------------------------------------------------------------------------------
#Inciso b) Integracion monte carlos utilizando distribución inversa -------------------------------------
#--------------------------------------------------------------------------------------------------------


#Creo una uniforme y escogi por que quiero una muestra de 10^6
Nsim=10^6 
#La uniforme no se escoge intervalo pq aqui siempre sera [0,1]
U=runif(Nsim)
#En este metodo tengo que partir la funcion en dos partes la de euler siempre hay que hayarle la inversa 
#y se guarda en mi X
#la inversa se busca en wolframalpha de la sgte manera: ---- solve[U=e^(-x)]
#el resultado que da la inversa es: -----  x = log(1/U))
X=log(1/U) 
#En la G siempre guardo el resto de la funcion
#Alertaaaa aqui la funcion trabaja con la X mayusculas que guardan el result de la inversa
G = function(X){(X^2+3)*sin(X)}
Y = G(X)
#Esto es el calculo de la integral
Int = sum(Y/Nsim)
Int   ## 2.002678

#Aqui calculo la integral mediante R para comparar resultados
#Alertaaa aqui se escribe la funcion completa junto con la parte del exp 
f = function(x){ 
  (x^2+3)*sin(x)*exp(-x)
}

#Cuando integramos vemos que los dos valores son muy parecidos
#ya con esto esta resuelto
integrate(f,0, Inf) ### 2 with absolute error < 4.9e-05

#Aqui dibujamos los graficos 
hist(X,freq=F,main="Exp from Uniform")
hist(Y,freq=F,main="Exp from R")

H = function(x){(x^2+3)*sin(x)*exp(-x)}
Y = H(X)
hist(Y,freq=F,main="Exp from R")

pg2 <- ggplot() + aes(X)+ geom_density(colour="blue", fill="lightblue") + ggtitle("Exp from R")
pg2


#############################################################################################################
######################Hoja 3 Ejercicio 3. Integracion Monte Carlos utilizando Aceptación y Rechazo General###
#############################################################################################################

#Aqui pongo los intervalos que me dan y escojo el len osea la muestra que quiera
x <- seq(0, 1, len = 100000)

#Aqui pongo la funcion para dibujarla
f = function(x){sqrt(1-x^2)}
plot(f,0,1)

#En este ejercicio no es como el anterior aqui no se pide hayar g por distrib inversa  sino 
#aceptacion y rechazo general

#Creo una uniforme en el intervalo de la integral y escogi por que quiero una muestra de 100000
sample.x = runif(100000,0,1) 
accept = c()
sample.accept = c()

#En el metodo de aceptacion y rechazo general hay que hayar el maximo de la divicion de dos funciones de densidad 
#(siempre es la funcion de densidad aqui),en este caso me piden hayar la fun density f(x)(0,1) a partir de una Uniforme(0,1)

#Primero se hayan las dos funciones de densidad y luego se dividen para al final encontrar el maximo de esa divicion.
#Cuando se trata de buscar la funcion de densidad es mejor buscarla por la funsion de distribucion y ahi aparecen las dos.
#Ya me dan la funcion de densidad f(x): 3/2*(1-x^2)
#De esta forma se busca la func distribution de la uniforme ---- uniform distribution min = 0,  max=1
#y se escoge la de densidad ----- en este caso da como resultado 1
#Luego se busca el maximo de la divicion entre ella de la sgte forma: 
#Alertaaaa hay que encerrar cada funcion entre parentesis por separado pq sino no lo calcula bien
#Se escribiria de la sgte forma---- max[ 3/2*(1-x^2) / 1 ] 
#Se obtiene ---- 3/2 y es el que se utiliza en el for que es el maximo de la divicion entre ellas


for(i in 1:length(sample.x)){
  U = runif(1, 0, 1)
  if(dunif(sample.x[i], 0, 1)*(3/2)*U <= f(sample.x[i])) { 
    accept[i] = 'Yes'
    sample.accept[i] = sample.x[i]
  } 
  else if(dunif(sample.x[i],0,1)*(3/2)*U > f(sample.x[i])) {
    accept[i] = 'No'
    sample.accept[i] = 0
  }
}


#Aqui guardo en un data frame todos los aceptados y rechazados
T = data.frame(sample.x, accept = factor(accept, levels= c('Yes','No')),sample.accept)

#Aqui guardo solo los aceptados en Taccept
Taccept = T[accept == 'Yes',]

#Aqui aplico la funcion G = (sqrt(1-x^2))/((3/2)*(1-x^2)) y saco la longitud de los aceptados
G = function(x){(sqrt(1-x^2))/((3/2)*(1-x^2))}
Naccept = length(which(T$accept == 'Yes'))

#Aqui continuo aplicando el metodo demonte carlos y esta formula es la que me va a dar 
#el valor aproximado de la integral I=sum(x)/n donde mis x son los Taccept$sample.accept 
#y n es la cantidad total de los aceptados Naccept
I = sum(G(Taccept$sample.accept))/Naccept
I  ##0.8487686

#Aqui compruebo con el resultado de R
F = function(x){(sqrt(1-x^2))}
Ireal = integrate(F,0,1)
Ireal  #0.7853983 with absolute error < 0.00011

#Aqui estamos calculando la desviacion estandar que no me lo piden pero lo dejo aqui
S = sqrt(sum((G(Taccept$sample.accept)-sum(G(Taccept$sample.accept))/Naccept)^2)/(Naccept - 1))
S


#Aqui hago el metodo de hit and miss para comparar con el resultado que me dio como me piden

#Esta secuencia es para dibujar la funcion
x = seq(0, 1, len = 100000)
f = function(x){sqrt(1-x^2)}
y = f(x)
df <- data.frame(x,y)

g <- ggplot(df, aes(x))
g <- g + geom_line(aes(y=y), colour="red")
g

#Creo una uniforme en el intervalo de la integral y escogi por que quiero una muestra de 100000
sample.x = runif(100000,0,1)
accept = c()
sample.accept = c()

#primero hayar el maximo de la funcion f = function(x){sqrt(1-x^2)} para utilizarlo dentro del for
#Alertaaaa hay que encerrar cada funcion entre parentesis por separado pq sino no lo calcula bien
#Se escribiria de la sgte forma---- max[20*x*(1-x)^3] in [0,1]
#Se obtiene ---- 1 como maximo

for(i in 1:length(sample.x)){
  U = runif(1, 0, 1)
  if(dunif(sample.x[i], 0, 1)*(1)*U <= f(sample.x[i])) { 
    accept[i] = 'Yes'
    sample.accept[i] = 1
  } 
  else if(dunif(sample.x[i],0,1)*(1)*U > f(sample.x[i])) {
    accept[i] = 'No'
    sample.accept[i] = 0
  }
}

#Esta es la formula que me va a dar el valor de la integral I=c(b-a)p en hit and miss
#phat es mi p es todos los aceptados entre el tamano total de mi muestra
phat = sum(sample.accept)/100000
phat

#Aqui se aplica la formula para calcular la integral
I = (1)*(1-(0))*phat
I  ## 0.78285

#Aqui se compara con la integral que calcula R y como los dos cuando se redondean dan 1 entonces esta ok
Ireal = integrate(f,0,1)
Ireal  ##0.7853983 with absolute error < 0.00011

#############################################################################################################
######################Hoja 3 Ejercicio 4. Integracion utilizando Muestreo uniforme ##########################
#############################################################################################################

#--------------------------------------------------------------------------------------------------------
#Inciso a) Integracion monte carlos utilizando muestreo uniforme ----------------------------------------
#--------------------------------------------------------------------------------------------------------

#Alertaaaa esto solo lo podemos hacer si [a,b] (intervalo acotado)
#Esto es una funcion que me da la aproximacion de la integral del ejercicio aplicando muestreo uniforme
Integral <- function(n,a,b){
  X <- runif(n,a,b)
  Y <- ((b-a)*(2/pi)*sqrt(1-X^2)) #g(x) ~ Unif[a,b]
  Int <- sum(Y)/n
  Int}

#Llamo la funcion anterior pasandole los intervalos [-1,1] y escogiendo una muestra N = 1000000 
Integral(1000000,-1,1)  ### 1.000121

#Lo compara con el valor real de R llamando esta misma funcion
fx = function(x){(2/pi)*sqrt(1-x^2)}
IntReal = integrate(fx, -1, 1)
IntReal   #1 with absolute error < 6.4e-10


#--------------------------------------------------------------------------------------------------------
#Inciso b) Integracion monte carlos utilizando muestreo uniforme ----------------------------------------
#--------------------------------------------------------------------------------------------------------


#Alertaaaa esto solo lo podemos hacer si [a,b] (intervalo acotado)
#Esto es una funcion que me da la aproximacion de la integral del ejercicio aplicando muestreo uniforme
Integral <- function(n,a,b){
  X <- runif(n,a,b)
  Y <- ((b-a)*cos((pi*x)/2)) #g(x) ~ Unif[a,b]
  Int <- sum(Y)/n
  Int}

#Llamo la funcion anterior pasandole los intervalos [0,1] y escogiendo una muestra N = 1000000 
Integral(1000000,0,1)  ### 0.06366184

#Lo compara con el valor real de R llamando esta misma funcion
fx = function(x){cos((pi*x)/2)}
IntReal = integrate(fx, 0, 1)
IntReal   #0.6366198 with absolute error < 7.1e-15


#############################################################################################################
######################Hoja 3 Ejercicio 5. Integracion por importancia   #####################################
#############################################################################################################

# Queremos aproximar la integral de g(x) en [0,ifty]
# Definimos la función 
g = function(x){
  cos(x)*x^2*exp(-x)
}

#Dibujemos la funcion
x = seq(0,10,by=0.001)
y = g(x)
df <- data.frame(x,y)
gg<- ggplot(df, aes(x))
gg <- gg + geom_line(aes(y=y), colour="lightcoral")
gg

# Segun el mètodo de integración por importancia, fx optima debe ser

# | cos(x) * x^2 * exp(-x) | / (integral...)

# Així, la descomposició que més s'assembla és la de l'apartat c).

# A continuació, veurem quina descomposició és la millor. És a dir, a quin 
# apartat obtenim un error estàndar més petit.

#----------------------------------------------------------------------------------------------------------------
#Inciso a)  Aqui se trabaja por el metodo de la inversa cuando esta no resulte 0 -------------------------------
#----------------------------------------------------------------------------------------------------------------
  
# Condideramos G(x)= sx^2*cos(X)
G = function(x){(x^2)*cos(x)}

# Así, X ~ funcion distribucion con funcion de densidad:

fx = function(x){e^{-x}}

# que de hecho es una Exp(1)

# Simulamos X por el mètodo de la distribución inversa: 
# Fx(x)=1-e^{-x} => F_x^{-1}(U)= -log(1-U)

U=runif(20000) #Corolario MDI, U ~ U[0,1]
X=-log(1-U)
Int_a = sum(G(X)/20000)
Int_a

#Veamos el valor obtenido con R
Ireal = integrate(g,0,Inf)
Ireal

#Así, el error absoluto cometido es:
abs(Int_a-Ireal$value)

# con error estándar, SE= sqrt( sum((G(X)- mean(G(X)))^2)/(N-1) )
SE_a = sqrt( sum((G(X)- mean(G(X)))^2)/(20000-1) )
SE_a

#----------------------------------------------------------------------------------------------------------------
#Inciso b) Aqui se trabaja por el mètodo general de aceptacion y rechazo pq la inversa resulta 0 ---------------
#----------------------------------------------------------------------------------------------------------------
  
# Condideramos G(x)= sx^2*cos(X)
G = function(x){x*cos(x)}

# Así, X ~ funcion distribucion con funcion de densidad:
fx = function(x){x*exp(-x)}

# Simulamos X por el mètodo general de aceptaion y rechazo

# AR general
#-------------

# fx funcion de densidad
# Intervalo [0,infty]

# g funcion de densidad Cauchy(0,1)

# Queremos f <= M*g
# f/g <= M

# g no envuelve f, pero podemos encotnrar una M tq f < Mg.
#  M = max( f/g) es  4.73
# tomamos M=5
#Veamos como ahora, Mg(x) envuelve f(x)
x = seq(0,5,by=0.001)
y = fx(x)
env = dcauchy(x,0,1)
df <- data.frame(x,y,env)
gg <- ggplot(df, aes(x))
gg <- gg + geom_line(aes(y=y, colour="f(x)"))
gg <- gg + geom_line(aes(y=5*env, colour="M*g(x)"))
gg <- gg + scale_colour_manual('Density', values = c('red', 'green'))
gg

# Definimos la función aceptacion-rechazo general
#   c= max( f/g)
#   N= numero de muestras
AR_general <- function(c,N,f){
  
  sample.x = rcauchy(N,0,1)
  sample.x<-subset(sample.x, sample.x>0 & sample.x<8)
  
  accept = c()
  
  for(i in 1:length(sample.x)){
    U = runif(1, 0, 1)
    if(dcauchy(sample.x[i], 0, 1)*c*U <= f(sample.x[i])) { 
      accept[i] = 'Yes' 
    } 
    else if(dcauchy(sample.x[i],0,1)*c*U > f(sample.x[i])) {
      accept[i] = 'No'
    }
  }
  return(list(accept=accept, sample.x=sample.x))
}

AR=AR_general(5, 100000,fx)


#Aproximació
T = data.frame(AR$sample.x, accept = factor(AR$accept, levels= c('Yes','No')))
ga <- qplot(AR$sample.x, data = T, geom = 'density', color = AR$accept)
ga <- ga + labs(title = "fx from Cauchy(0,1)",  x = "x", y = "density", color = "Accept") +
  coord_cartesian(ylim = c(0,0.6), xlim=c(0,8))
#Densitat real
x = seq(0,8,by=0.001)
y = fx(x)
df <- data.frame(x,y)
gr <- ggplot(df, aes(x))+ geom_line(aes(y=y),  colour="deepskyblue1")
gr <- gr + labs(title = "fx from  R",  x = "x", y = "density") + 
  coord_cartesian(ylim = c(0,0.6), xlim=c(0,8))

multiplot(ga,gr,cols=2)

# X seran los elementos de la muestra que hemos aceptado.
X=T[T$accept=='Yes',]$AR.sample.x

# ---- FIN -----


Int_b = sum(G(X)/length(X))
Int_b

#Veamos el valor obtenido con R
Ireal = integrate(g,0,Inf)
Ireal

#Así, el error absoluto cometido es:
abs(Int_b-Ireal$value)

# con error estándar, SE= sqrt( sum((G(X)- mean(G(X)))^2)/(N-1) )
SE_b = sqrt( sum((G(X)- mean(G(X)))^2)/(length(X)-1) )
SE_b

#----------------------------------------------------------------------------------------------------------------
#Inciso c) Aqui se trabaja por el mètodo general de aceptacion y rechazo pq la inversa resulta 0 ---------------
#----------------------------------------------------------------------------------------------------------------
  
# Condideramos G(x)= sx^2*cos(X)
G = function(x){2*cos(x)}

# Así, X ~ funcion distribucion con funcion de densidad:
fx = function(x){(1/2)*x^2*exp(-x)}

# Simulamos X por el mètodo general de aceptaion y rechazo

# AR general
#-------------

# fx funcion de densidad
# Intervalo [0,infty]

# g funcion de densidad Cauchy(0,1)

# Queremos f <= M*g
# f/g <= M

# g no envuelve f, pero podemos encotnrar una M tq f < Mg.
#  M = max( f/g) es  7.84
# tomamos M=8
#Veamos como ahora, Mg(x) envuelve f(x)
x = seq(0,5,by=0.001)
y = fx(x)
env = dcauchy(x,0,1)
df <- data.frame(x,y,env)
gg <- ggplot(df, aes(x))
gg <- gg + geom_line(aes(y=y, colour="f(x)"))
gg <- gg + geom_line(aes(y=8*env, colour="M*g(x)"))
gg <- gg + scale_colour_manual('Density', values = c('red', 'green'))
gg


AR=AR_general(8, 100000,fx)


#Aproximació
T = data.frame(AR$sample.x, accept = factor(AR$accept, levels= c('Yes','No')))
ga <- qplot(AR$sample.x, data = T, geom = 'density', color = AR$accept)
ga <- ga + labs(title = "fx from Cauchy(0,1)",  x = "x", y = "density", color = "Accept") +
  coord_cartesian(ylim = c(0,0.6), xlim=c(0,8))
#Densitat real
x = seq(0,8,by=0.001)
y = fx(x)
df <- data.frame(x,y)
gr <- ggplot(df, aes(x))+ geom_line(aes(y=y),  colour="deepskyblue1")
gr <- gr + labs(title = "fx from  R",  x = "x", y = "density") + 
  coord_cartesian(ylim = c(0,0.6), xlim=c(0,8))

multiplot(ga,gr,cols=2)

# X seran los elementos de la muestra que hemos aceptado.
X=T[T$accept=='Yes',]$AR.sample.x

# ---- FIN -----


Int_c = sum(G(X)/length(X))
Int_c

#Veamos el valor obtenido con R
Ireal = integrate(g,0,Inf)
Ireal

#Así, el error absoluto cometido es:
abs(Int_c-Ireal$value)

# con error estándar, SE= sqrt( sum((G(X)- mean(G(X)))^2)/(N-1) )
SE_c = sqrt( sum((G(X)- mean(G(X)))^2)/(length(X)-1) )
SE_c

#----------------------------------------------------------------------------------------------------------------
#Inciso d) Aqui se trabaja por el mètodo general de aceptacion y rechazo pq la inversa resulta 0 ---------------
#----------------------------------------------------------------------------------------------------------------
  
# Condideramos G(x)= sx^2*cos(X)
G = function(x){pi*(x^2+1)*cos(x)*x^2*exp(-x)}

# Así, X ~ funcion distribucion con funcion de densidad:
fx = function(x){(1/pi)*1/(1+x^2)}

integrate(fx, 0, Inf)
# no nos sirve como f. densidad en 0, infty
# Deifinimos (mult*2)
fx = function(x){2*(1/pi)*1/(1+x^2)}
G = function(x){0.5*pi*(x^2+1)*cos(x)*x^2*exp(-x)}

# Simulamos X por el mètodo general de aceptaion y rechazo

# AR general
#-------------

# fx funcion de densidad
# Intervalo [0,infty]

# g funcion de densidad Cauchy(0,1)

# Queremos f <= M*g
# f/g <= M

# g no envuelve f, pero podemos encotnrar una M tq f < Mg.
#  M = max( f/g) es  2
# tomamos M=2
#Veamos como ahora, Mg(x) envuelve f(x) (2.1 porque queda justo encima)
x = seq(0,5,by=0.001)
y = fx(x)
env = dcauchy(x,0,1)
df <- data.frame(x,y,env)
gg <- ggplot(df, aes(x))
gg <- gg + geom_line(aes(y=y, colour="f(x)"))
gg <- gg + geom_line(aes(y=2.1*env, colour="M*g(x)"))
gg <- gg + scale_colour_manual('Density', values = c('red', 'green'))
gg


AR=AR_general(2, 100000,fx)


#Aproximació
T = data.frame(AR$sample.x, accept = factor(AR$accept, levels= c('Yes','No')))
ga <- qplot(AR$sample.x, data = T, geom = 'density', color = AR$accept)
ga <- ga + labs(title = "fx from Cauchy(0,1)",  x = "x", y = "density", color = "Accept") +
  coord_cartesian(ylim = c(0,0.8), xlim=c(0,8))
#Densitat real
x = seq(0,8,by=0.001)
y = fx(x)
df <- data.frame(x,y)
gr <- ggplot(df, aes(x))+ geom_line(aes(y=y),  colour="lightcoral")
gr <- gr + labs(title = "fx from  R",  x = "x", y = "density") + 
  coord_cartesian(ylim = c(0,0.8), xlim=c(0,8))

multiplot(ga,gr,cols=2)

# X seran los elementos de la muestra que hemos aceptado.
X=T[T$accept=='Yes',]$AR.sample.x

# ---- FIN -----

X = subset(X, X>0)
length(X)
Int_d = sum(G(X)/length(X))
Int_d

#Veamos el valor obtenido con R
Ireal = integrate(g,0,Inf)
Ireal

#Así, el error absoluto cometido es:
abs(Int_d-Ireal$value)

# con error estándar, SE= sqrt( sum((G(X)- mean(G(X)))^2)/(N-1) )
SE_d = sqrt( sum((G(X)- mean(G(X)))^2)/(length(X)-1) )
SE_d


#-------------
# Comparar
#------------

matrix(rbind( c(Int_a, abs(Int_a-Ireal$value),  SE_a), c(Int_b, abs(Int_b-Ireal$value), SE_b),
              c(Int_c, abs(Int_c-Ireal$value), SE_c), c(Int_d, abs(Int_d-Ireal$value),SE_d)), 
       ncol=3, nrow =4,
       dimnames= list(c("a","b","c","d"),c("Int","error","SE")))

# SE menor es el de c) --> 1.255185
# Tambien es el que comete menor error ---> 0.0002949284


#############################################################################################################
######################Hoja 3 Ejercicio 6. Integracion por importancia   #####################################
#############################################################################################################

# Queremos aproximar la integral de g(x) en [0,1]
# Definimos la función 
g = function(x){
  sqrt(1-x^2)
}

#Dibujemos la funcion
x = seq(0,1,by=0.001)
y = g(x)
df <- data.frame(x,y)
gg<- ggplot(df, aes(x))
gg <- gg + geom_line(aes(y=y), colour="lightcoral")
gg

# Segun el mètodo de integración por importancia, fx optima debe ser

# | sqrt(1-x^2) | / (integral...)

# Així, la descomposició que més s'assembla és la de l'apartat a).

# A continuació, veurem quina descomposició és la millor. És a dir, a quin 
# apartat obtenim un error estàndar més petit.

#----------------------------------------------------------------------------------------------------------------
# Apartado a) ---------------------------------------------------------------------------------------------------
#----------------------------------------------------------------------------------------------------------------

# Condideramos G(x)
G = function(x){sqrt(1-x^2)/((3/2)*(1-x^2))}

# Así, X ~ funcion distribucion con funcion de densidad:
fx = function(x){(3/2)*(1-x^2)}

integrate(fx, 0,1)

# Simulamos X por el mètodo simple de aceptaion y rechazo

# AR simple
#-------------

# Definimos la funcion aceptación rechazo simple
#   N: numero de muestras
#   c: maximo función
#   myf: función
AR_simple <- function(c,N,myf,I){
  
  accept = c()
  # Muestra de una U[0,1]
  sample.x = runif(N,I[1],I[2])
  for(i in 1:length(sample.x)){
    U = runif(1, 0, 1)
    if(c*U <= myf(sample.x[i])) { 
      accept[i] = 'Yes' 
    } 
    else if(c*U > myf(sample.x[i])) {
      accept[i] = 'No'
    }
  }
  
  return(list(accept=accept, sample.x=sample.x))
  
}
# Creamos 10000 muestras (aprox)
aR_sample=AR_simple(3/2,100000,fx,c(0,1))

# Vegem que ho hem aproximat bé

# Aproximació
# Data frame amb els acceptats i rebutjats
T = data.frame(aR_sample$sample.x, accept = factor(aR_sample$accept, levels= c('Yes','No')))
ga <- qplot(aR_sample$sample.x, data = T, geom = 'density', color = aR_sample$accept)
ga <- ga + labs(title = "f(x) from AR simple",  x = "x", y = "density", color = "Accept") + 
  coord_cartesian(ylim = c(0,1.5), xlim=c(0,1))

# Funció real
gr <- ggplot(data.frame(x=c(0, 1)), aes(x)) + stat_function(fun=fx,  colour="deepskyblue1")
gr <- gr + labs(title = "f(x) from  R",  x = "x", y = "density") + 
  coord_cartesian(ylim = c(0,1.5), xlim=c(0,1))

multiplot(ga,gr,cols=2)

# X seran los elementos de la muestra que hemos aceptado.
X=T[T$accept=='Yes',]$aR_sample.sample.x

# ---- FIN -----

length(X)
Int_a = sum(G(X)/length(X))
Int_a

#Veamos el valor obtenido con R
Ireal = integrate(g,0,1)
Ireal

#Así, el error absoluto cometido es:
abs(Int_a-Ireal$value)

# con error estándar, SE= sqrt( sum((G(X)- mean(G(X)))^2)/(N-1) )
SE_a = sqrt( sum((G(X)- mean(G(X)))^2)/(length(X)-1) )
SE_a

#----------------------------------------------------------------------------------------------------------------
# Apartado b) ---------------------------------------------------------------------------------------------------
#----------------------------------------------------------------------------------------------------------------

# Condideramos G(x)
G = function(x){sqrt(1-x^2)/((5/4)*(1-x^4))}

# Así, X ~ funcion distribucion con funcion de densidad:
fx = function(x){(5/4)*(1-x^4)}

integrate(fx, 0,1)

# Simulamos X por el mètodo simple de aceptaion y rechazo

# AR simple
#-------------

aR_sample=AR_simple(5/4,100000,fx,c(0,1))

# Vegem que ho hem aproximat bé

# Aproximació
# Data frame amb els acceptats i rebutjats
T = data.frame(aR_sample$sample.x, accept = factor(aR_sample$accept, levels= c('Yes','No')))
ga <- qplot(aR_sample$sample.x, data = T, geom = 'density', color = aR_sample$accept)
ga <- ga + labs(title = "f(x) from AR simple",  x = "x", y = "density", color = "Accept") + 
  coord_cartesian(ylim = c(0,1.5), xlim=c(0,1))

# Funció real
gr <- ggplot(data.frame(x=c(0, 1)), aes(x)) + stat_function(fun=fx,  colour="deepskyblue1")
gr <- gr + labs(title = "f(x) from  R",  x = "x", y = "density") + 
  coord_cartesian(ylim = c(0,1.5), xlim=c(0,1))

multiplot(ga,gr,cols=2)

# X seran los elementos de la muestra que hemos aceptado.
X=T[T$accept=='Yes',]$aR_sample.sample.x

# ---- FIN -----

length(X)
Int_b = sum(G(X)/length(X))
Int_b

#Veamos el valor obtenido con R
Ireal = integrate(g,0,1)
Ireal

#Así, el error absoluto cometido es:
abs(Int_a-Ireal$value)

# con error estándar, SE= sqrt( sum((G(X)- mean(G(X)))^2)/(N-1) )
SE_b = sqrt( sum((G(X)- mean(G(X)))^2)/(length(X)-1) )
SE_b

#----------------------------------------------------------------------------------------------------------------
# Apartado c) ---------------------------------------------------------------------------------------------------
#----------------------------------------------------------------------------------------------------------------

# Condideramos G(x)
G = function(x){sqrt(1-x^2)/((3/4)*(2-2*x^2))}

# Así, X ~ funcion distribucion con funcion de densidad:
fx = function(x){(3/4)*(2-2*x^2)}

integrate(fx, 0,1)

# Simulamos X por el mètodo simple de aceptaion y rechazo

# AR simple
#-------------

aR_sample=AR_simple(3/2,100000,fx,c(0,1))

# Vegem que ho hem aproximat bé

# Aproximació
# Data frame amb els acceptats i rebutjats
T = data.frame(aR_sample$sample.x, accept = factor(aR_sample$accept, levels= c('Yes','No')))
ga <- qplot(aR_sample$sample.x, data = T, geom = 'density', color = aR_sample$accept)
ga <- ga + labs(title = "f(x) from AR simple",  x = "x", y = "density", color = "Accept") + 
  coord_cartesian(ylim = c(0,1.5), xlim=c(0,1))

# Funció real
gr <- ggplot(data.frame(x=c(0, 1)), aes(x)) + stat_function(fun=fx,  colour="deepskyblue1")
gr <- gr + labs(title = "f(x) from  R",  x = "x", y = "density") + 
  coord_cartesian(ylim = c(0,1.5), xlim=c(0,1))

multiplot(ga,gr,cols=2)

# X seran los elementos de la muestra que hemos aceptado.
X=T[T$accept=='Yes',]$aR_sample.sample.x

# ---- FIN -----

length(X)
Int_c = sum(G(X)/length(X))
Int_c

#Veamos el valor obtenido con R
Ireal = integrate(g,0,1)
Ireal

#Así, el error absoluto cometido es:
abs(Int_a-Ireal$value)

# con error estándar, SE= sqrt( sum((G(X)- mean(G(X)))^2)/(N-1) )
SE_c = sqrt( sum((G(X)- mean(G(X)))^2)/(length(X)-1) )
SE_c

#-------------
# Comparar
#------------

matrix(rbind( c(Int_a, abs(Int_a-Ireal$value),  SE_a), c(Int_b, abs(Int_b-Ireal$value), SE_b),
              c(Int_c, abs(Int_c-Ireal$value), SE_c)), 
       ncol=3, nrow =3,
       dimnames= list(c("a","b","c"),c("Int","error","SE")))

# El millor sembla ser b), SE=0.1022780 (no com s'havia suposat)


