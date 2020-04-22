package inventario

import grails.gorm.services.Service

@Service(NomMarcaPortatil)
interface NomMarcaPortatilService {

    NomMarcaPortatil get(Serializable id)

    List<NomMarcaPortatil> list(Map args)

    Long count()

    void delete(Serializable id)

    NomMarcaPortatil save(NomMarcaPortatil nomMarcaPortatil)

}