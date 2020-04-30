#Este codigo se ha ejecutado usando la version 3.4.3 de R. Algunos paquetes podrian no 
#estar disponibles en versiones menos recientes

rm(list=ls())
cat("\014") ## limpia la pantalla del R

#Definicion del directorio de trabajo
setwd("D:/Trabajo con Mineria de Datos Orientadas al Turismo/tarea 1")

# Librerias que seran utilizadas
library(leaps)        #Paquete para el mco con mejor seleccion de subconjunto
library(glmnet)       #Paquete para el Ridge
library(foreach)      #Paquete para el Ridge
library(caTools)      #Paquete para el analisis de regresiones de arbol
library(rpart)        #Paquete para el analisis de regresiones de arbol
library(rpart.plot)   #Paquete para el analisis de regresiones de arbol
library(randomForest) #Paquete para el analisis de bosques aleatorios
library(pls)

library(caret)        #Paquete para el Lasso con Red Elastica
library(ggmap)        #Paquete para usar mapas de Google
library(ggplot2)      #Paquete para construir graficos
library(dplyr)        #Paquete para la manipulacion de datos
library(stringr)      #Paquete para la edicion de texto


#Cargamos los datos para el analisis
mll_data = read.csv("listings.csv", stringsAsFactors = TRUE, header = TRUE, sep=",")

#Describimos los datos y descubrimos el nombre de las variables
str(mll_data)
names(mll_data)
head(mll_data)
dim(mll_data)


#Remover caracteres especiales de la variable neighbourhood_cleansed
levels(mll_data$neighbourhood_cleansed)
mll_data$neighbourhood_cleansed=gsub('Plan-les-Ouates', 'Plan les Ouates', mll_data$neighbourhood_cleansed)
mll_data$neighbourhood_cleansed=gsub('Aire-la-Ville', 'Aire la Ville', mll_data$neighbourhood_cleansed)
mll_data$neighbourhood_cleansed=gsub('AniÃ¨res', 'Aniares', mll_data$neighbourhood_cleansed)
mll_data$neighbourhood_cleansed=gsub('Grand-Saconnex', 'Grand Saconnex', mll_data$neighbourhood_cleansed)
mll_data$neighbourhood_cleansed=gsub('Commune de GenÃ¨ve', 'Commune de Genave', mll_data$neighbourhood_cleansed)
mll_data$neighbourhood_cleansed=gsub('ChÃªne-Bourg', 'Chane Bourg', mll_data$neighbourhood_cleansed)
mll_data$neighbourhood_cleansed=gsub('Pregny-ChambÃ©sy', 'Pregny Chambasy', mll_data$neighbourhood_cleansed)
mll_data$neighbourhood_cleansed=gsub('ThÃ´nex', 'Tha nex', mll_data$neighbourhood_cleansed)
mll_data$neighbourhood_cleansed=gsub('Collex-Bossy', 'Collex Bossy', mll_data$neighbourhood_cleansed)
mll_data$neighbourhood_cleansed=gsub('ChÃªne-Bougeries', 'Chane Bougeries', mll_data$neighbourhood_cleansed)
mll_data$neighbourhood_cleansed=gsub('Collonge-Bellerive', 'Collonge Bellerive', mll_data$neighbourhood_cleansed)
mll_data$neighbourhood_cleansed=gsub('Perly-Certoux', 'Perly Certoux', mll_data$neighbourhood_cleansed)

#Quitamos espacios en blancos que puedan estar al incio y final de variables
str_trim(mll_data$neighbourhood_cleansed, side=c("both"))

#Remover el simbolo del dolar de la variable precio
mll_data$price=as.numeric(gsub("\\$","",mll_data$price))

#Verificamos que la variable precios sea numerica
typeof(mll_data$price)

#Examinamos la variable
hist(mll_data$price)
mean(mll_data$price, na.rm = TRUE)

#El precio puede diferir dependiendo de la localidad. Podemos verificarlo a continuacion
tapply(mll_data$price, mll_data$neighbourhood_cleansed, mean, na.rm = TRUE)

#Alternativamente, este calculo se puede hacer de la siguiente manera
aggregate(price ~ neighbourhood_cleansed, data=mll_data, FUN=mean)

#Ahora, centremos la atencion en Ginebra y analicemos los precios
pm_map  = get_map(location = "Geneva", zoom = 15)
pm_data = mll_data[(mll_data$neighbourhood_cleansed=="Geneva"), ]

#Fijamos un indicador que muestre el precio 
pm_circle_size <- 0.005 

#Creemos un mapa que muestre las zonas de Ginebra y los precios
ggmap(pm_map, extent = "device") + geom_point(aes(x=longitude, y=latitude), 
                                              data=pm_data, col="red", alpha=0.1, size=pm_data$price*pm_circle_size) +
  scale_size_continuous(range=range(pm_data$price)) +
  ggtitle("Precios de los Airbnb por zonas en Ginebra")

#Podemos extender el analisis para el caso de Ginebra
mll_map = get_map(location = "Ginebra", zoom = 15)

#Generemos un mapa que muestre la densidad de las propiedades disponibles en Airbnb en Ginebra
ggmap(mll_map, extent = "device") +  geom_density2d(data = mll_data, aes(x = longitude, y =latitude), size = 0.3) +
  stat_density2d(data = mll_data, aes(x = longitude, y = latitude, fill = ..level.., alpha = ..level..), size = 0.01, bins = 16, geom = "polygon") + 
  scale_fill_gradient(low = "blue", high = "red",name = "Density") + scale_alpha(range = c(0,0.3), guide = FALSE) +
  ggtitle("Densidad en la distribucion de Airbnbs en Ginebra")

#Fijamos un indicador que muestre el precio 
mll_circle_size <- 0.005 

#Creemos un mapa que muestre las zonas de Ginebra y los precios
ggmap(mll_map, extent = "device") + geom_point(aes(x=longitude, y=latitude), 
                                               data=mll_data, col="red", alpha=0.3, size=mll_data$price*mll_circle_size) +
  scale_size_continuous(range=range(mll_data$price)) +
  ggtitle("Precios de los Airbnb por zonas en Ginebra")

##################
#Limpieza de datos
##################

#Descripcion de los datos antes de ajustar un modelo de regresion lineal que prediga el precio
aggregate(price ~ cancellation_policy, data=mll_data, FUN=mean)
aggregate(price ~ review_scores_value, data=mll_data, FUN=mean)
cor(mll_data$price, mll_data$review_scores_value, use="pairwise.complete.obs")
cor(mll_data$price, mll_data$review_scores_rating, use="pairwise.complete.obs")
aggregate(price ~ review_scores_rating, data=mll_data, FUN=mean)

# Detectando valores perdidos
all_missing_list=  colnames(mll_data)[colSums(is.na(mll_data)) > 0]
all_missing_list

# Rellenar valores medianos,loop a través del marco de datos, llenando cada columna con la mediana de
# los valores existentes para todo el conjunto de datos.¿Dónde todavía hay valores faltantes?
all_missing_list =  colnames(mll_data)[colSums(is.na(mll_data)) > 0]
length(all_missing_list) == 0

# Si se cumple la condicion todos los valores son imputados
for( i in 1:length(all_missing_list)){
  # Obtener la mediana global
  median_all = median(mll_data[,all_missing_list[i]], na.rm =TRUE)
  # Imputando los valores perdidos con la mediana
  mll_data[,all_missing_list[i]][is.na(mll_data[,all_missing_list[i]])] = median_all
}
length(all_missing_list) == 0


#Dividiendo los datos para entrenamiento y prueba
set.seed(11)
spl = sample.split(mll_data$price, 0.7)
train = subset(mll_data, spl == TRUE)
test  = subset(mll_data, spl == FALSE)
mean(train$price, na.rm = TRUE)
mean(test$price, na.rm = TRUE)

prueba.mll_data = mll_data[,c(61,56,80,77,60,50,49,91)]
prueba.train = train[,c(61,56,80,77,60,50,49,91)] 
prueba.test = test[,c(61,56,80,77,60,50,49,91)] 
 
dim(mll_data)
dim(train)
dim(test)
dim(prueba.mll_data)
dim(prueba.train)
dim(prueba.test)
names(prueba.train)

x=model.matrix(price~.,prueba.mll_data)[,-1] # Guardamos las variables independientes en una variable
attach(prueba.mll_data)
y=price # La variable a predecir y
y.test=y[spl == FALSE] # Las y de prueba
test_ride = (spl == FALSE)
train_ride = (spl == TRUE)

##################
#MCO
##################

set.seed(11)
mco.fit = lm(price ~., data=prueba.train) # Creamos el modelo con el conjunto de entrenamiento
mco.pred = predict(mco.fit, newdata = prueba.test) # Predecimos los valores para el conjunto de entrenamiento
error.mco = mean((prueba.test[,"price"] - mco.pred)^2)
error.mco

##############################
#MCO con metodo MSS con cv=10
##############################

set.seed(11)
nvariables <- 7 # Trabajaremos con 7 variables independientes
regfit.full=regsubsets(price ~ .,data=prueba.train,nvmax=nvariables)
summary(regfit.full) # Busca el mejor modelo de subconjuntos.

predict.regsubsets=function(object,newdata,id,...){ # Creamos una función que nos dará los coeficientes de cada regresión
  form=as.formula(object$call[[2]])
  mat=model.matrix(form,newdata)
  coefi=coef(object,id=id)
  xvars=names(coefi)
  mat[,xvars]%*%coefi
}
k = 10 # Fijamos una validación cruzada de 10 bloques
set.seed(11)
folds=sample(1:k,nrow(prueba.train),replace=TRUE) # Creamos una máscara para los datos de k bloques diferentes
folds
table(folds) # Vemos que cada bloque tiene un número diferente
cv.errors = matrix(NA,k,7, dimnames =list(NULL , paste(1:7))) # Aplicamos la validación cruzada con 17
#variables. Sin embargo, generemos un bucle que vaya aplicándola para cada conjunto de variables.
for(j in 1:k){
  best.fit=regsubsets(price~.,data=prueba.train[folds!=j,], # Ajustame para todos los datos sin el del bloque j
                      nvmax=7)
  for(i in 1:7){
    pred=predict.regsubsets(best.fit,prueba.train[folds==j,],id=i) # Predice con el modelo anterior sobre el bloque j
    cv.errors[j,i]=mean( (prueba.train$price[folds==j]-pred)^2)
  }
}
rmse.cv=sqrt(apply(cv.errors,2,mean))
rmse.cv # El error de cada modelo con n variables
plot(rmse.cv,pch=19,type="b") # Hacemos el plot y vemos que el mejor es el de 4 variables
reg.best=regsubsets (price~.,data=prueba.train , nvmax=7)
coef(reg.best, 7) # Vemos los coeficientes del mejor modelo

fit.final <- lm(price ~ bedrooms + review_scores_rating + number_of_reviews + square_feet + longitude + latitude + cancellation_policy, data = prueba.train) # Volvemos a hacer el fit a todos los datos de entrenamiento
summary(fit.final)

# Vayamos a predecir sobre el conjunto de prueba
mejor.pred = predict(fit.final, newdata = prueba.test)
error.mss.cv10 = mean((prueba.test[,"price"] - mejor.pred)^2)
error.mss.cv10

#############################
#MCO con metodo MSS con cv=5
#############################

# Haremos validación cruzada solo sobre el bloque de entrenamiento y luego probaremos la función sobre el conjunto de test. 
k = 5 # Fijamos una validación cruzada de 5 bloques
set.seed(11)
folds=sample(1:k,nrow(prueba.train),replace=TRUE) # Creamos una máscara para los datos de k bloques diferentes
folds
table(folds) # Vemos que cada bloque tiene un número diferente

cv.errors = matrix(NA,k,7, dimnames =list(NULL , paste(1:7))) # Aplicamos la validación cruzada con 7
#variables. Sin embargo, generemos un bucle que vaya aplicándola para cada conjunto de variables.
for(j in 1:k){
  best.fit=regsubsets(price ~ .,data=prueba.train[folds!=j,], # Ajustame para todos los datos sin el del bloque j
                      nvmax=7)
  for(i in 1:7){
    pred=predict.regsubsets(best.fit,prueba.train[folds==j,],id=i) # Predice con el modelo anterior sobre el bloque j
    cv.errors[j,i]=mean( (prueba.train$price[folds==j]-pred)^2)
  }
}
rmse.cv=sqrt(apply(cv.errors,2,mean))
rmse.cv # El error de cada modelo con n variables
plot(rmse.cv,pch=19,type="b") # Hacemos el plot y vemos que el mejor es el de 7 variables
reg.best=regsubsets (price ~ .,data=prueba.train , nvmax=7)
coef(reg.best, 7) # Vemos los coeficientes del mejor modelo

fit.final <- lm(price ~ bedrooms + review_scores_rating + number_of_reviews + square_feet + longitude + latitude + cancellation_policy, data = prueba.train) # Volvemos a hacer el fit a todos los datos de entrenamiento
summary(fit.final)

# Vayamos a predecir sobre el conjunto de prueba
mejor.pred = predict(fit.final, newdata = prueba.test)
error.mss.cv5 = mean((prueba.test[,"price"] - mejor.pred)^2)
error.mss.cv5 

##############################
#MCO con metodo msha con cv=5
##############################

k = 5 # Fijamos una validación cruzada de 5 bloques
set.seed(11)
folds=sample(1:k,nrow(prueba.train),replace=TRUE) # Creamos una máscara para los datos de k bloques diferentes
folds
table(folds) # Vemos que cada bloque tiene un número diferente
cv.errors = matrix(NA,k,7, dimnames =list(NULL , paste(1:7))) # Aplicamos la validación cruzada con 7
#variables. Sin embargo, generemos un bucle que vaya aplicándola para cada conjunto de variables.
for(j in 1:k){
  best.fit=regsubsets(price~.,data=prueba.train[folds!=j,], # Ajustame para todos los datos sin el del bloque j
                      nvmax=7, method = "forward") # Decimos que son steps hacia delante
  for(i in 1:7){
    pred=predict.regsubsets(best.fit,prueba.train[folds==j,],id=i) # Predice con el modelo anterior sobre el bloque j
    cv.errors[j,i]=mean( (prueba.train$price[folds==j]-pred)^2)
  }
}
rmse.cv=sqrt(apply(cv.errors,2,mean))
rmse.cv # El error de cada modelo con n variables
plot(rmse.cv,pch=19,type="b") # Hacemos el plot
reg.best=regsubsets (price~.,data=prueba.train , nvmax=7, method = "forward")
coef(reg.best, 7) # Vemos los coeficientes del mejor modelo

fit.final <- lm(price ~ bedrooms + review_scores_rating + number_of_reviews + square_feet + longitude + latitude + cancellation_policy, data = prueba.train) # Volvemos a hacer el fit a todos los datos de entrenamiento
summary(fit.final)

# Vayamos a predecir sobre el conjunto de prueba
mejor.pred.ad = predict(fit.final, newdata = prueba.test)
error.msha.cv5 = mean((prueba.test[,"price"] - mejor.pred.ad)^2)
error.msha.cv5 

##############################
#MCO con metodo msha con cv=10
##############################

k = 10 # Fijamos una validación cruzada de 5 bloques
set.seed(11)
folds=sample(1:k,nrow(prueba.train),replace=TRUE) # Creamos una máscara para los datos de k bloques diferentes
folds
table(folds) # Vemos que cada bloque tiene un número diferente
cv.errors = matrix(NA,k,7, dimnames =list(NULL , paste(1:7))) # Aplicamos la validación cruzada con 7
#variables. Sin embargo, generemos un bucle que vaya aplicándola para cada conjunto de variables.
for(j in 1:k){
  best.fit=regsubsets(price~.,data=prueba.train[folds!=j,], # Ajustame para todos los datos sin el del bloque j
                      nvmax=7, method = "forward") # Decimos que son steps hacia delante
  for(i in 1:7){
    pred=predict.regsubsets(best.fit,prueba.train[folds==j,],id=i) # Predice con el modelo anterior sobre el bloque j
    cv.errors[j,i]=mean( (prueba.train$price[folds==j]-pred)^2)
  }
}
rmse.cv=sqrt(apply(cv.errors,2,mean))
rmse.cv # El error de cada modelo con n variables
plot(rmse.cv,pch=19,type="b") # Hacemos el plot
reg.best=regsubsets (price~.,data=prueba.train , nvmax=7, method = "forward")
coef(reg.best, 7) # Vemos los coeficientes del mejor modelo

fit.final <- lm(price ~ bedrooms + review_scores_rating + number_of_reviews + square_feet + longitude + latitude + cancellation_policy, data = prueba.train) # Volvemos a hacer el fit a todos los datos de entrenamiento
summary(fit.final)

# Vayamos a predecir sobre el conjunto de prueba
mejor.pred.ad = predict(fit.final, newdata = prueba.test)
error.msha.cv10 = mean((prueba.test[,"price"] - mejor.pred.ad)^2)
error.msha.cv10 

##################
#Ridge cv=10
##################

# Riege con cv=10
set.seed(11)
grid=10^seq(10,-2, length =100) # Generamos las lambdas
cv.ridge=cv.glmnet(x[train_ride ,],y[train_ride],alpha=0,lambda=grid) 
plot(cv.ridge) 
mejorlambda=cv.ridge$lambda.min
mejorlambda

#Rige sin la validación cruzada para obtener los valores finales
ridge.mod=glmnet(x[train_ride ,],y[train_ride],alpha=0,lambda=grid)
ridge.pred=predict(ridge.mod,s=mejorlambda ,newx=x[test_ride ,])
error.ridge.cv10 = mean((ridge.pred - prueba.test[,"price"])^2)
error.ridge.cv10


##################
#Ridge cv=5
##################

set.seed(11)
cv.ridge=cv.glmnet(x[train_ride ,],y[train_ride],alpha=0,lambda=grid, nfolds = 5) 
plot(cv.ridge) 
mejorlambda=cv.ridge$lambda.min
mejorlambda 
ridge.mod=glmnet(x[train_ride ,],y[train_ride],alpha=0,lambda=grid) # Hacemos ya el Rige sin la validación cruzada 
ridge.pred=predict(ridge.mod,s=mejorlambda ,newx=x[test_ride ,])
error.ridge.cv5 = mean((ridge.pred - prueba.test[,"price"])^2)
error.ridge.cv5

##################
#Lasso con cv=10
##################
 
set.seed(11)
cv.lasso=cv.glmnet(x[train_ride ,],y[train_ride],alpha=1, lambda = grid) # Alpha=1 para hacer LASSO
plot(cv.lasso)
bestlam=cv.lasso$lambda.min
bestlam
lasso.mod=glmnet(x[train_ride ,],y[train_ride],alpha=1,lambda=grid)
plot(lasso.mod)
lasso.pred=predict(lasso.mod,s=bestlam,newx=x[test_ride ,])
error.lasso.cv10 = mean((lasso.pred - prueba.test[,"price"])^2)
error.lasso.cv10

##################
#Lasso con cv=5
##################

set.seed(11)
cv.lasso=cv.glmnet(x[train_ride ,],y[train_ride],alpha=1, lambda = grid,nfolds = 5) # Alpha=1 para hacer LASSO
plot(cv.lasso)
bestlam=cv.lasso$lambda.min
bestlam
lasso.mod=glmnet(x[train_ride ,],y[train_ride],alpha=1,lambda=grid)
plot(lasso.mod)
lasso.pred=predict(lasso.mod,s=bestlam,newx=x[test_ride ,])
error.lasso.cv5 = mean((lasso.pred - prueba.test[,"price"])^2)
error.lasso.cv5

#####################################################
#Analisis de Componmentes Principales (PLS) con cv=10
#####################################################

set.seed(11)
pls.fit=plsr(price~., data=prueba.train, scale=TRUE, validation="CV") 
summary(pls.fit) 
validationplot(pls.fit,val.type="MSEP", xlab = "Numero de Componentes Principales")
pls.cv <- crossval(pls.fit, segments = 10) 
plot(MSEP(pls.cv), legendpos="topright")
summary(pls.cv, what = "validation")
pls.pred=predict(pls.fit,newdata=x[test_ride,],ncomp=2)
pls.pred
error.pls = mean((pls.pred-prueba.test[,"price"])^2)
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
set.seed(11)
mi.entrenamiento <- train(price~., data = prueba.train, method = "glmnet", 
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



#Veamos si modelos alternativos pueden mejorar la prediccion. En primer lugar, probemos con una regresion de arbol
model_cart <- rpart(price ~ bedrooms + review_scores_rating + number_of_reviews + square_feet + longitude + latitude + cancellation_policy, data=train, method = "anova", na.action = na.omit)

#Calculemos el Error Cuadrado Medio
predict_cart = predict(model_cart, newdata = test)
MSE_cart = mean((predict_cart-test$price)^2, na.rm = TRUE)
MSE_cart

# Podemos dibujar el arbol
prp(model_cart)

# Ahora, probemos con un Random Forest Model
model_rf = randomForest(price ~ bedrooms + review_scores_rating + number_of_reviews + square_feet + longitude + latitude + cancellation_policy, data=train, importance= TRUE, na.action = na.omit)
model_rf

predict_rf = predict(model_rf, ntree=4, newdata=test)
MSE_rf = mean((predict_rf-test$price)^2,na.rm = TRUE)
MSE_rf

plot(model_rf)


# Comparando todos los modelos:
B <-
  matrix(
    c(
      error.mco,
      error.mss.cv10,
      error.mss.cv5,
      error.msha.cv5,
      error.msha.cv10,
      error.ridge.cv10,
      error.ridge.cv5,
      error.lasso.cv10,
      error.lasso.cv5,
      error.pls,
      error.lasso.re,
      MSE_cart,
      MSE_rf
    ),
    nrow = 1,
    ncol = 13
  )
dimnames(B) <-
  list(
    "MSE",
    c(
      "error.mco",
      "error.mss.cv10",
      "error.mss.cv5",
      "error.msha.cv5",
      "error.msha.cv10",
      "error.ridge.cv10",
      "error.ridge.cv5",
      "error.lasso.cv10",
      "error.lasso.cv5",
      "error.pls",
      "error.lasso.re",
      "MSE_cart",
      "MSE_rf"
    )
  )
B
#El minimo es el Random Forest
which.min(B)


#Grafico importancia de las variables
varImpPlot(model_rf, sort=TRUE, main="Importancia", n.var=5)

#Tabla con la importancia de las variables
var.imp=data.frame(importance(model_rf, type=2))
var.imp$Variables=row.names(var.imp)
var.imp[order(var.imp$IncNodePurity, decreasing = TRUE),]

#Matriz de correlacion entre el precio y las otras variables
pairs(mll_data[,c(61,56,80,77,60,50,49,91)])




#Preguntas para la presentación:

#Cuáles son los barrios o las zonas con los precios más altos de propiedades en Airbnb disponibles en la ciudad escogida.

#En qué zonas se concentran la mayor cantidad de propiedades en la ciudad considerada.

#¿Hay asociación entre precios y los atributos de las propiedades disponibles?

#Cómo se distribuye la variable precio. 

