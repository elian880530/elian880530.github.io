package incimed

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class NomCasaServiceSpec extends Specification {

    NomCasaService nomCasaService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new NomCasa(...).save(flush: true, failOnError: true)
        //new NomCasa(...).save(flush: true, failOnError: true)
        //NomCasa nomCasa = new NomCasa(...).save(flush: true, failOnError: true)
        //new NomCasa(...).save(flush: true, failOnError: true)
        //new NomCasa(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //nomCasa.id
    }

    void "test get"() {
        setupData()

        expect:
        nomCasaService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<NomCasa> nomCasaList = nomCasaService.list(max: 2, offset: 2)

        then:
        nomCasaList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        nomCasaService.count() == 5
    }

    void "test delete"() {
        Long nomCasaId = setupData()

        expect:
        nomCasaService.count() == 5

        when:
        nomCasaService.delete(nomCasaId)
        sessionFactory.currentSession.flush()

        then:
        nomCasaService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        NomCasa nomCasa = new NomCasa()
        nomCasaService.save(nomCasa)

        then:
        nomCasa.id != null
    }
}
