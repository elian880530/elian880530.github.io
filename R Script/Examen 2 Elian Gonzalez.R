#Elian Gonzalez Hernandez

#--------------------------------------------------------------------------------------------
#Pregunta 1
#1) Estimar el porcentage de hogares con television por cable en casa con y sin usar el factor de elevacion.
#--------------------------------------------------------------------------------------------

TIC = read.csv2("HOGAR_SP.csv",header=TRUE,sep=",")
attach(TIC)
names(TIC)
str(factor_h)
factor_h = as.numeric(as.character(factor_h))
tvc = as.factor(as.character(tvc))

# Construimos una función que
actualiza.pn<-function(p,n,x,fe){
  if (x==1) { # Si x es un 1 (Sí), actualiza esta estimación con el factor de elevación
    pa<-p+fe
    na<-n+fe
  } else {
    pa<-p
    if (x==6) na<-n+fe # Si x es un 6 (No)
    else na<-n
  }
  return(list(p=pa,n=na))
}
porcentaje.encuesta<-function(variable,prov,codiProv="tot",factorElev){ # Variable, provincia, el código, y el factor de elevación
  n<-length(variable) # Calculamos el tamaño
  if (missing(factorElev) || factorElev==1) fe<-rep(1,n) # Si no hay factor de elevación asignalo como 1
  else fe<-factorElev # Sino, es el propio valor
  pn<-list(p=0,n=0) # Iniciamos el cuenteo de la proporción y n
  for (i in 1:n){ # Para cada observación
    if (codiProv=="tot") pn<-actualiza.pn(pn$p,pn$n,variable[i],fe[i]) #Si el código de la provincia es tot, asigna a todo
    else if (prov[i]==codiProv) pn<-actualiza.pn(pn$p,pn$n,variable[i],fe[i])  # Sino a las provincias asignadas
  }
  return(pn$p/pn$n)
}


summary(tvc) # Toma 1, 6 y 9. El 1 identifica Sí, el 6 No, y el 9 Ausencia de Datos (es igual que un table(TELEV))
# Proporción de los hogares que tienen television por cable en casa pero sin factor de elevación (en general todos con 1):
porcentaje.encuesta(tvc)
# Proporción de los hogares que tienen television por cable en casa teniendo en cuenta el factor de elevación:
porcentaje.encuesta(tvc, factorElev = factor_h)


#--------------------------------------------------------------------------------------------
#Pregunta 2
#2) Determinar el tamaÒo muestral para que el error de estimaciÛn de un porcentage sea inferior, 
# en valor absoluto, a 0.025 con una probabilidad del 95%. Considera tanto un muestreo con como sin reposiciÛn.
#--------------------------------------------------------------------------------------------

# Con Reposici'on:
n.cr = qnorm(1-0.025)^2*0.25/0.025^2
n.cr

# Sin Reposici'on:
n.sr = qnorm(1-0.025)^2*0.25*length(tvc)/(0.025^2*(length(tvc)-1)+qnorm(1-0.025)^2*0.25)
n.sr  # Cogemos como valor de n 855


#--------------------------------------------------------------------------------------------
#Pregunta 3
#3) Obtener una muestra sin reposiciÛn de dicho tamaÒo de la anterior base
#de datos y estimar el porcentage de hogares con televisiÛn por cable en casa
#en dicha muestra (con y sin usar el factor de elevaciÛn).
#--------------------------------------------------------------------------------------------

muestra<-function(x,n,replace=F,prob=NULL){ # Función para seleccionar una muestra de los datos x, de n elementos, sin remplazo y con probabilidad uniforme en todos los datos
  s <- sample(1:length(x[[1]]),n,replace,prob)
  return(x[s,])
}

#Con reposicion:

# Sumamos el total de los factores de elevación, que ha de dar N, el número total de hogares:
sum(factor_h)
#Es para saber cuál es el valor por el que se ha de normalizar la "probabilidad" de pertenecer a la muestra que se
#calcula justamente después:
sum(1/factor_h)
# Vayamos a hacer un muestreo con los mismos factores de elevació que tenía .
#Como la inversa del factor es la probabilidad de incursión en la muestra,
#es decir, la de aparición diremos que p=1/factor, y la normalizamos entre la suma de las inversas:
p<-(1/factor_h)/sum(1/factor_h)
# La suma ha de ser la unidad:
sum(p)

# Así las muestreamos con probabilidades diferentes, con sus correspondientes cada una respecto su factor de elevación:
mTIC.fe<-muestra(TIC,855,FALSE,prob=p) # 500 observaciones sin remplazo
porcentaje.encuesta(mTIC.fe$tvc, factorElev = factor_h) # Obtenemos una estimación mediante una muestra del valor anterior, vemos que tiene una similitud

#Sin reposicion:

# Así las muestreamos con probabilidades diferentes, con sus correspondientes cada una respecto su factor de elevación:
mTIC.nofe<-muestra(TIC,855,FALSE) # 500 observaciones sin remplazo
porcentaje.encuesta(mTIC.nofe$tvc)
