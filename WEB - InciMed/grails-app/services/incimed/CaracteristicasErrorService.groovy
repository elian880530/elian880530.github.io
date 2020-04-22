package incimed

import grails.gorm.services.Service

@Service(CaracteristicasError)
interface CaracteristicasErrorService {

    CaracteristicasError get(Serializable id)

    List<CaracteristicasError> list(Map args)

    Long count()

    void delete(Serializable id)

    CaracteristicasError save(CaracteristicasError caracteristicasError)

}