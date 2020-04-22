package inventario

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DispositivoServiceSpec extends Specification {

    DispositivoService dispositivoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Dispositivo(...).save(flush: true, failOnError: true)
        //new Dispositivo(...).save(flush: true, failOnError: true)
        //Dispositivo dispositivo = new Dispositivo(...).save(flush: true, failOnError: true)
        //new Dispositivo(...).save(flush: true, failOnError: true)
        //new Dispositivo(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //dispositivo.id
    }

    void "test get"() {
        setupData()

        expect:
        dispositivoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Dispositivo> dispositivoList = dispositivoService.list(max: 2, offset: 2)

        then:
        dispositivoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        dispositivoService.count() == 5
    }

    void "test delete"() {
        Long dispositivoId = setupData()

        expect:
        dispositivoService.count() == 5

        when:
        dispositivoService.delete(dispositivoId)
        sessionFactory.currentSession.flush()

        then:
        dispositivoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Dispositivo dispositivo = new Dispositivo()
        dispositivoService.save(dispositivo)

        then:
        dispositivo.id != null
    }
}
