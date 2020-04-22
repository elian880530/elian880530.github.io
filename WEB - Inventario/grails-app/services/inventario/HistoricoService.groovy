package inventario

import grails.gorm.services.Service

@Service(Historico)
interface HistoricoService {

    Historico get(Serializable id)

    List<Historico> list(Map args)

    Long count()

    void delete(Serializable id)

    Historico save(Historico historico)

}