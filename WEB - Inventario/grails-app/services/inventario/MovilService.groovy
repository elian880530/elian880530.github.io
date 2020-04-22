package inventario

import grails.gorm.services.Service

@Service(Movil)
interface MovilService {

    Movil get(Serializable id)

    List<Movil> list(Map args)

    Long count()

    void delete(Serializable id)

    Movil save(Movil movil)

}