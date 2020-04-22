package inventario

import grails.gorm.services.Service

@Service(NomMarcaPc)
interface NomMarcaPcService {

    NomMarcaPc get(Serializable id)

    List<NomMarcaPc> list(Map args)

    Long count()

    void delete(Serializable id)

    NomMarcaPc save(NomMarcaPc nomMarcaPc)

}