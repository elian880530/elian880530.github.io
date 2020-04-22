package inventario

import grails.gorm.services.Service

@Service(Portatil)
interface PortatilService {

    Portatil get(Serializable id)

    List<Portatil> list(Map args)

    Long count()

    void delete(Serializable id)

    Portatil save(Portatil portatil)

}