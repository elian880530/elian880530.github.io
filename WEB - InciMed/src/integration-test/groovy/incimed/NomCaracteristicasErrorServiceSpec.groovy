package incimed

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class NomCaracteristicasErrorServiceSpec extends Specification {

    NomCaracteristicasErrorService nomCaracteristicasErrorService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new NomCaracteristicasError(...).save(flush: true, failOnError: true)
        //new NomCaracteristicasError(...).save(flush: true, failOnError: true)
        //NomCaracteristicasError nomCaracteristicasError = new NomCaracteristicasError(...).save(flush: true, failOnError: true)
        //new NomCaracteristicasError(...).save(flush: true, failOnError: true)
        //new NomCaracteristicasError(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //nomCaracteristicasError.id
    }

    void "test get"() {
        setupData()

        expect:
        nomCaracteristicasErrorService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<NomCaracteristicasError> nomCaracteristicasErrorList = nomCaracteristicasErrorService.list(max: 2, offset: 2)

        then:
        nomCaracteristicasErrorList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        nomCaracteristicasErrorService.count() == 5
    }

    void "test delete"() {
        Long nomCaracteristicasErrorId = setupData()

        expect:
        nomCaracteristicasErrorService.count() == 5

        when:
        nomCaracteristicasErrorService.delete(nomCaracteristicasErrorId)
        sessionFactory.currentSession.flush()

        then:
        nomCaracteristicasErrorService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        NomCaracteristicasError nomCaracteristicasError = new NomCaracteristicasError()
        nomCaracteristicasErrorService.save(nomCaracteristicasError)

        then:
        nomCaracteristicasError.id != null
    }
}
