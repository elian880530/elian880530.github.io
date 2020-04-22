package incimed

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class NomMomentoDiaServiceSpec extends Specification {

    NomMomentoDiaService nomMomentoDiaService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new NomMomentoDia(...).save(flush: true, failOnError: true)
        //new NomMomentoDia(...).save(flush: true, failOnError: true)
        //NomMomentoDia nomMomentoDia = new NomMomentoDia(...).save(flush: true, failOnError: true)
        //new NomMomentoDia(...).save(flush: true, failOnError: true)
        //new NomMomentoDia(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //nomMomentoDia.id
    }

    void "test get"() {
        setupData()

        expect:
        nomMomentoDiaService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<NomMomentoDia> nomMomentoDiaList = nomMomentoDiaService.list(max: 2, offset: 2)

        then:
        nomMomentoDiaList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        nomMomentoDiaService.count() == 5
    }

    void "test delete"() {
        Long nomMomentoDiaId = setupData()

        expect:
        nomMomentoDiaService.count() == 5

        when:
        nomMomentoDiaService.delete(nomMomentoDiaId)
        sessionFactory.currentSession.flush()

        then:
        nomMomentoDiaService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        NomMomentoDia nomMomentoDia = new NomMomentoDia()
        nomMomentoDiaService.save(nomMomentoDia)

        then:
        nomMomentoDia.id != null
    }
}
