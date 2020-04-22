package inventario
import grails.plugin.springsecurity.annotation.Secured

@Secured("ROLE_Z_ITEMBOX_ADMIN")
class SecureController {

    def index() {
        render view:"/index"
    }

}
