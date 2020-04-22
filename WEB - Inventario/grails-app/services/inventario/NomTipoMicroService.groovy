package inventario

import grails.gorm.services.Service

@Service(NomTipoMicro)
interface NomTipoMicroService {

    NomTipoMicro get(Serializable id)

    List<NomTipoMicro> list(Map args)

    Long count()

    void delete(Serializable id)

    NomTipoMicro save(NomTipoMicro nomTipoMicro)

}