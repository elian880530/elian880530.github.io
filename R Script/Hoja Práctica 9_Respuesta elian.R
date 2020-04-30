####################################################################################################
### Modelos No Param�tricos: ###
####################################################################################################
rm(list=ls())
cat("\014") ## limpia la pantalla del R

##1 Carga los datos
library(ISLR)
set.seed(11)
attach(College)
Private2=rep(0,nrow(College))
Private2[College$Private == "Yes"]= 1
datos <- data.frame(College)
datos$Private = Private2

#a
set.seed(11)
train.size = dim(datos)[1] / 2
train = sample(1:dim(datos)[1], train.size)
test = -train
datos.train = datos[train, ]
datos.test = datos[test, ]

#b
dim(datos.train)
dim(datos.test)
datos.test = datos.test[-1,]

#c
#GAM CON NS GRADO 3
library(splines)
library(gam)
nvariables <- as.numeric(dim(datos)[2] -1)
set.seed(11)
x=model.matrix(Apps~.,datos.train)[,-1]
y=datos.train$Apps
gam1=gam(y~x[,1] + ns(x[,2], df =3) + ns(x[,3], df =3) + ns(x[,4], df =3)
         + ns(x[,5], df =3) + ns(x[,6], df =3) + ns(x[,7], df =3) 
         + ns(x[,8], df =3) + ns(x[,9], df =3)  + ns(x[,10], df =3) 
         + ns(x[,11], df =3)  + ns(x[,12], df =3) + ns(x[,13], df =3)
         + ns(x[,14], df =3)  + ns(x[,15], df =3) 
         + ns(x[,16], df =3) + ns(x[,17], df =3) ,data=datos.train)
preds=predict(gam1,newdata=datos.test, type = NULL)
error.gam1 = mean((datos.test[, "Apps"] - preds)^2)
error.gam1

#d
#GAM CON NS GRADO 3 con todas sus variables explicativas al 5% de significacion
summary(gam1)

# Miramos la significaci�n de los coeficientes el modelo
fit.final <- gam(y~x[,1] + ns(x[,2], df =3) + ns(x[,3], df =3) + ns(x[,4], df =3)
                 + ns(x[,5], df =3) + ns(x[,6], df =3)
                 + ns(x[,14], df =3) + ns(x[,16], df =3) ,data=datos.train)
sum.fit.final <- summary(fit.final)
sum.fit.final

# Predecimos con ese modelo
fit.final.pred <- predict(fit.final, newdata = datos.test)

# Calculamos el error cometido
error.gam2 <- mean((datos.test[,'Apps'] - fit.final.pred)^2)
error.gam2

gam2 <- gam(y~x[,1] + ns(x[,2], df =3) + ns(x[,3], df =3) + ns(x[,4], df =3)
            + ns(x[,5], df =3) + ns(x[,6], df =3) 
            + ns(x[,14], df =3)  + ns(x[,16], df =3), data=datos.train)
#e
#spline natural = 5
gam3=gam(y~x[,1] + ns(x[,2], df =5) + ns(x[,3], df =5) + ns(x[,4], df =5)
         + ns(x[,5], df =5) + ns(x[,6], df =5) 
         + ns(x[,14], df =5)  + ns(x[,16], df =5), data=datos.train)
anova(gam2,gam3, test = "F")

#spline natural = 2
gam4=gam(y~x[,1] + ns(x[,2], df =2) + ns(x[,3], df =2) + ns(x[,4], df =2)
         + ns(x[,5], df =2) + ns(x[,6], df =2) 
         + ns(x[,14], df =2)  + ns(x[,16], df =2), data=datos.train)
anova(gam4,gam2, test = "F")

preds3=predict(gam4,newdata=datos.test, type = NULL)
error.gam4 <- mean((datos.test[, "Apps"] - preds3)^2)
error.gam4 

#f
#spline cubico de base = 3
gam5=gam(y~x[,1] + bs(x[,2]) + bs(x[,3]) + bs(x[,4])
         + bs(x[,5]) + bs(x[,6]) 
         + bs(x[,14])  + bs(x[,16]), data=datos.train)

preds4=predict(gam5,newdata=datos.test, type = NULL)
error.gam5 <- mean((datos.test[, "Apps"] - preds4)^2)
error.gam5

#g
#LAR con cv=10
library(lars)
nvariables <- as.numeric(dim(datos)[2] -1)
set.seed(11)
x=model.matrix(Apps~.,datos)[,-1]
y=Apps
y.test=y[test]
cv.lar <- cv.lars(x[train,],y[train], plot.it = TRUE, mode = "step", type = "lar")
idx.lar <- which.min(cv.lar$cv)
idx.lar
coef(lars(x, y, type = 'lar'))[idx.lar,]

lar.mod <- lars(x[train,],y[train], type="lar", trace=TRUE)
lar.pred <- predict.lars(lar.mod , s=idx.lar,newx=x[-train,], type="fit",mode="step")
MSE.lar <- mean((lar.pred$fit-y.test)^2)
MSE.lar

#h
#LAR con cv=5
cv.lar.5 <- cv.lars(x[train,],y[train],K=5, plot.it = TRUE, mode = "step", type = "lar")
idx.lar.5 <- which.min(cv.lar.5$cv)
idx.lar.5
coef(lars(x, y, type = 'lar'))[idx.lar.5,]

lar.mod <- lars(x[train,],y[train], type="lar", trace=TRUE)
lar.pred <- predict.lars(lar.mod , s=idx.lar.5,newx=x[-train,], type="fit",mode="step")
MSE.lar.cv5 <- mean((lar.pred$fit-y.test)^2)
MSE.lar.cv5

#I
#Lasso con red elastica cv=10
## (d) ###
library(glmnet)
library(caret)
lambda.grid <- 10^seq(2,-2, length = 100)
alpha.grid <- seq(0,1, length = 10)
lambda.grid
alpha.grid
Control <- trainControl(method = "repeatedcv", number = 10, repeats = 5)
busca.grid <- expand.grid(.alpha = alpha.grid, .lambda = lambda.grid)
busca.grid
set.seed(11)
mi.entrenamiento <- train(Apps~., data = datos.train, method = "glmnet", 
                          tuneGrid = busca.grid, trControl = Control, 
                          standardize = TRUE, maxit = 1000000)

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
error.pred <- mean((lre.pred - y.test)^2)
error.pred

#J
#Lasso con red elastica con cv=5
Control <- trainControl(method = "repeatedcv", number = 5, repeats = 5)
busca.grid <- expand.grid(.alpha = alpha.grid, .lambda = lambda.grid)
busca.grid
set.seed(11)
mi.entrenamiento <- train(Apps~., data = datos.train, method = "glmnet", 
                          tuneGrid = busca.grid, trControl = Control, 
                          standardize = TRUE, maxit = 1000000)

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
error.pred.cv5 <- mean((lre.pred - y.test)^2)
error.pred.cv5

#k
#mco
set.seed(11)
lm.fit = lm(Apps~., data=datos.train)
lm.pred = predict(lm.fit, newdata = datos.test)
mean((datos.test[, "Apps"] - lm.pred)^2)

error.mco <- mean((datos.test[, "Apps"] - lm.pred)^2)
error.mco

#L
#mss con cv=10
dim(datos)
library(leaps)
nvariables <- as.numeric(dim(datos)[2] -1)
regfit.full=regsubsets(Apps~.,data= datos[train,],nvmax=nvariables)

predict.regsubsets=function(object,newdata,id,...){
  form=as.formula(object$call[[2]])
  mat=model.matrix(form,newdata)
  coefi=coef(object,id=id)
  xvars=names(coefi)
  mat[,xvars]%*%coefi
}
k = 10
set.seed(11)
folds=sample(1:k,nrow(datos.train),replace=TRUE)
folds
table(folds)
cv.errors=matrix(NA,k,17, dimnames =list(NULL , paste(1:17)))
for(j in 1:k){
  best.fit=regsubsets(Apps~.,data=datos.train[folds!=j,],
                      nvmax=17)
  for(i in 1:17){
    pred=predict.regsubsets(best.fit,datos.train[folds==j,],id=i)
    cv.errors[j,i]=mean( (datos.train$Apps[folds==j]-pred)^2)
  }
}
rmse.cv=sqrt(apply(cv.errors,2,mean))
rmse.cv
which.min(rmse.cv)
plot(rmse.cv,pch=19,type="b")
reg.best=regsubsets (Apps~.,data=datos.train , nvmax=17)
coef(reg.best ,4)
lm.pred = predict.regsubsets(regfit.full, newdata = datos.test, id=4)
error.mss <- mean((datos.test[, "Apps"] - lm.pred)^2)
error.mss 

#L
#mss con cv=5
k = 5
set.seed(11)
folds=sample(1:k,nrow(datos.train),replace=TRUE)
folds
table(folds)
cv.errors=matrix(NA,k,17, dimnames =list(NULL , paste(1:17)))
for(j in 1:k){
  best.fit=regsubsets(Apps~.,data=datos.train[folds!=j,],
                      nvmax=17)
  for(i in 1:17){
    pred=predict.regsubsets(best.fit,datos.train[folds==j,],id=i)
    cv.errors[j,i]=mean( (datos.train$Apps[folds==j]-pred)^2)
  }
}
rmse.cv=sqrt(apply(cv.errors,2,mean))
rmse.cv
which.min(rmse.cv)
plot(rmse.cv,pch=19,type="b")
reg.best=regsubsets (Apps~.,data=datos.train , nvmax=17)
coef(reg.best ,10)
lm.pred = predict.regsubsets(regfit.full, newdata = datos.test, id=10)
error.mss.cv5 <- mean((datos.test[, "Apps"] - lm.pred)^2)
error.mss.cv5 

#L
#msha con cv=10
k = 10
set.seed(11)
folds=sample(1:k,nrow(datos.train),replace=TRUE)
folds
table(folds)
cv.errors=matrix(NA,k,17, dimnames =list(NULL , paste(1:17)))
for(j in 1:k){
  best.fit=regsubsets(Apps~.,data=datos.train[folds!=j,],
                      nvmax=17,method = "forward")
  for(i in 1:17){
    pred=predict.regsubsets(best.fit,datos.train[folds==j,],id=i)
    cv.errors[j,i]=mean( (datos.train$Apps[folds==j]-pred)^2)
  }
}
rmse.cv=sqrt(apply(cv.errors,2,mean))
rmse.cv
which.min(rmse.cv)
plot(rmse.cv,pch=19,type="b")
reg.best=regsubsets (Apps~.,data=datos.train , nvmax=17,method = "forward")
coef(reg.best ,11)
lm.pred = predict.regsubsets(regfit.full, newdata = datos.test, id=11)
error.msha <- mean((datos.test[, "Apps"] - lm.pred)^2)
error.msha 

#M
#msha cv=5
k = 5
set.seed(11)
folds=sample(1:k,nrow(datos.train),replace=TRUE)
folds
table(folds)
cv.errors=matrix(NA,k,17, dimnames =list(NULL , paste(1:17)))
for(j in 1:k){
  best.fit=regsubsets(Apps~.,data=datos.train[folds!=j,],
                      nvmax=17,method = "forward")
  for(i in 1:17){
    pred=predict.regsubsets(best.fit,datos.train[folds==j,],id=i)
    cv.errors[j,i]=mean( (datos.train$Apps[folds==j]-pred)^2)
  }
}
rmse.cv=sqrt(apply(cv.errors,2,mean))
rmse.cv
which.min(rmse.cv)
plot(rmse.cv,pch=19,type="b")
reg.best=regsubsets (Apps~.,data=datos.train , nvmax=17,method = "forward")
coef(reg.best ,10)
lm.pred = predict.regsubsets(regfit.full, newdata = datos.test, id=10)
error.msha.cv5 <- mean((datos.test[, "Apps"] - lm.pred)^2)
error.msha.cv5 

#N
#ridge cv=10
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

set.seed(11)
library(glmnet)
x=model.matrix(Apps~.,datos)[,-1]
y=Apps
y.test=y[test]
grid=10^seq(10,-2, length =100)
cv.ridge=cv.glmnet(x[train ,],y[train],alpha=0,lambda=grid)
plot(cv.ridge)
mejorlambda=cv.ridge$lambda.min
mejorlambda

ridge.mod=glmnet(x[train ,],y[train],alpha=0,lambda=grid)
ridge.pred=predict(ridge.mod,s=mejorlambda ,newx=x[test ,])
mean((ridge.pred-y.test)^2)
error.ridge <- mean((ridge.pred-datos.test[, "Apps"] )^2)
error.ridge

#O
#ridge cv=5
cv.ridge=cv.glmnet(x[train ,],y[train],alpha=0,lambda=grid,nfolds = 5)
plot(cv.ridge)
mejorlambda=cv.ridge$lambda.min
mejorlambda

ridge.mod=glmnet(x[train ,],y[train],alpha=0,lambda=grid)
ridge.pred=predict(ridge.mod,s=mejorlambda ,newx=x[test ,])
mean((ridge.pred-y.test)^2)
error.ridge.cv5 <- mean((ridge.pred-datos.test[, "Apps"] )^2)
error.ridge.cv5

#P
#lasso cv=10
set.seed(11)
cv.lasso=cv.glmnet(x[train ,],y[train],alpha=1, lambda = grid)
plot(cv.lasso)
bestlam=cv.lasso$lambda.min
bestlam

lasso.mod=glmnet(x[train ,],y[train],alpha=1,lambda=grid)
plot(lasso.mod)
lasso.pred=predict(lasso.mod,s=bestlam,newx=x[test ,])
mean((lasso.pred-y.test)^2)
error.lasso <- mean((lasso.pred-datos.test[, "Apps"] )^2)
error.lasso

#Q
#lasso cv=5
set.seed(11)
cv.lasso=cv.glmnet(x[train ,],y[train],alpha=1, lambda = grid,nfolds = 5)
plot(cv.lasso)
bestlam=cv.lasso$lambda.min
bestlam

lasso.mod=glmnet(x[train ,],y[train],alpha=1,lambda=grid)
plot(lasso.mod)
lasso.pred=predict(lasso.mod,s=bestlam,newx=x[test ,])
mean((lasso.pred-y.test)^2)
error.lasso.cv5 <- mean((lasso.pred-datos.test[, "Apps"] )^2)
error.lasso.cv5

#R
#MINIMOS CUADRADOS PARCIALES CV=10
library(pls)
set.seed(11)
pls.fit=plsr(Apps~., data=datos,subset=train,scale=TRUE, validation="CV")
summary(pls.fit)
validationplot(pls.fit,val.type="MSEP", xlab = "NUmero de Componentes Principales")
pls.cv <- crossval(pls.fit, segments = 10)
plot(MSEP(pls.cv), legendpos="topright")
summary(pls.cv, what = "validation")

pls.pred=predict(pls.fit,newdata=x[test,],ncomp=11)
mean((pls.pred-y.test)^2)
error.pls <- mean((pls.pred-datos.test[, "Apps"] )^2)
error.pls #Este es el modelo con menor error

#O
#MINIMOS CUADRADOS PARCIALES CV=5
library(pls)
set.seed(11)
pls.fit=plsr(Apps~., data=datos,subset=train,scale=TRUE, validation="CV")
summary(pls.fit)
validationplot(pls.fit,val.type="MSEP", xlab = "NUmero de Componentes Principales")
pls.cv <- crossval(pls.fit, segments = 5)
plot(MSEP(pls.cv), legendpos="topright")
summary(pls.cv, what = "validation")

pls.pred=predict(pls.fit,newdata=x[test,],ncomp=16)
mean((pls.pred-y.test)^2)
error.pls.cv5 <- mean((pls.pred-datos.test[, "Apps"] )^2)
error.pls.cv5

#
#identificar el de menor error
error.gam1
#
error.gam2
#
error.gam4 #Este es el que menor error presenta y el que mejor predice
#
error.gam5

