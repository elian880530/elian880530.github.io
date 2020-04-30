#####
### MRLS: ###
#####
rm(list=ls())
cat("\014") ##Limpia la pantalla de R

#Define un directorio de trabajo
#setwd("a")

#Carga los datos
turismo=read.csv2("Datos_Turismo.csv",dec=".") # La csv2 es más eficiente que la normal
#Si hacemos attach(turismo) luego ya podremos omitir el data=turismo.
fit1=lm(log_llegadas ~ estpol, data = turismo) # Realiza el ajuste lineal, donde ponemos y tilde x
summary(fit1)

###2 (a)-(c) ###
attach(turismo)
fit2 = lm(log_pib ~ log_llegadas)
summary(fit2)
#a) Tenemos que el coeficiente b1 es positivo, por lo tanto, el número de llegadas afecta positivamente al turismo.
#b) Además, como el p-valor de este coeficiente 1 es mucho más pequeño que 0.01, rechazamos H0 (b1=0) al 1% de significación
#c) 
sum.fit2=summary(fit2)
sum.fit2$coefficients
t=(sum.fit2$coefficients[2,1]-1)/sum.fit2$coefficients[2,2] #Guardamos el valor estadístico t=(b1-1)/sigma_b1
#Tenemos que k=1, y tenemos n-k-1 grados de libertad, para verlo mejor, usamos el siguiente comand oque nos da los grados de libertad:
sum.fit2$df # Nos da número de varaibles (2) y grados de libertad (150)
sum.fit2$df[2] # Nos da los grados de libertad (n-k-1) directamente
#Haremos uso de la función cuantil qt(alpha,df,lower.tail=FALSE(porque haremos un contraste de cola superior))
v.c.=qt(0.05,sum.fit2$df[2],lower.tail=TRUE) # Usamos alpha=0.05, 150 grados de libertad y que la cola es superior


confint(fit1)
predict(fit1,,interval="confidence")