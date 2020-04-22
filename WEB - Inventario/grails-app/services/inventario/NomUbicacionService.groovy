package inventario

import grails.gorm.services.Service

@Service(NomUbicacion)
interface NomUbicacionService {

    NomUbicacion get(Serializable id)

    List<NomUbicacion> list(Map args)

    Long count()

    void delete(Serializable id)

    NomUbicacion save(NomUbicacion nomUbicacion)

}