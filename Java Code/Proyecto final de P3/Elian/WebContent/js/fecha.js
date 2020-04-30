  fecha = new Date()
    var days = new Array('Domingo','Lunes','Martes','Miércoles','Jueves','Viernes','Sábado')
    var months = new Array('Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre')

 function mostrarHora(){
 var dia  = fecha.getDay()
 var today = days[dia] + ', '+ fecha.getDate() + ' de ' + months[fecha.getMonth()] + ' de ' + fecha.getFullYear()
document.getElementById('div').innerHTML = today
setTimeout("mostrarHora()",1000)
}