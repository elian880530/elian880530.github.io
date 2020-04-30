rm(list=ls())
cat("\014") ## limpia la pantalla del R

#Definicion del directorio de trabajo
setwd("D:/Trabajo con Mineria de Datos Orientadas al Turismo/Trabajo Final")

# Librerias que seran utilizadas
library(leaps)        #Paquete para el mco con mejor seleccion de subconjunto
library(glmnet)       #Paquete para el Ridge
library(foreach)      #Paquete para el Ridge
library(pls)          #Paquete para Analisis de Comp Principales
library(caret)        #Paquete para el Lasso con Red Elastica

library(caTools)      #Paquete para el analisis de regresiones de arbol
library(rpart)        #Paquete para el analisis de regresiones de arbol
library(rpart.plot)   #Paquete para el analisis de regresiones de arbol
library(randomForest) #Paquete para el analisis de bosques aleatorios
library(pls)

library(ggmap)        #Paquete para usar mapas de Google
library(ggplot2)      #Paquete para construir graficos
library(dplyr)        #Paquete para la manipulacion de datos
library(stringr)      #Paquete para la edicion de texto


###################################
#Cargamos los datos para el analisis

prueba.train = read.csv("AMDIT-BALEARES-TRAIN.csv", stringsAsFactors = TRUE, header = TRUE, sep=",")
prueba.test = read.csv("AMDIT-BALEARES-TEST.csv", stringsAsFactors = TRUE, header = TRUE, sep=",")

#Unificando los datos en un solo conjunto
mll_data = rbind(prueba.train,prueba.test)

#Organizando las columnas para tener la var dependiente
mll_data = mll_data[,c(8,1,2,3,4,5,6,7,9)]
prueba.train = prueba.train[,c(8,1,2,3,4,5,6,7,9)]
prueba.test = prueba.test[,c(8,1,2,3,4,5,6,7,9)]

# Via_entrada convertimos (Aeropuerto) en 1 y (Barco) en 0
mll_data$Via_entrada=ifelse(mll_data$Via_entrada == "Aeropuerto",1,0)
prueba.train$Via_entrada=ifelse(prueba.train$Via_entrada == "Aeropuerto",1,0)
prueba.test$Via_entrada=ifelse(prueba.test$Via_entrada == "Aeropuerto",1,0)

# Motivo convertimos (Ocio) en 1 y (Otros) en 0
mll_data$Motivo=ifelse(mll_data$Motivo == "Ocio",1,0)
prueba.train$Motivo=ifelse(prueba.train$Motivo == "Ocio",1,0)
prueba.test$Motivo=ifelse(prueba.test$Motivo == "Ocio",1,0)

# Convirtiendo en números los paises
mll_data$Pais = as.numeric(mll_data$Pais)
prueba.train$Pais = as.numeric(prueba.train$Pais)
prueba.test$Pais = as.numeric(prueba.test$Pais)

dim(mll_data)
head(mll_data)
names(mll_data)
str(mll_data)
summary(mll_data)

###################################
#Dividiendo los datos para entrenamiento y prueba

spl = sample.split(mll_data$ln_Gasto_Medio_pt, 0.7)
train = subset(mll_data, spl == TRUE)
test  = subset(mll_data, spl == FALSE)

#Guardando los valores originales en esta variable
prueba.mll_data = mll_data

x=model.matrix(ln_Gasto_Medio_pt~.,prueba.mll_data)[,-1] # Guardamos las variables independientes en una variable
attach(prueba.mll_data)
y=ln_Gasto_Medio_pt # La variable a predecir y
y.test=y[spl == FALSE] # Las y de prueba
test_ride = (spl == FALSE)
train_ride = (spl == TRUE)

test.ridge = c(1: dim(prueba.test)[1],dim(prueba.test)[1])
train.ridge = c(1: dim(prueba.train)[1],dim(prueba.train)[1])

##################
#MCO
##################

set.seed(1289)
mco.fit = lm(ln_Gasto_Medio_pt ~., data=prueba.train) # Creamos el modelo con el conjunto de entrenamiento
mco.pred = predict(mco.fit, newdata = prueba.test) # Predecimos los valores para el conjunto de entrenamiento
error.mco = mean((prueba.test[,"ln_Gasto_Medio_pt"] - mco.pred)^2)
error.mco

##############################
#MCO con metodo MSS con cv=10
##############################

set.seed(1289)
nvariables <- 8 # Trabajaremos con 8 variables independientes
regfit.full=regsubsets(ln_Gasto_Medio_pt ~ .,data=prueba.train,nvmax=nvariables)
summary(regfit.full) # Busca el mejor modelo de subconjuntos.

predict.regsubsets=function(object,newdata,id,...){ # Creamos una funciÃ³n que nos darÃ¡ los coeficientes de cada regresiÃ³n
  form=as.formula(object$call[[2]])
  mat=model.matrix(form,newdata)
  coefi=coef(object,id=id)
  xvars=names(coefi)
  mat[,xvars]%*%coefi
}
k = 10 # Fijamos una validaciÃ³n cruzada de 10 bloques
set.seed(1289)
folds=sample(1:k,nrow(prueba.train),replace=TRUE) # Creamos una mÃ¡scara para los datos de k bloques diferentes
folds
table(folds) # Vemos que cada bloque tiene un nÃºmero diferente
cv.errors = matrix(NA,k,8, dimnames =list(NULL , paste(1:8))) 
for(j in 1:k){
  best.fit=regsubsets(ln_Gasto_Medio_pt~.,data=prueba.train[folds!=j,], nvmax=8)
  for(i in 1:8){
    pred=predict.regsubsets(best.fit,prueba.train[folds==j,],id=i) # Predice con el modelo anterior sobre el bloque j
    cv.errors[j,i]=mean( (prueba.train$ln_Gasto_Medio_pt[folds==j]-pred)^2)
  }
}
rmse.cv=sqrt(apply(cv.errors,2,mean))
rmse.cv # El error de cada modelo con n variables
plot(rmse.cv,pch=19,type="b") # Hacemos el plot y vemos que el mejor es el de 4 variables
reg.best=regsubsets (ln_Gasto_Medio_pt~.,data=prueba.train , nvmax=8)
coef(reg.best, 8) # Vemos los coeficientes del mejor modelo

fit.final <- lm(ln_Gasto_Medio_pt ~ Pais + Anyo + Mes + Via_entrada + Motivo + ln_Turistas + ln_Gasto_total + ln_Mallorca_GoogleTrends, data = prueba.train) # Volvemos a hacer el fit a todos los datos de entrenamiento
summary(fit.final)

# Vayamos a predecir sobre el conjunto de prueba
mejor.pred = predict(fit.final, newdata = prueba.test)
error.mss.cv10 = mean((prueba.test[,"ln_Gasto_Medio_pt"] - mejor.pred)^2)
error.mss.cv10

#############################
#MCO con metodo MSS con cv=5
#############################

# Haremos validaciÃ³n cruzada solo sobre el bloque de entrenamiento y luego probaremos la funciÃ³n sobre el conjunto de test. 
k = 5 # Fijamos una validaciÃ³n cruzada de 5 bloques
set.seed(1289)
folds=sample(1:k,nrow(prueba.train),replace=TRUE) # Creamos una mÃ¡scara para los datos de k bloques diferentes
folds
table(folds) # Vemos que cada bloque tiene un nÃºmero diferente

cv.errors = matrix(NA,k,8, dimnames =list(NULL , paste(1:8))) # Aplicamos la validaciÃ³n cruzada con 8
#variables. Sin embargo, generemos un bucle que vaya aplicÃ¡ndola para cada conjunto de variables.
for(j in 1:k){
  best.fit=regsubsets(ln_Gasto_Medio_pt ~ .,data=prueba.train[folds!=j,], # Ajustame para todos los datos sin el del bloque j
                      nvmax=8)
  for(i in 1:8){
    pred=predict.regsubsets(best.fit,prueba.train[folds==j,],id=i) # Predice con el modelo anterior sobre el bloque j
    cv.errors[j,i]=mean( (prueba.train$ln_Gasto_Medio_pt[folds==j]-pred)^2)
  }
}
rmse.cv=sqrt(apply(cv.errors,2,mean))
rmse.cv # El error de cada modelo con n variables
plot(rmse.cv,pch=19,type="b") # Hacemos el plot y vemos que el mejor es el de 8 variables
reg.best=regsubsets (ln_Gasto_Medio_pt ~ .,data=prueba.train , nvmax=8)
coef(reg.best, 8) # Vemos los coeficientes del mejor modelo

fit.final <- lm(ln_Gasto_Medio_pt ~ Pais + Anyo + Mes + Via_entrada + Motivo + ln_Turistas + ln_Gasto_total + ln_Mallorca_GoogleTrends, data = prueba.train) # Volvemos a hacer el fit a todos los datos de entrenamiento
summary(fit.final)

# Vayamos a predecir sobre el conjunto de prueba
mejor.pred = predict(fit.final, newdata = prueba.test)
error.mss.cv5 = mean((prueba.test[,"ln_Gasto_Medio_pt"] - mejor.pred)^2)
error.mss.cv5 

##############################
#MCO con metodo msha con cv=5
##############################

k = 5 # Fijamos una validaciÃ³n cruzada de 5 bloques
set.seed(1289)
folds=sample(1:k,nrow(prueba.train),replace=TRUE) # Creamos una mÃ¡scara para los datos de k bloques diferentes
folds
table(folds) # Vemos que cada bloque tiene un nÃºmero diferente
cv.errors = matrix(NA,k,8, dimnames =list(NULL , paste(1:8))) # Aplicamos la validaciÃ³n cruzada con 8
#variables. Sin embargo, generemos un bucle que vaya aplicÃ¡ndola para cada conjunto de variables.
for(j in 1:k){
  best.fit=regsubsets(ln_Gasto_Medio_pt~.,data=prueba.train[folds!=j,], # Ajustame para todos los datos sin el del bloque j
                      nvmax=8, method = "forward") # Decimos que son steps hacia delante
  for(i in 1:8){
    pred=predict.regsubsets(best.fit,prueba.train[folds==j,],id=i) # Predice con el modelo anterior sobre el bloque j
    cv.errors[j,i]=mean( (prueba.train$ln_Gasto_Medio_pt[folds==j]-pred)^2)
  }
}
rmse.cv=sqrt(apply(cv.errors,2,mean))
rmse.cv # El error de cada modelo con n variables
plot(rmse.cv,pch=19,type="b") # Hacemos el plot
reg.best=regsubsets (ln_Gasto_Medio_pt~.,data=prueba.train , nvmax=8, method = "forward")
coef(reg.best, 8) # Vemos los coeficientes del mejor modelo

fit.final <- lm(ln_Gasto_Medio_pt ~ Pais + Anyo + Mes + Via_entrada + Motivo + ln_Turistas + ln_Gasto_total + ln_Mallorca_GoogleTrends, data = prueba.train) # Volvemos a hacer el fit a todos los datos de entrenamiento
summary(fit.final)

# Vayamos a predecir sobre el conjunto de prueba
mejor.pred.ad = predict(fit.final, newdata = prueba.test)
error.msha.cv5 = mean((prueba.test[,"ln_Gasto_Medio_pt"] - mejor.pred.ad)^2)
error.msha.cv5 

##############################
#MCO con metodo msha con cv=10
##############################

k = 10 # Fijamos una validaciÃ³n cruzada de 5 bloques
set.seed(1289)
folds=sample(1:k,nrow(prueba.train),replace=TRUE) # Creamos una mÃ¡scara para los datos de k bloques diferentes
folds
table(folds) # Vemos que cada bloque tiene un nÃºmero diferente
cv.errors = matrix(NA,k,8, dimnames =list(NULL , paste(1:8))) # Aplicamos la validaciÃ³n cruzada con 8
#variables. Sin embargo, generemos un bucle que vaya aplicÃ¡ndola para cada conjunto de variables.
for(j in 1:k){
  best.fit=regsubsets(ln_Gasto_Medio_pt~.,data=prueba.train[folds!=j,], # Ajustame para todos los datos sin el del bloque j
                      nvmax=8, method = "forward") # Decimos que son steps hacia delante
  for(i in 1:8){
    pred=predict.regsubsets(best.fit,prueba.train[folds==j,],id=i) # Predice con el modelo anterior sobre el bloque j
    cv.errors[j,i]=mean( (prueba.train$ln_Gasto_Medio_pt[folds==j]-pred)^2)
  }
}
rmse.cv=sqrt(apply(cv.errors,2,mean))
rmse.cv # El error de cada modelo con n variables
plot(rmse.cv,pch=19,type="b") # Hacemos el plot
reg.best=regsubsets (ln_Gasto_Medio_pt~.,data=prueba.train , nvmax=8, method = "forward")
coef(reg.best, 8) # Vemos los coeficientes del mejor modelo

fit.final <- lm(ln_Gasto_Medio_pt ~ Pais + Anyo + Mes + Via_entrada + Motivo + ln_Turistas + ln_Gasto_total + ln_Mallorca_GoogleTrends, data = prueba.train) # Volvemos a hacer el fit a todos los datos de entrenamiento
summary(fit.final)

# Vayamos a predecir sobre el conjunto de prueba
mejor.pred.ad = predict(fit.final, newdata = prueba.test)
error.msha.cv10 = mean((prueba.test[,"ln_Gasto_Medio_pt"] - mejor.pred.ad)^2)
error.msha.cv10 

##################
#Ridge cv=10
##################

# Riege con cv=10
set.seed(1289)
grid=10^seq(10,-2, length =100) # Generamos las lambdas
cv.ridge=cv.glmnet(x[train.ridge ,],y[train.ridge],alpha=0,lambda=grid) 
plot(cv.ridge) 
mejorlambda=cv.ridge$lambda.min
mejorlambda

#Rige sin la validaciÃ³n cruzada para obtener los valores finales
ridge.mod=glmnet(x[train.ridge ,],y[train.ridge],alpha=0,lambda=grid)
ridge.pred=predict(ridge.mod,s=mejorlambda ,newx=x[test.ridge ,])
error.ridge.cv10 = mean((ridge.pred - prueba.test[,"ln_Gasto_Medio_pt"])^2)
error.ridge.cv10


##################
#Ridge cv=5
##################

set.seed(1289)
cv.ridge=cv.glmnet(x[train_ride ,],y[train_ride],alpha=0,lambda=grid, nfolds = 5) 
plot(cv.ridge) 
mejorlambda=cv.ridge$lambda.min
mejorlambda 
ridge.mod=glmnet(x[train_ride ,],y[train_ride],alpha=0,lambda=grid) # Hacemos ya el Rige sin la validaciÃ³n cruzada 
ridge.pred=predict(ridge.mod,s=mejorlambda ,newx=x[test_ride ,])
error.ridge.cv5 = mean((ridge.pred - prueba.test[,"ln_Gasto_Medio_pt"])^2)
error.ridge.cv5

##################
#Lasso con cv=10
##################

set.seed(1289)
cv.lasso=cv.glmnet(x[train_ride ,],y[train_ride],alpha=1, lambda = grid) # Alpha=1 para hacer LASSO
plot(cv.lasso)
bestlam=cv.lasso$lambda.min
bestlam
lasso.mod=glmnet(x[train_ride ,],y[train_ride],alpha=1,lambda=grid)
plot(lasso.mod)
lasso.pred=predict(lasso.mod,s=bestlam,newx=x[test_ride ,])
error.lasso.cv10 = mean((lasso.pred - prueba.test[,"ln_Gasto_Medio_pt"])^2)
error.lasso.cv10

##################
#Lasso con cv=5
##################

set.seed(1289)
cv.lasso=cv.glmnet(x[train_ride ,],y[train_ride],alpha=1, lambda = grid,nfolds = 5) # Alpha=1 para hacer LASSO
plot(cv.lasso)
bestlam=cv.lasso$lambda.min
bestlam
lasso.mod=glmnet(x[train_ride ,],y[train_ride],alpha=1,lambda=grid)
plot(lasso.mod)
lasso.pred=predict(lasso.mod,s=bestlam,newx=x[test_ride ,])
error.lasso.cv5 = mean((lasso.pred - prueba.test[,"ln_Gasto_Medio_pt"])^2)
error.lasso.cv5

#####################################################
#Analisis de Componmentes Principales (PLS) con cv=10
#####################################################

set.seed(1289)
pls.fit=plsr(ln_Gasto_Medio_pt~., data=prueba.train, scale=TRUE, validation="CV") 
summary(pls.fit) 
validationplot(pls.fit,val.type="MSEP", xlab = "Numero de Componentes Principales")
pls.cv <- crossval(pls.fit, segments = 10) 
plot(MSEP(pls.cv), legendpos="topright")
summary(pls.cv, what = "validation")
pls.pred=predict(pls.fit,newdata=x[test_ride,],ncomp=10)
pls.pred
error.pls = mean((pls.pred-prueba.test[,"ln_Gasto_Medio_pt"])^2)
error.pls 

########################
#LASSO CON RED ELASTICA
########################

lambda.grid <- 10^seq(2,-2, length = 100)
alpha.grid <- seq(0,1, length = 10)
lambda.grid
alpha.grid
Control <- trainControl(method = "repeatedcv", number = 10, repeats = 5)
busca.grid <- expand.grid(.alpha = alpha.grid, .lambda = lambda.grid)
busca.grid
set.seed(1289)
mi.entrenamiento <- train(ln_Gasto_Medio_pt~., data = prueba.train, method = "glmnet", 
                          tuneGrid = busca.grid, trControl = Control, 
                          standardize = TRUE, maxit = 100000)

plot(mi.entrenamiento)
attributes(mi.entrenamiento)
mi.entrenamiento$bestTune
mi.entrenamiento$finalModel

mi.modelo.glmnet <- mi.entrenamiento$finalModel
coef(mi.modelo.glmnet, s = mi.entrenamiento$bestTune$lambda)
mej.modelo <- glmnet(x[train_ride ,],y[train_ride], alpha=mi.entrenamiento$bestTune$alpha,
                     lambda = mi.entrenamiento$bestTune$lambda)

coef(mej.modelo, s = mi.entrenamiento$bestTune$lambda)
cbind(coef(mej.modelo, s = mi.entrenamiento$bestTune$lambda), coef(mi.modelo.glmnet, s = mi.entrenamiento$bestTune$lambda))
lre.pred <- predict(mej.modelo,s=mi.entrenamiento$bestTune$lambda,newx=x[test_ride ,])
error.lasso.re <- mean((lre.pred - y.test)^2)
error.lasso.re

