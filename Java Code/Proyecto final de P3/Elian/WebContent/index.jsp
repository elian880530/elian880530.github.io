<html>
<head>
<style type="text/css">
/*----------Text Styles----------*/
.ws6 {font-size: 8px;}
.ws7 {font-size: 9.3px;}
.ws8 {font-size: 11px;}
.ws9 {font-size: 12px;}
.ws10 {font-size: 13px;}
.ws11 {font-size: 15px;}
.ws12 {font-size: 16px;}
.ws14 {font-size: 19px;}
.ws16 {font-size: 21px;}
.ws18 {font-size: 24px;}
.ws20 {font-size: 27px;}
.ws22 {font-size: 29px;}
.ws24 {font-size: 32px;}
.ws26 {font-size: 35px;}
.ws28 {font-size: 37px;}
.ws36 {font-size: 48px;}
.ws48 {font-size: 64px;}
.ws72 {font-size: 96px;}
.wpmd {font-size: 13px;font-family: 'Arial';font-style: normal;font-weight: normal;}
/*----------Para Styles----------*/
DIV,UL,OL /* Left */
{
 margin-top: 0px;
 margin-bottom: 0px;
}
</style>
<script>
    var ancho = 46;
	var cont = 0;
	var percent = 2;
	var completo = 0;

function validar(u,p){
	if(u==""){
		document.getElementById("text2").innerHTML = "* Requerido";
		return;
	}else{
		document.getElementById("text2").innerHTML = "";
	}
	if(p==""){
		document.getElementById("text3").innerHTML = "* Requerido";
		return;
	}else{
		document.getElementById("text3").innerHTML = "";
	}
document.procesar.submit();
}

	
 function efecto(){
        if(cont==100){
            return;
        }else{
            	if(cont%2==1){
            		var cur = document.getElementById('imagenEfecto');
            		cur.filters.alpha.opacity = cont+=2;
            		cont-=1;
            		document.getElementById('porciento').innerHTML = percent + "%";
            		percent+=2;
            		if(percent>=100){
            			completado();
                	}	
                }else{
                	var cur = document.getElementById('imagenEfecto');
            		cur.filters.alpha.opacity = cont++;
                }
            
            
            setTimeout('efecto()',1);
    	}
 }

function completado(){
		if(completo < 2){
			document.getElementById('porciento').innerHTML = "Completado";
			completo++;
			setTimeout('completado()',100);
		}else{
			document.getElementById('porciento').style.visibility = 'hidden'; 
			return;
		}
}
	
function openLogin(){
       document.getElementById('all').style.visibility = 'visible';

}

         function desplegar(){
					if(ancho==233){
					  openLogin();
						return
					}else{
					    document.getElementById('imagenEfecto').style.height = ancho++;
						var timer = setTimeout('desplegar()',1);
					}
}

         function dirigir(){
				desplegar();
				desplegar();
				efecto();
				return;
             }
       
</script>

</head>

<body onload="dirigir()" link="#550000" vlink="#550000" alink="#550000">

       <div id="efectImage">
     <div id="imagenEfecto" style="position:absolute; overflow:hidden; left:312px; top:154px; width:387px; height:46px;FILTER: alpha(opacity=0)"><img src="images2/imagenEfecto.JPG" alt="" border=0 width=387 height=233></div>
     </div>
<div id='all' style="visibility: hidden;">
<div id="image1" style="position:absolute; overflow:hidden; left:312px; top:153px; width:387px; height:233px;z-index:0"><img src="images2/login1.JPG" alt="" border=0 width=387 height=233></div>


<form name='procesar' action="procesar" method="post">
<input name="user" alt='Ingrese su usuario' type="text" style="position:absolute;width:164px;left:511px;top:256px;border: solid;border-width: 1px">
<input name="pass" alt='Ingrese su password' type="password" style="position:absolute;width:164px;left:511px;top:300px;border: solid;border-width: 1px">
<input name="start" type="button" onclick="validar(user.value,pass.value)" value="Iniciar sesión" style="position:absolute;left:560px;top:333px;border: solid;background: #E2E2E2;border-width: 1px">
</form>

<div id="art1" style="position:absolute; overflow:hidden; left:344px; top:163px; width:143px; height:30px; z-index:4"><img border=0 alt="Prisiones-Cuba" src="images2/art8190578.gif"></div>

<div id="text1" style="position:absolute; overflow:hidden; left:624px; top:163px; width:62px; height:20px; z-index:5">

</div>

<div  id="image2" style="position:absolute; overflow:hidden; left:677px; top:257px; width:15px; height:20px;visibility: hidden"><img src="images2/img8785109.png" alt="" border=0 width=15 height=20></div>

<div  id="image3" style="position:absolute; overflow:hidden; left:676px; top:301px; width:15px; height:20px;visibility: hidden"><img src="images2/img8785125.png" alt="" border=0 width=15 height=20></div>

<div id="text2" style="position:absolute;color:#F00000; overflow:hidden; left:700px; top:259px; width:255px; height:19px;FILTER: alpha(opacity=35)"><div class="wpmd">
<div><font class="ws11"></font></div>
</div></div>

<div id="text3" style="position:absolute;color:#F00000; overflow:hidden; left:700px; top:302px; width:255px; height:19px;FILTER: alpha(opacity=35)"><div class="wpmd">
<div><font class="ws11"></font></div>
</div></div>
     </div>
     <div id='porciento' style="position: absolute;left: 600px;top:170px;color:white"></div>

</body>
</html>
