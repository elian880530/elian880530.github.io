package incimed

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class NomCausasErrorServiceSpec extends Specification {

    NomCausasErrorService nomCausasErrorService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new NomCausasError(...).save(flush: true, failOnError: true)
        //new NomCausasError(...).save(flush: true, failOnError: true)
        //NomCausasError nomCausasError = new NomCausasError(...).save(flush: true, failOnError: true)
        //new NomCausasError(...).save(flush: true, failOnError: true)
        //new NomCausasError(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //nomCausasError.id
    }

    void "test get"() {
        setupData()

        expect:
        nomCausasErrorService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<NomCausasError> nomCausasErrorList = nomCausasErrorService.list(max: 2, offset: 2)

        then:
        nomCausasErrorList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        nomCausasErrorService.count() == 5
    }

    void "test delete"() {
        Long nomCausasErrorId = setupData()

        expect:
        nomCausasErrorService.count() == 5

        when:
        nomCausasErrorService.delete(nomCausasErrorId)
        sessionFactory.currentSession.flush()

        then:
        nomCausasErrorService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        NomCausasError nomCausasError = new NomCausasError()
        nomCausasErrorService.save(nomCausasError)

        then:
        nomCausasError.id != null
    }
}
