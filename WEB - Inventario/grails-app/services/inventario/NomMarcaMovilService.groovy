package inventario

import grails.gorm.services.Service

@Service(NomMarcaMovil)
interface NomMarcaMovilService {

    NomMarcaMovil get(Serializable id)

    List<NomMarcaMovil> list(Map args)

    Long count()

    void delete(Serializable id)

    NomMarcaMovil save(NomMarcaMovil nomMarcaMovil)

}