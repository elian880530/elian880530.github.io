package inventario

import grails.gorm.services.Service

@Service(NomCantAlmacenamiento)
interface NomCantAlmacenamientoService {

    NomCantAlmacenamiento get(Serializable id)

    List<NomCantAlmacenamiento> list(Map args)

    Long count()

    void delete(Serializable id)

    NomCantAlmacenamiento save(NomCantAlmacenamiento nomCantAlmacenamiento)

}