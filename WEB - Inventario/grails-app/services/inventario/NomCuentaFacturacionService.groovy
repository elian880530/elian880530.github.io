package inventario

import grails.gorm.services.Service

@Service(NomCuentaFacturacion)
interface NomCuentaFacturacionService {

    NomCuentaFacturacion get(Serializable id)

    List<NomCuentaFacturacion> list(Map args)

    Long count()

    void delete(Serializable id)

    NomCuentaFacturacion save(NomCuentaFacturacion nomCuentaFacturacion)

}