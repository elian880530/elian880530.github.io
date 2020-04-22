package incimed

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CaracteristicasErrorServiceSpec extends Specification {

    CaracteristicasErrorService caracteristicasErrorService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new CaracteristicasError(...).save(flush: true, failOnError: true)
        //new CaracteristicasError(...).save(flush: true, failOnError: true)
        //CaracteristicasError caracteristicasError = new CaracteristicasError(...).save(flush: true, failOnError: true)
        //new CaracteristicasError(...).save(flush: true, failOnError: true)
        //new CaracteristicasError(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //caracteristicasError.id
    }

    void "test get"() {
        setupData()

        expect:
        caracteristicasErrorService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<CaracteristicasError> caracteristicasErrorList = caracteristicasErrorService.list(max: 2, offset: 2)

        then:
        caracteristicasErrorList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        caracteristicasErrorService.count() == 5
    }

    void "test delete"() {
        Long caracteristicasErrorId = setupData()

        expect:
        caracteristicasErrorService.count() == 5

        when:
        caracteristicasErrorService.delete(caracteristicasErrorId)
        sessionFactory.currentSession.flush()

        then:
        caracteristicasErrorService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        CaracteristicasError caracteristicasError = new CaracteristicasError()
        caracteristicasErrorService.save(caracteristicasError)

        then:
        caracteristicasError.id != null
    }
}
