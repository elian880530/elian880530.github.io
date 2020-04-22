package incimed

import grails.gorm.services.Service

@Service(NomGravedadError)
interface NomGravedadErrorService {

    NomGravedadError get(Serializable id)

    List<NomGravedadError> list(Map args)

    Long count()

    void delete(Serializable id)

    NomGravedadError save(NomGravedadError nomGravedadError)

}