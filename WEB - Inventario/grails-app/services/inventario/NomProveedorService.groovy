package inventario

import grails.gorm.services.Service

@Service(NomProveedor)
interface NomProveedorService {

    NomProveedor get(Serializable id)

    List<NomProveedor> list(Map args)

    Long count()

    void delete(Serializable id)

    NomProveedor save(NomProveedor nomProveedor)

}