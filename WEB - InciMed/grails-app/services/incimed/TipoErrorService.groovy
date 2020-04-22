package incimed

import grails.gorm.services.Service

@Service(TipoError)
interface TipoErrorService {

    TipoError get(Serializable id)

    List<TipoError> list(Map args)

    Long count()

    void delete(Serializable id)

    TipoError save(TipoError tipoError)

}