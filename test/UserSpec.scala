import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._
import play.api.mvc._
import models._
import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
@RunWith(classOf[JUnitRunner])
class UserSpec extends Specification {

  "User CRUD" should {

    "User Create" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        val user = User("user001",Option("一般"),"恵比寿")
        User.create(user)
        val findUser = User.findById("user001")
        findUser.userId must equalTo("user001")
        findUser.position must equalTo(Option("一般"))
        findUser.workLocation must equalTo("恵比寿")
      }
    }

    "User Update" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        val user = User("user001",Option("一般"),"恵比寿")
        User.create(user)

        val updateUser = User("user001",Option("課長"),"品川")
        User.update(updateUser)

        val findUser = User.findById("user001")
        findUser.userId must equalTo("user001")
        findUser.position must equalTo(Option("課長"))
        findUser.workLocation must equalTo("品川")
      }
    }

  }

}
