####################################################################################################
### Modelos No Param�tricos: ###
####################################################################################################
rm(list=ls())
cat("\014") ## limpia la pantalla del R

## Define el directorio de trabajo
setwd("C:/Users/usuario/Dropbox/11633 - Econometr�a para Datos Masivos/Bloque 4_Modelos No Param�tricos/Tema9_Clase Pr�ctica")

## Carga los datos
library(ISLR)
set.seed(11)
attach(College)
Private2=rep(0,nrow(College))
Private2[College$Private == "Yes"]= 1
datos <- data.frame(College)
datos$Private = Private2

set.seed(11)
train.size = dim(datos)[1] / 2
train = sample(1:dim(datos)[1], train.size)
test = -train
datos.train = datos[train, ]
datos.test = datos[test, ]

dim(datos.train)
dim(datos.test)

datos.test = datos.test[-1,]
