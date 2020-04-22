package incimed

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CausasErrorServiceSpec extends Specification {

    CausasErrorService causasErrorService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new CausasError(...).save(flush: true, failOnError: true)
        //new CausasError(...).save(flush: true, failOnError: true)
        //CausasError causasError = new CausasError(...).save(flush: true, failOnError: true)
        //new CausasError(...).save(flush: true, failOnError: true)
        //new CausasError(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //causasError.id
    }

    void "test get"() {
        setupData()

        expect:
        causasErrorService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<CausasError> causasErrorList = causasErrorService.list(max: 2, offset: 2)

        then:
        causasErrorList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        causasErrorService.count() == 5
    }

    void "test delete"() {
        Long causasErrorId = setupData()

        expect:
        causasErrorService.count() == 5

        when:
        causasErrorService.delete(causasErrorId)
        sessionFactory.currentSession.flush()

        then:
        causasErrorService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        CausasError causasError = new CausasError()
        causasErrorService.save(causasError)

        then:
        causasError.id != null
    }
}
