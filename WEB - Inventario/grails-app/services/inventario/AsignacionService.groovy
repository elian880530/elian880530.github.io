package inventario

import grails.gorm.services.Service

@Service(Asignacion)
interface AsignacionService {

    Asignacion get(Serializable id)

    List<Asignacion> list(Map args)

    Long count()

    void delete(Serializable id)

    Asignacion save(Asignacion asignacion)

}