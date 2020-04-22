package incimed

import grails.gorm.services.Service

@Service(NomCasa)
interface NomCasaService {

    NomCasa get(Serializable id)

    List<NomCasa> list(Map args)

    Long count()

    void delete(Serializable id)

    NomCasa save(NomCasa nomCasa)

}