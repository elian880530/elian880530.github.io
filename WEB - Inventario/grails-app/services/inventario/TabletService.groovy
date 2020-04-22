package inventario

import grails.gorm.services.Service

@Service(Tablet)
interface TabletService {

    Tablet get(Serializable id)

    List<Tablet> list(Map args)

    Long count()

    void delete(Serializable id)

    Tablet save(Tablet tablet)

}