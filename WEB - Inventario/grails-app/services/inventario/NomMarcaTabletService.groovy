package inventario

import grails.gorm.services.Service

@Service(NomMarcaTablet)
interface NomMarcaTabletService {

    NomMarcaTablet get(Serializable id)

    List<NomMarcaTablet> list(Map args)

    Long count()

    void delete(Serializable id)

    NomMarcaTablet save(NomMarcaTablet nomMarcaTablet)

}