# Predecir el salario de los jugadores de la Liga Nacional de Hockey

# Basado en los datos descargados de los siguientes sitios:
# http://www.hockeyabstract.com/testimonials/nhl2016-17playerdata
# https://www.kaggle.com

# Introducción
# En el siguiente trabajo examinaremos todas las variables estadísticas de los jugadores LNH
# y también los predictores dentro y fuera del hielo para obtener un modelo que describa cual 
# es el salario óptimo para estos jugadores profesionales. Al final del documento se encuentran 
# descritas todas las variables utilizadas en los datos.

# Se ha determinado usar el salario como la variable de respuesta para este análisis, debido a que 
# está influenciado por una gran cantidad de factores más allá del desempeño de un jugador en 
# el hielo. Además, cada equipo tiene una cantidad limitada de dinero que puede gastar en jugadores. 
# La cifra en dólares que cuenta para el tope salarial no es la cantidad real de dinero que gana un jugador en una temporada, 
# es el promedio de su compensación anual durante la duración de su contrato.

# Para esta ejecución del modelo, solo comprende la temporada 2016/2017 
# para así evitar la replicación mediante el uso de múltiples años de datos donde los mismos jugadores están presentes en los datos de varios años.

#Objetivos:
#¿Construir un modelo para predecir los salarios de los jugadores de NHL? ¿Cuáles son los mejores predictores de cuánto hará un jugador?

#Pasos para construir un modelo de los factores que influyen en el salario de la NHL:
# Primero limpiar el conjunto de datos, imputar los valores perdidos y eliminar las columnas categóricas que podrían llevar a un sobreajuste. 
# Segundo explorar y visualizar algunas de las relaciones en los datos.
# Tercero construir un modelo y probarlo hasta que encontremos el conjunto más óptimo de características predictivas.
# Cuarto Seleccionar el mejor modelo.

#################

# 1
# Cargando los datos: 
options(prompt='R> ')
options(continue = '\t')

#cargando los datos y comprobando que todo esté en orden.
train = read.csv('D:/Trabajo con Aprendizaje Estadístico Archivo (hayko y william)/Trabajo Final con William/train.csv')
head(train)
test_x = read.csv('D:/Trabajo con Aprendizaje Estadístico Archivo (hayko y william)/Trabajo Final con William/test.csv')
head(test_x)
test_y = read.csv('D:/Trabajo con Aprendizaje Estadístico Archivo (hayko y william)/Trabajo Final con William/test_salaries.csv')
head(test_y)

#################

# 1.1
# Instalando packages
install.packages("ISLR")
install.packages("tree")
install.packages("gbm")

# Cargando las bibliotecas necesarias:
library(randomForest)
library(plyr)
library(stringr)
library(magrittr)
library(scatterplot3d)
library(glmnet)
library(pls)
library(caret)
require(ISLR)
require(tree)
require(gbm)


#################

# 1.2
# Limpiando los datos
# Imputando datos faltantes y corregiendo columnas categóricas problemáticas.
# Se fusiona el conjunto de entrenamiento y los datos de prueba en un solo conjunto para detectar dónde hay valores faltantes.

#Fusionando train/test
test_x$TrainTest = "test"
train$TrainTest =  "train"
test = cbind(test_y, test_x)
all_data = rbind(train,test)

# Detectando valores perdidos
all_missing_list=  colnames(all_data)[colSums(is.na(all_data)) > 0]
all_missing_list

#################

# 1.3
# Imputando la columna de estado para jugadores internacionales
# Se imputara 'INT' para designar internacional para todos los demás individuos.
all_data$Pr.St = mapvalues(all_data$Pr.St, from = "", to="INT")

#################

# 1.4
# Imputando la columna de equipo
# Hacer columnas booleanas del equipo y obtener la lista única de acrónimos del equipo
teams = c()
for( i in levels(all_data$Team)){
  x = strsplit(i, "/")
  for(y in x){
    teams = c(teams, y)
  }
}
teams = unique(teams)

# Agregar columnas con los nombres del equipo como el encabezado y 0 como valores
for(team in teams){
  all_data[,team] = 0
}

# Iterar y registrar los equipos para cada jugador
for(i in 1:length(all_data$Team)){
  teams_of_person = strsplit(as.character(all_data$Team[i]), "/")[[1]]
  for(x in teams_of_person){
    all_data[,x][i] = 1	
  }
}

#################

# 1.5
# Posiciones jugadas
# División de la columna de posición categórica en múltiples booleanos.
pos = c()
for( i in levels(all_data$Position)){
  x = strsplit(i, "/")
  for(y in x){
    pos = c(pos, y)
  }
}
pos = unique(pos)

# Agregar columnas con los nombres de posición como el encabezado y 0 como valores
for(position in pos){
  all_data[,position] = 0
}

# Iterar y registrar las posiciones para cada jugador
for(i in 1:length(all_data$Position)){
  pos_of_person = strsplit(as.character(all_data$Position[i]), "/")[[1]]
  for(x in pos_of_person){
    all_data[,x][i] = 1	
  }
}

#################

# 1.6
# Año, día y mes de nacimiento
bday_parts = str_split_fixed(all_data$Born, "-", 3)

# columna de año ajustada para dar cuenta de los dígitos que faltan
birth_year = c()
for(year in bday_parts[,1]){
  if(as.numeric(year) < 10){
    yr = paste("20", year, sep="")
    birth_year = c(birth_year, yr)
  }else{
    yr = paste("19",year, sep="")
    birth_year = c(birth_year, yr)
  }
}

all_data$birth_year = as.numeric(birth_year)
all_data$birth_month = as.numeric(bday_parts[,2])
all_data$birth_day = as.numeric(bday_parts[,3])

#################

# 1.7
# Ciudadanía y país de nacimiento
# Dividir Cntry y Nat a columnas booleanas
birth_country = levels(all_data$Cntry)

# Agregando columnas con las opciones de país de nacimiento
for(country in birth_country){
  c = paste("born", country, sep="_")
  
  all_data[,c] = 0
}

# Iterando y registrando el país de nacimiento de cada jugador
for(i in 1:length(all_data$Cntry)){
  birth_country = all_data$Cntry[i]
  c = paste("born", birth_country, sep="_")
  all_data[,c][i] = 1	
}
nationality = levels(all_data$Nat)
for(country in nationality){
  c = paste("nation", country, sep="_")
  all_data[,c] = 0
}

# Iterando y registrando el país de nacimiento de cada jugador
for(i in 1:length(all_data$Nat)){
  nationality = all_data$Nat[i]
  c = paste("nation", nationality, sep="_")
  all_data[,c][i] = 1	
}

#################

# 1.8
# Imputando columnas numéricas
# Imputando la mediana del valor faltante para las columnas numéricas. 
# contabilizamos los jugadores no seleccionados creando una columna booleana imputando el año medio del DRAFT.
all_data$undrafted = is.na(all_data$DftRd)

# Rellenar valores medianos y llenando cada columna con la mediana de
# los valores existentes para todo el conjunto de datos.
all_missing_list =  colnames(all_data)[colSums(is.na(all_data)) > 0]
length(all_missing_list) == 0

# Si se cumple la condicion todos los valores son imputados
for( i in 1:length(all_missing_list)){
  # Obtener la mediana global
  median_all = median(all_data[,all_missing_list[i]], na.rm =TRUE)
  # Imputando los valores perdidos con la mediana
  all_data[,all_missing_list[i]][is.na(all_data[,all_missing_list[i]])] = median_all
}
length(all_missing_list) == 0

#################

# 1.9
# Imputando la columna de provincia
# Hacer columnas booleanas de la provincia y obtener la lista única de acrónimos 
provincia = c()
for( i in levels(all_data$Pr.St)){
  x = strsplit(i, "/")
  for(y in x){
    provincia = c(provincia, y)
  }
}
provincia = unique(provincia)

# Agregar columnas con los nombres de las provincias como el encabezado y 0 como valores
for(provincia in provincia){
  all_data[,provincia] = 0
}

# Iterar y registrar las provincias de cada jugador
for(i in 1:length(all_data$Pr.St)){
  provincia_of_person = strsplit(as.character(all_data$Pr.St[i]), "/")[[1]]
  for(x in provincia_of_person){
    all_data[,x][i] = 1	
  }
}

# Eliminando esta variable despues de convertir cada uno de sus valores en columnas
names_col=all_data$Last.Name
all_data = all_data[, !(colnames(all_data) %in% c("Pr.St"))]

#################

# 1.10
# Undrafted convertimos FALSE en 0 y TRUE en 1
all_data$undrafted=ifelse(all_data$undrafted == FALSE,0,1)

#################

# 1.11
# Hand convertimos L (LEFT) en 1 y R (RIGT) en 0
all_data$Hand=ifelse(all_data$Hand == "L",1,0)

#################

# 2
# Análisis gráfico y exploración de datos

# 2.1
# ¿Cuántos jugadores de cada país?
barplot(sort(table(all_data$Nat),
             decreasing=TRUE), 
        horiz=TRUE, 
        las=1,
        col=c("red","blue4","blue","red3","skyblue"),
        main="Número de jugadores NHL de cada país", ylab="Pais",xlab="Cantidad")

# Una gran parte de los jugadores son canadienses y estadounidenses 

#################

# 2.2
# Desglose de edad
table(all_data$birth_year)
# Histograma
hist(all_data$birth_year, breaks=28, 
     col="skyblue", xlab='Año de nacimiento', 
     main='Distribución de jugadores NHL por año de nacimiento (temporada 2016/2017)')
# Tener en cuenta que hay 15 jugadores nacidos en 2000 o más adelante

#################

# 2.3
# Distribución de los salarios de NHL
summary(all_data$Salary)
hist(all_data$Salary, breaks=52, 
     col="salmon", xlab='Salario', 
     ylab = "Numero de jugadores", main='Distribución de salarios NHL: 2016/2017')

# Existe disparidad en los ingresos. El jugador medio de la NHL está ganando $ 925,000, pero el maximium es de $ 14,000,000. 

#################

# 2.4
# Comparando el salario con el rendimiento de cada jugador
plot(all_data$G, all_data$Salary, xlab='Goles Marcados',pch=20 , ylab='Dinero Ganado')
abline(lm(all_data$Salary ~ all_data$G), col="red")

# Existe una gran variación en el diagrama anterior. 
# Etiquetando los puntos para obtener más información de este diagrama de dispersión.
plot(all_data$G, all_data$Salary, pch=20, xlab='Goles Marcados', ylab='Dinero Ganado', main="¿Quiénes son los atípicos?")
abline(lm(all_data$Salary ~ all_data$G), col="red")
text(all_data$G, all_data$Salary, labels=all_data$Last.Name, cex=0.7, pos = 3)
# Los jugadores de alto puntaje y alto sueldo se agrupan en la parte superior derecha, 
# pero el valor real se encuentra en la esquina inferior derecha 

#################

# 2.5
# Efectos de los objetivos y la edad en el salario
# Los jugadores más viejos tienen mejor compensacion por tener la misma producción de hielo que los jugadores más jóvenes
color.gradient <- function(x, colors=c("green", "yellow", "red"), colsteps=100) {
  return( colorRampPalette(colors) (colsteps) [ findInterval(x, seq(min(x),max(x), length.out=colsteps)) ] )
}
#plot 
sd3 = scatterplot3d(all_data$G, all_data$birth_year, all_data$Salary,
                    pch=19,type="h",
                    cex.axis=0.5,
                    las=1,
                    lty.hplot=2,
                    color=color.gradient(all_data$Salary,c("black","salmon")),
                    main="Interacción de edad, objetivos y salario",
                    zlab="Salario",
                    xlab="Goles",
                    ylab="Año de nacimiento",
                    grid=TRUE)

# Dos clases principales de jugadores sobresalen en el medio,los defensas  
# y los goleadores en la parte inferior derecha

#################

# 2.6
# Analisis de métricas de hielo y salarios
score_3d = scatterplot3d(all_data$TOI,all_data$SCF,all_data$Salary,pch=19,
                         type="h",
                         cex.axis=0.5,
                         las=1,
                         lty.hplot=2,
                         color=color.gradient(all_data$Salary, 
                                              colors=c("black","skyblue")),
                         main="Mayor Correlacion:Salario, tiempo en el hielo y oportunidades de gol",
                         zlab="Salario",xlab="Tiempo en el Hielo",
                         ylab="Posibilidades de gol mientras jugador en hielo",
                         grid=TRUE)
# Existe una fuerte relación entre el tiempo en el hielo las oportunidades de gol y el salario.

#################

# 3
# Preparando los datos para modelar.
# Para construir los modelos y evaluar el ajuste, se dividen el grupo de entrenamiento y se prueba en dos data-frames nuevamente.

# Se eliminan columnas que se reemplazaron anteriormente para que no ocurra un sobreajuste.
names_col=all_data$Last.Name
all_data = all_data[, !(colnames(all_data) %in% c("Last.Name","First.Name","Cntry","Nat","Born","Team","City","Position"))]

# Conformano los datos de Train y Test
train_dat = all_data[all_data$TrainTest == "train",]
test_dat = all_data[all_data$TrainTest == "test",]

# Comprobando que no se elimino ningun valor
length(test_dat$TrainTest) + length(train_dat$TrainTest) == length(all_data$TrainTest)

# Borrar las columnas divididas de prueba / entrenamiento
train_dat = train_dat[, !(colnames(train_dat) %in% c("TrainTest"))]
test_dat = test_dat[, !(colnames(test_dat) %in% c("TrainTest"))]

# Guardando los datos en memoria 
all_data = rbind(train_dat,test_dat)
attach(all_data)
train.size = dim(train_dat)[1]#Tomamos el tamano de entrenamiento 
train = sample(1:dim(train_dat)[1], train.size)
test.size = dim(test_dat)[1]#Tomamos el tamano de entrenamientoprueba
test = sample(1:dim(test_dat)[1], test.size)

# Preparando las variables para utilizarlas en los diferentes metodos
x=model.matrix(Salary~.,all_data)[,-1] # Guardamos las variables independientes en una variable
y=Salary # La variable a predecir y
y.test=y[test] # Las y de prueba

###############

# 3.1
# MCO
set.seed(11)
mco.fit = lm(Salary ~., data=train_dat) # Creamos el modelo con el conjunto de entrenamiento
mco.pred = predict(mco.fit, newdata = test_dat) # Predecimos los valores para el conjunto de entrenamiento
error.mco = mean((test_dat[,"Salary"] - mco.pred)^2)
error.mco

###############

# 3.2
# Riege con cv=10
set.seed(11)
grid=10^seq(10,-2, length =100) # Generamos las lambdas
cv.ridge=cv.glmnet(x[train ,],y[train],alpha=0,lambda=grid) 
plot(cv.ridge) 
mejorlambda=cv.ridge$lambda.min
mejorlambda

#Rige sin la validación cruzada para obtener los valores finales
set.seed(11)
ridge.mod=glmnet(x[train ,],y[train],alpha=0,lambda=grid)
plot(ridge.mod)
ridge.pred=predict(ridge.mod,s=mejorlambda ,newx=x[test ,])
plot(ridge.pred)
error.ridge = mean((ridge.pred - test_dat[,"Salary"])^2)
error.ridge

###############

# 3.3
# Lasso con cv=10
set.seed(11)
cv.lasso=cv.glmnet(x[train ,],y[train],alpha=1, lambda = grid) # Alpha=1 para hacer LASSO
plot(cv.lasso)
bestlam=cv.lasso$lambda.min
bestlam
lasso.mod=glmnet(x[train ,],y[train],alpha=1,lambda=grid)
plot(lasso.mod)
lasso.pred=predict(lasso.mod,s=bestlam,newx=x[test ,])
plot(lasso.pred)
error.lasso = mean((lasso.pred - test_dat[,"Salary"])^2)
error.lasso

###############

# 3.4
# LASSO CON RED ELASTICA
set.seed(11)
lambda.grid <- 10^seq(2,-2, length = 100)
alpha.grid <- seq(0,1, length = 10)
lambda.grid
alpha.grid
Control <- trainControl(method = "repeatedcv", number = 10, repeats = 5)
busca.grid <- expand.grid(.alpha = alpha.grid, .lambda = lambda.grid)
busca.grid
set.seed(11)
mi.entrenamiento <- train(Salary~., data = train_dat, method = "glmnet", 
                          tuneGrid = busca.grid, trControl = Control, 
                          standardize = TRUE, maxit = 1000)

plot(mi.entrenamiento)
attributes(mi.entrenamiento)
mi.entrenamiento$bestTune
mi.entrenamiento$finalModel

mi.modelo.glmnet <- mi.entrenamiento$finalModel
coef(mi.modelo.glmnet, s = mi.entrenamiento$bestTune$lambda)
mej.modelo <- glmnet(x[train ,],y[train], alpha=mi.entrenamiento$bestTune$alpha,
                     lambda = mi.entrenamiento$bestTune$lambda)

coef(mej.modelo, s = mi.entrenamiento$bestTune$lambda)
cbind(coef(mej.modelo, s = mi.entrenamiento$bestTune$lambda), coef(mi.modelo.glmnet, s = mi.entrenamiento$bestTune$lambda))
lre.pred <- predict(mej.modelo,s=mi.entrenamiento$bestTune$lambda,newx=x[test ,])
error.lasso.re <- mean((lre.pred - y.test)^2)
error.lasso.re

###############

# 3.5
# Tree
hist(Salary)
set.seed(11)
tree.salario=tree(Salary~.,data=all_data)
summary(tree.salario)
plot(tree.salario)
text(tree.salario,pretty=0)
# Imprimiendo el arbol para obtener un resumen detallado.
tree.salario
# Creciendo el árbol en el conjunto de entrenamiento y evaluando su rendimiento en el conjunto de prueba.
set.seed(11)
tree.salario=tree(Salary~.,all_data,subset=train)
plot(tree.salario);text(tree.salario,pretty=0)
tree.pred=predict(tree.salario,newdata = test_dat)
error.tree = mean((tree.pred - test_dat[,"Salary"])^2)
error.tree
# Este árbol se cultivó a profundidad completa, y podría ser muy variable. Ahora usamos CV para podarlo.
cv.salario=cv.tree(tree.salario,FUN=prune.tree)
cv.salario
plot(cv.salario)
prune.salario=prune.tree(tree.salario,best=10)#El 10 es el que mejor resultado tiene
plot(prune.salario);text(prune.salario,pretty=0)
tree.pred.podado=predict(prune.salario,newdata = test_dat)
error.tree.podado = mean((tree.pred.podado - test_dat[,"Salary"])^2)
error.tree.podado

###############

# 3.6
# Boosting
set.seed(11)
boosting.salary=gbm(Salary~.,data=all_data,distribution="gaussian",n.trees=1000,shrinkage=0.01,interaction.depth=4)
summary(boosting.salary)
plot(boosting.salary)
# Imprimiendo el arbol para obtener un resumen detallado.
boosting.salary
#Creciendo el árbol en el conjunto de entrenamiento y evaluando su rendimiento en el conjunto de prueba.
set.seed(11)
boosting.salary=gbm(Salary~.,data=train_dat,distribution="gaussian",n.trees=1000,shrinkage=0.01,interaction.depth=4)
plot(boosting.salary)
n.trees=seq(from=100,to=1000,by=100)
boosting.pred=predict(boosting.salary,newdata = test_dat,n.trees=n.trees)
berr=with(test_dat,apply( (boosting.pred-Salary)^2,2,mean))
plot(n.trees,berr,pch=19,ylab="Mean Squared Error", xlab="# Trees",main="Boosting Test Error")
error.boosting = mean((boosting.pred - test_dat[,"Salary"])^2)
error.boosting

###############

# 3.7
# Cross-validation 5 en Boosting basado en el mejor número estimado de árboles

# Hacer una validación cruzada de 5 veces
boosting.salary.cv=gbm(Salary~.,data=train_dat,distribution="gaussian",n.trees=1000,cv.folds=5,shrinkage=0.01,interaction.depth=4)
# comprobar el rendimiento con una validación cruzada de 5 veces
best.iter <- gbm.perf(boosting.salary.cv,method="cv")
print(best.iter)
# plot the performance # plot variable influence
summary(boosting.salary.cv,n.trees=1000) # basado en diez mil árboles
summary(boosting.salary.cv,n.trees=best.iter) # basado en el mejor número estimado de árboles
# predecir los nuevos datos utilizando la "mejor" cantidad de árboles
n.trees.cv5=seq(from=100,to=best.iter,by=100)
boosting.pred.cv5=predict(boosting.salary,newdata = test_dat,n.trees=n.trees.cv5)
berr.cv5=with(test_dat,apply( (boosting.pred.cv5-Salary)^2,2,mean))
plot(n.trees.cv5,berr.cv5,pch=19,ylab="Mean Squared Error", xlab="# Trees",main="Boosting Test Error")
error.boosting.cv5 = mean((boosting.pred.cv5 - test_dat[,"Salary"])^2)
error.boosting.cv5

###############

# 3.8
# Cross-validation 10 en Boosting basado en el mejor número estimado de árboles

# Hacer una validación cruzada de 10 veces
boosting.salary.cv=gbm(Salary~.,data=train_dat,distribution="gaussian",n.trees=1000,cv.folds=10,shrinkage=0.01,interaction.depth=4)
# comprobar el rendimiento con una validación cruzada de 10 veces
best.iter <- gbm.perf(boosting.salary.cv,method="cv")
print(best.iter)
# trazar el rendimiento
summary(boosting.salary.cv,n.trees=1000) # basado en mil árboles
summary(boosting.salary.cv,n.trees=best.iter) # basado en el mejor número estimado de árboles
# predecir los nuevos datos utilizando la "mejor" cantidad de árboles
n.trees.cv10=seq(from=100,to=best.iter,by=100)
boosting.pred.cv10=predict(boosting.salary,newdata = test_dat,n.trees=n.trees.cv10)
berr.cv10=with(test_dat,apply( (boosting.pred.cv10-Salary)^2,2,mean))
plot(n.trees.cv10,berr.cv10,pch=19,ylab="Mean Squared Error", xlab="# Trees",main="Boosting Test Error")
error.boosting.cv10 = mean((boosting.pred.cv10 - test_dat[,"Salary"])^2)
error.boosting.cv10

###############

# 3.9
# Boosting con Caret
#COMPROBAR PARÁMETROS PARA SINTONIZAR
getModelInfo()$gbm$parameters
set.seed(11)
fitControl <- trainControl(method = "repeatedcv",number = 10,repeats = 5)
gbmFit1 <- train(Salary ~ ., data = train_dat,method = "gbm",trControl = fitControl,verbose = FALSE)
gbmFit1
plot(gbmFit1)
gbmFit1$bestTune
boost.caret.pred <- predict(gbmFit1, test_dat)
error.boosting.caret = mean((boost.caret.pred - test_dat[,"Salary"])^2)
error.boosting.caret


###############

# 3.10
# Ramdon Forest
set.seed(11)
rf.salary=randomForest(Salary~.,data=train_dat)
rf.salary
oob.err=double(8)
test.err=double(8)
for(mtry in 1:8){
  fit=randomForest(Salary~.,data=train_dat,mtry=mtry,ntree=1000,importance = TRUE)
  oob.err[mtry]=fit$mse[1000]
  rf.pred=predict(fit,all_data[test,])
  test.err[mtry]=with(all_data[test,],mean((Salary-rf.pred)^2))
  cat(mtry," ") #Imprimiendo los valores del bucle
}

matplot(1:mtry,cbind(test.err,oob.err),pch=19,col=c("red","blue"),type="b",ylab="Mean Squared Error")
legend("topright",legend=c("Test","OOB"),pch=19,col=c("red","blue"))

first_importance_list = importance(fit)
first_importance_list  = sort(first_importance_list[,1], decreasing=TRUE)
first_importance_list

varImpPlot(fit)
error.rforest = mean((rf.pred - test_dat[,"Salary"])^2)
error.rforest

###############

# 4
# Comparando todos los modelos:
B <-
  matrix(
    c(
      error.mco,
      error.ridge,
      error.lasso,
      error.lasso.re,
      error.tree.podado,
      error.boosting,
      error.boosting.cv5,
      error.boosting.cv10,
      error.boosting.caret,
      error.rforest
    ),
    nrow = 1,
    ncol = 10
  )
dimnames(B) <-
  list(
    "RMSE",
    c(
      "MCO",
      "RIDGE",
      "LASSO",
      "LASSO CON RED ELASTICA",
      "TREE",
      "BOOSTING",
      "BOOSTING CON CV5",
      "BOOSTING CON CV10",
      "BOOSTING CON CARET",
      "RANDOM FOREST"
    )
  )
B
which.min(B)

  #Conclusiones:
  
  #Después de realizar una evaluación sobre todos los modelos, se recomienda utilizar el modelo 4 donde 
  #se utiliza el método de Lasso con Red Elástica pues presenta menor MSE y es el que menor coste 
  #computacional conlleva. En la ejecución final de este modelo, se obtiene una leve mejoría en el 
  #porcentaje de varianza. Los mejores predictores del salario siguen siendo las métricas de edad 
  #(año de draft (DftYr) y año de nacimiento (birth_year)) y las medidas de tiempo en hielo 
  #(TOI.GP.1, TOI.GP, TOI.). Los siguientes indicadores más alto son FOW (El enfrentamiento del 
  #equipo gana mientras este jugador estaba en el hielo) y SF (Los disparos del equipo a la meta 
  #mientras este jugador estaba en el hielo). Luego observamos xGF (Los objetivos esperados del equipo 
  #(tiros pesados) mientras este jugador estaba en el hielo). Estos predictores están mostrando una 
  #imagen clara de cómo se puede predecir el salario de un jugador. En primer lugar, las limitaciones 
  #de edad afectan su salario potencial, y después de esto, los mejores predictores son FOL 
  #(Las pérdidas de cara a cara del equipo mientras este jugador estaba en el hielo) y el FF 
  #(los intentos de disparo desbloqueado del equipo mientras este jugador estaba en el hielo)