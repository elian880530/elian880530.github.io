package incimed

import grails.gorm.services.Service

@Service(IncidenciaMedicacion)
interface IncidenciaMedicacionService {

    IncidenciaMedicacion get(Serializable id)

    List<IncidenciaMedicacion> list(Map args)

    Long count()

    void delete(Serializable id)

    IncidenciaMedicacion save(IncidenciaMedicacion incidenciaMedicacion)

}