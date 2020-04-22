package incimed

import grails.plugin.springsecurity.annotation.Secured

@Secured("ROLE_Z_INCIMED_ADMIN")
class SecureController {

    def index() {
        render view: "/index"
    }

}