package incimed

import grails.gorm.services.Service

@Service(CausasError)
interface CausasErrorService {

    CausasError get(Serializable id)

    List<CausasError> list(Map args)

    Long count()

    void delete(Serializable id)

    CausasError save(CausasError causasError)

}