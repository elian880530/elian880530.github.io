package incimed

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class NomTipoErrorServiceSpec extends Specification {

    NomTipoErrorService nomTipoErrorService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new NomTipoError(...).save(flush: true, failOnError: true)
        //new NomTipoError(...).save(flush: true, failOnError: true)
        //NomTipoError nomTipoError = new NomTipoError(...).save(flush: true, failOnError: true)
        //new NomTipoError(...).save(flush: true, failOnError: true)
        //new NomTipoError(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //nomTipoError.id
    }

    void "test get"() {
        setupData()

        expect:
        nomTipoErrorService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<NomTipoError> nomTipoErrorList = nomTipoErrorService.list(max: 2, offset: 2)

        then:
        nomTipoErrorList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        nomTipoErrorService.count() == 5
    }

    void "test delete"() {
        Long nomTipoErrorId = setupData()

        expect:
        nomTipoErrorService.count() == 5

        when:
        nomTipoErrorService.delete(nomTipoErrorId)
        sessionFactory.currentSession.flush()

        then:
        nomTipoErrorService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        NomTipoError nomTipoError = new NomTipoError()
        nomTipoErrorService.save(nomTipoError)

        then:
        nomTipoError.id != null
    }
}
