#Elian Gonzalez Hernandez

#--------------------------------------------------------------------------------------------
#Pregunta 1
#--------------------------------------------------------------------------------------------

#Esta secuencia es para dibujar la funcion.Alerta debo fijarme en el intervalo de la integral
x = seq(0,2,by=0.001)
f = function(x){((x^2)+cos(x))}
y = f(x)
df <- data.frame(x,y)

g <- ggplot(df, aes(x))
g <- g + geom_line(aes(y=y), colour="red")
g

#Creo una uniforme en el intervalo de la integral y escogi por que quiero una muestra de 100000
sample.x = runif(100000,0,2)
accept = c()
sample.accept = c()

#primero hayar el maximo de la funcion f = function(x){((x^2)+cos(x))} para utilizarlo dentro del for
#Alertaaaa hay que encerrar cada funcion entre parentesis por separado pq sino no lo calcula bien
#Se escribiria de la sgte forma---- max[((x^2)+cos(x))] in [0,2]
#Se obtiene ---- (4+(cos(2))

for(i in 1:length(sample.x)){
  #Genero la uniforme siempre en el intervalo[0,1] dentro del for
  U = runif(1, 0, 2)
  #Tomo los valores del sample en el mismo intervalo de la integral [0,2] y sustituyo el valor del max = (4+(cos(2))
  if(dunif(sample.x[i], 0,2)*(4+cos(2))*U <= f(sample.x[i])) { 
    accept[i] = 'Yes'
    sample.accept[i] = 1
  }
  #Tomo los valores del sample en el mismo intervalo de la integral [0,2] y sustituyo el valor del max = (4+(cos(2))
  else if(dunif(sample.x[i],0,2)*(4+cos(2))*U > f(sample.x[i])) {
    accept[i] = 'No'
    sample.accept[i] = 0
  }
}

#Esta es la formula que me va a dar el valor de la integral I=c(b-a)p en hit and miss
#phat es mi p es todos los aceptados entre el tamano total de mi muestra
#c es el maximo de la funcion que se obtiene arriba en este caso es (4+cos(2))
#(b-a) es la resta de los intervalos de la integral en este caso es (2-(0))
phat = sum(sample.accept)/100000
phat

#Aqui se aplica la formula
I = (4+cos(2))*(2)*phat
I  ##El resultado de la integral es 3.567224

#Aqui se compara con la integral que calcula R 
Ireal = integrate(f,0,2)
Ireal  ##3.575964 with absolute error < 4e-14


#--------------------------------------------------------------------------------------------
#Pregunta 2 inciso a
#--------------------------------------------------------------------------------------------

#Aqui pongo los intervalos que me dan y escojo el len osea la muestra que quiera
x <- seq(0, 1, len = 100000)

#Aqui pongo la funcion para dibujarla
f = function(x){(2*pi*sqrt(2-(2*(x^4))))}
plot(f,0,1)

#Creo una uniforme en el intervalo de la integral y escogi por que quiero una muestra de 100000
sample.x = runif(100000,0,1) 
accept = c()
sample.accept = c()

#En el metodo de aceptacion y rechazo general hay que hayar el maximo de la divicion de dos funciones de densidad 
#(siempre es la funcion de densidad aqui),en este caso me piden hayar la fun density f(x)(0,1) a partir de una Uniforme(0,1)

#Primero se hayan las dos funciones de densidad y luego se dividen para al final encontrar el maximo de esa divicion.
#Cuando se trata de buscar la funcion de densidad es mejor buscarla por la funsion de distribucion y ahi aparecen las dos.
#Ya me dan la funcion de densidad f(x): (5/8)*(2-(2*(x^4)))
#De esta forma se busca la func distribution de la uniforme ---- uniform distribution min = 0,  max=1
#y se escoge la de densidad ----- en este caso da como resultado 1
#Luego se busca el maximo de la divicion entre ella de la sgte forma: 
#Alertaaaa hay que encerrar cada funcion entre parentesis por separado pq sino no lo calcula bien
#Se escribiria de la sgte forma---- max[ ((5/8)*(2-(2*(x^4))))/1 ] in [0,1]
#Se obtiene ---- 5/4 y es el que se utiliza en el for que es el maximo de la divicion entre ellas


for(i in 1:length(sample.x)){
  U = runif(1, 0, 1)
  if(dunif(sample.x[i], 0, 1)*(5/4)*U <= f(sample.x[i])) { 
    accept[i] = 'Yes'
    sample.accept[i] = sample.x[i]
  } 
  else if(dunif(sample.x[i],0,1)*(5/4)*U > f(sample.x[i])) {
    accept[i] = 'No'
    sample.accept[i] = 0
  }
}


#Aqui guardo en un data frame todos los aceptados y rechazados
T = data.frame(sample.x, accept = factor(accept, levels= c('Yes','No')),sample.accept)

#Aqui guardo solo los aceptados en Taccept
Taccept = T[accept == 'Yes',]

#Aqui aplico la funcion G = (2*pi*sqrt(2-(2*(x^4))))/(5/8)*(2-(2*(x^4))) y saco la longitud de los aceptados
G = function(x){(2*pi*sqrt(2-(2*(x^4))))/((5/8)*(2-(2*(x^4))))}
Naccept = length(which(T$accept == 'Yes'))

#Aqui continuo aplicando el metodo demonte carlos y esta formula es la que me va a dar 
#el valor aproximado de la integral I=sum(x)/n donde mis x son los Taccept$sample.accept 
#y n es la cantidad total de los aceptados Naccept
I = sum(G(Taccept$sample.accept))/Naccept
I  ##Este es el valor aproximado de la integral 8.064748

#Aqui compruebo con el resultado de R
F = function(x){(2*pi*sqrt(2-(2*(x^4))))}
Ireal = integrate(F,0,1)
Ireal  #7.76633 with absolute error < 0.00049


#--------------------------------------------------------------------------------------------
#Pregunta 2 inciso b
#--------------------------------------------------------------------------------------------

#Aqui hago el metodo de hit and miss para comparar con el resultado que me dio como me piden

#Esta secuencia es para dibujar la funcion
x = seq(0, 1, len = 100000)
f = function(x){2*pi*sqrt(2-(2*(x^4)))}
y = f(x)
df <- data.frame(x,y)

g <- ggplot(df, aes(x))
g <- g + geom_line(aes(y=y), colour="red")
g

#Creo una uniforme en el intervalo de la integral y escogi por que quiero una muestra de 100000
sample.x = runif(100000,0,1)
accept = c()
sample.accept = c()

#primero hayar el maximo de la funcion f = function(x){2*pi*sqrt(2-(2*(x^4)))} para utilizarlo dentro del for
#Alertaaaa hay que encerrar cada funcion entre parentesis por separado pq sino no lo calcula bien
#Se escribiria de la sgte forma---- max[2*pi*sqrt(2-(2*(x^4)))] in [0,1]
#Se obtiene ---- 2*pi*sqrt(2) como maximo

for(i in 1:length(sample.x)){
  U = runif(1, 0, 1)
  if(dunif(sample.x[i], 0, 1)*(2*pi*sqrt(2))*U <= f(sample.x[i])) { 
    accept[i] = 'Yes'
    sample.accept[i] = 1
  } 
  else if(dunif(sample.x[i],0,1)*(2*pi*sqrt(2))*U > f(sample.x[i])) {
    accept[i] = 'No'
    sample.accept[i] = 0
  }
}

#Esta es la formula que me va a dar el valor de la integral I=c(b-a)p en hit and miss
#phat es mi p es todos los aceptados entre el tamano total de mi muestra
phat = sum(sample.accept)/100000
phat

#Aqui se aplica la formula para calcular la integral
I = (2*pi*sqrt(2))*(1-(0))*phat
I  ## Este es el valor de la integral resultante 7.762694

#Aqui se compara con la integral que calcula R 
Ireal = integrate(f,0,1)
Ireal  ##Este es el resultado de la integral calculado por R es: 7.76633 with absolute error < 0.00049

