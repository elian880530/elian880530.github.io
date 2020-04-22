package inventario

import grails.gorm.services.Service

@Service(NomTarificacion)
interface NomTarificacionService {

    NomTarificacion get(Serializable id)

    List<NomTarificacion> list(Map args)

    Long count()

    void delete(Serializable id)

    NomTarificacion save(NomTarificacion nomTarificacion)

}