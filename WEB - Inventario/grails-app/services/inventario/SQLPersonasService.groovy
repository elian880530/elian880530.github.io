package inventario

import groovy.sql.Sql
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.scheduling.annotation.Scheduled

@Slf4j
@CompileStatic
class SQLPersonasService {

    boolean lazyInit = false

    void sqlPersonas() {

        def sql1 = Sql.newInstance("jdbc:mysql://192.168.100.15:3306/c0_asignaciones", "c0_asignaciones", "eNzpxgW9SMD4_", "com.mysql.jdbc.Driver")
        def sql2 = Sql.newInstance("jdbc:mysql://localhost/inventario", "root", "Amanecer.32", "com.mysql.jdbc.Driver")
        //def sql2 = Sql.newInstance("jdbc:mysql://192.168.100.51/inventario", "root", "Amanecer.32", "com.mysql.jdbc.Driver")

        def var1 = sql1.rows("SELECT\n" +
                "  A3_dbo_datos_empleados_amadip.NIF,\n" +
                "  A3_dbo_datos_empleados_amadip.NOMBRE,\n" +
                "  A3_dbo_datos_empleados_amadip.APELLIDO1,\n" +
                "  A3_dbo_datos_empleados_amadip.APELLIDO2,\n" +
                "  A3_dbo_datos_empleados_amadip.DEPARTAMENTO,\n" +
                "  A3_dbo_datos_empleados_amadip.ID_BAJA,\n" +
                "  A3_dbo_datos_empleados_amadip.EmployeeID,\n" +
                "  A3_dbo_datos_empleados_amadip.EMAIL\n" +
                "FROM A3_dbo_datos_empleados_amadip")

        def var2 = sql2.rows("SELECT\n" +
                "  persona.version,\n" +
                "  persona.correo,\n" +
                "  persona.departamento,\n" +
                "  persona.doc_identificativo,\n" +
                "  persona.apellido1,\n" +
                "  persona.nombre,\n" +
                "  persona.bajaID,\n" +
                "  persona.employeeID,\n" +
                "  persona.apellido2\n" +
                "FROM persona")

        def varEmployeeID = false

        var1.each { valor1 ->

            var2.each { valor2 ->

                if (valor1.EmployeeID == valor2.employeeID) {

                    varEmployeeID = true

                    if (valor1.EMAIL != valor2.correo) {

                        sql2.execute("UPDATE persona\n" +
                                "SET version = ${+1},\n" +
                                "    correo = \'${valor1.EMAIL}\' ")

                        println "Ejecutando tarea de UPDATE -- EMAIL --" + valor1
                    }
                    else if (valor1.DEPARTAMENTO != valor2.departamento) {

                        sql2.execute("UPDATE persona\n" +
                                "SET version = ${+1},\n" +
                                "departamento = \'${valor1.DEPARTAMENTO}\' ")

                        println "Ejecutando tarea de UPDATE -- DEPARTAMENTO --" + valor1
                    }
                    else if (valor1.NIF != valor2.doc_identificativo) {

                        sql2.execute("UPDATE persona\n" +
                                "SET version = ${+1},\n" +
                                "doc_identificativo = \'${valor1.NIF}\' ")

                        println "Ejecutando tarea de UPDATE -- doc_identificativo --" + valor1
                    }
                    else if (valor1.APELLIDO1 != valor2.apellido1) {

                        sql2.execute("UPDATE persona\n" +
                                "SET version = ${+1},\n" +
                                "apellido1 = \'${valor1.APELLIDO1}\' ")

                        println "Ejecutando tarea de UPDATE -- APELLIDO1 --" + valor1
                    }
                    else if (valor1.APELLIDO2 != valor2.apellido2) {

                        sql2.execute("UPDATE persona\n" +
                                "SET version = ${+1},\n" +
                                "apellido2 = \'${valor1.APELLIDO2}\' ")

                        println "Ejecutando tarea de UPDATE -- APELLIDO2 --" + valor1
                    }
                    else if (valor1.ID_BAJA != valor2.bajaID) {

                        sql2.execute("UPDATE persona\n" +
                                "SET version = ${+1},\n" +
                                "bajaID = \'${valor1.ID_BAJA}\' ")

                        println "Ejecutando tarea de UPDATE -- ID_BAJA --" + valor1
                    }
                    else if (valor1.NOMBRE != valor2.nombre) {

                        sql2.execute("UPDATE persona\n" +
                                "SET version = ${+1},\n" +
                                "nombre = \'${valor1.NOMBRE}\' ")

                        println "Ejecutando tarea de UPDATE -- NOMBRE --" + valor1
                    }
                    else {

                        println "No se realizaron cambios en --" + valor1
                    }



                }
            }

            if (varEmployeeID == false) {
                sql2.execute("INSERT persona\n" +
                        "SET version = ${0},\n" +
                        "    correo = \'${valor1.EMAIL}\',\n" +
                        "    departamento = \'${valor1.DEPARTAMENTO}\',\n" +
                        "    doc_identificativo = \'${valor1.NIF}\',\n" +
                        "    apellido1 = \'${valor1.APELLIDO1}\',\n" +
                        "    apellido2 = \'${valor1.APELLIDO2}\',\n" +
                        "    bajaID = \'${valor1.ID_BAJA}\',\n" +
                        "    employeeID = \'${valor1.EmployeeID}\',\n" +
                        "    nombre = \'${valor1.NOMBRE}\' ")

                println "Ejecutando tarea de INSERT --" + valor1

            }

            varEmployeeID == false

        }

        sql1.close()
        sql2.close()

    }

    @Scheduled(cron = "0 30 4 1/1 * ?")
    void execute() {
        println "Ejecutando el cron de las 4 30 AM"
        sqlPersonas()
    }

}
