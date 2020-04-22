package inventario

import grails.gorm.services.Service

@Service(Pc)
interface PcService {

    Pc get(Serializable id)

    List<Pc> list(Map args)

    Long count()

    void delete(Serializable id)

    Pc save(Pc pc)

}