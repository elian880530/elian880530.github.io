package incimed

class BootStrap {

    def init = {

        /**
         def adminRole = new Role(authority: 'ROLE_ADMIN').save()

         def testUser = new User(username: 'administrador', password: 'administrador').save()

         UserRole.create testUser, adminRole

         UserRole.withSession {it.flush()
         it.clear()}assert User.count() == 1
         assert Role.count() == 1
         assert UserRole.count() == 1
         */
    }


    def destroy = {
    }
}
