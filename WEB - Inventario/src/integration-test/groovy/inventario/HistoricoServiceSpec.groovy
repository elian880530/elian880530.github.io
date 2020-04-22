package inventario

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class HistoricoServiceSpec extends Specification {

    HistoricoService historicoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Historico(...).save(flush: true, failOnError: true)
        //new Historico(...).save(flush: true, failOnError: true)
        //Historico historico = new Historico(...).save(flush: true, failOnError: true)
        //new Historico(...).save(flush: true, failOnError: true)
        //new Historico(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //historico.id
    }

    void "test get"() {
        setupData()

        expect:
        historicoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Historico> historicoList = historicoService.list(max: 2, offset: 2)

        then:
        historicoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        historicoService.count() == 5
    }

    void "test delete"() {
        Long historicoId = setupData()

        expect:
        historicoService.count() == 5

        when:
        historicoService.delete(historicoId)
        sessionFactory.currentSession.flush()

        then:
        historicoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Historico historico = new Historico()
        historicoService.save(historico)

        then:
        historico.id != null
    }
}
