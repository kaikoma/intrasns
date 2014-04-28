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
        createDummyUsers()
        User.findAll.size must equalTo(5)
        User.create(User(None, "user006", "ユーザ　００６", "user006@aaa.com", "0","0"))
        User.findAll.size must equalTo(6)
        
        val findUser = User.findById("6")
        findUser.userId must equalTo("user006")
        findUser.name must equalTo("ユーザ　００６")
        findUser.email must equalTo("user006@aaa.com")
        findUser.resignationFlg must equalTo("0")
        findUser.adminFlg must equalTo("0")
      }
    }

    "User Update" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        createDummyUsers()
        User.findAll.size must equalTo(5)

        val updateUser = User(Some(5), "user005", "ユーザ　００５Ａ", "user005a@aaa.com", "1","1")
        User.update(updateUser)

        val findUser = User.findById("5")
        findUser.userId must equalTo("user005")
        findUser.name must equalTo("ユーザ　００５Ａ")
        findUser.email must equalTo("user005a@aaa.com")
        findUser.resignationFlg must equalTo("1")
        findUser.adminFlg must equalTo("1")
      }
    }

    "User Find All" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        createDummyUsers()
        User.findAll.size must equalTo(5)
      }
    }

    "User Delete" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        createDummyUsers()
        User.findAll.size must equalTo(5)

        User.delete("5")

        User.findAll.size must equalTo(4)        

        User.delete("1")

        User.findAll.size must equalTo(3)

      }
    }
  }
  
  def createDummyUsers() {
    User.create(User(None, "user001", "ユーザ　００１", "user001@aaa.com", "0", "1"))
    User.create(User(None, "user002", "ユーザ　００２", "user002@aaa.com", "0", "1"))
    User.create(User(None, "user003", "ユーザ　００３", "user003@aaa.com", "0", "1"))
    User.create(User(None, "user004", "ユーザ　００４", "user004@aaa.com", "0", "1"))
    User.create(User(None, "user005", "ユーザ　００５", "user005@aaa.com", "0", "1"))
  }

}
