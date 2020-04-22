package incimed

import grails.gorm.services.Service

@Service(NomMomentoDia)
interface NomMomentoDiaService {

    NomMomentoDia get(Serializable id)

    List<NomMomentoDia> list(Map args)

    Long count()

    void delete(Serializable id)

    NomMomentoDia save(NomMomentoDia nomMomentoDia)

}