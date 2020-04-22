package inventario

import grails.gorm.services.Service

@Service(NomCantRam)
interface NomCantRamService {

    NomCantRam get(Serializable id)

    List<NomCantRam> list(Map args)

    Long count()

    void delete(Serializable id)

    NomCantRam save(NomCantRam nomCantRam)

}