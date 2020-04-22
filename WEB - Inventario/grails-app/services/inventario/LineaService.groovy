package inventario

import grails.gorm.services.Service

@Service(Linea)
interface LineaService {

    Linea get(Serializable id)

    List<Linea> list(Map args)

    Long count()

    void delete(Serializable id)

    Linea save(Linea linea)

}