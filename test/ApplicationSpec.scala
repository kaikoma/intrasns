import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._
import play.api.mvc._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification {

  "Not Login" should {

    "send 404 on a bad request" in new WithApplication{
      route(FakeRequest(GET, "/boum")) must beNone
    }

    "root path redirect to home page" in new WithApplication{
      val home = route(FakeRequest(GET, "/")).get

      status(home) must equalTo(SEE_OTHER)
      redirectLocation(home) must equalTo(Some("/home/"))
    }

    "not Login home path redirect to login page" in new WithApplication{
      val home = route(FakeRequest(GET, "/home/")).get

      status(home) must equalTo(SEE_OTHER)
      redirectLocation(home) must equalTo(Some("/login"))
    }

  }

  "Logined" should {

    "Logined home page" in new WithApplication{
      val home = route(FakeRequest(GET, "/home/").withSession(("pac4jSessionId", "sessiontest"))).get

      status(home) must equalTo(OK)
    }
  }

}
