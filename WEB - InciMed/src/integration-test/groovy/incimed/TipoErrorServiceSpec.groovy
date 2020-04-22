package incimed

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TipoErrorServiceSpec extends Specification {

    TipoErrorService tipoErrorService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new TipoError(...).save(flush: true, failOnError: true)
        //new TipoError(...).save(flush: true, failOnError: true)
        //TipoError tipoError = new TipoError(...).save(flush: true, failOnError: true)
        //new TipoError(...).save(flush: true, failOnError: true)
        //new TipoError(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //tipoError.id
    }

    void "test get"() {
        setupData()

        expect:
        tipoErrorService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<TipoError> tipoErrorList = tipoErrorService.list(max: 2, offset: 2)

        then:
        tipoErrorList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        tipoErrorService.count() == 5
    }

    void "test delete"() {
        Long tipoErrorId = setupData()

        expect:
        tipoErrorService.count() == 5

        when:
        tipoErrorService.delete(tipoErrorId)
        sessionFactory.currentSession.flush()

        then:
        tipoErrorService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        TipoError tipoError = new TipoError()
        tipoErrorService.save(tipoError)

        then:
        tipoError.id != null
    }
}
