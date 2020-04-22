package inventario

import grails.gorm.services.Service

@Service(Dispositivo)
interface DispositivoService {

    Dispositivo get(Serializable id)

    List<Dispositivo> list(Map args)

    Long count()

    void delete(Serializable id)

    Dispositivo save(Dispositivo dispositivo)

}