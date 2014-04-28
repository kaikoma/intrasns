package models
 
import play.api.db._
import play.api.Play.current
import anorm._
import anorm.SqlParser._
import play.api.libs.json.Json
 
case class User(id: Option[Long], userId: String, name: String, email: String, resignationFlg: String, adminFlg: String)

object User {

  implicit val userFormat = Json.format[User]
 
  val simple = {
    get[Option[Long]]("id") ~
    get[String]("user_id") ~
    get[String]("name") ~
    get[String]("email") ~
    get[String]("resignation_flg") ~
    get[String]("admin_flg")  map {
      case id~user_id~name~email~resignation_flg~admin_flg => User(id, user_id, name, email, resignation_flg, admin_flg)
    }
  }
 
  def findAll(): Seq[User] = {
    DB.withConnection { implicit connection =>
      SQL("""select * from User""").as(User.simple *)
    }
  }
 
  def findById(id: String): User = {
    DB.withConnection { implicit connection =>
      SQL(
        """select * from User where id = {id}"""
      ).on("id" -> id).as(User.simple.single)
    }
  }

  def create(user: User): Long = {
    DB.withConnection { implicit connection =>
      SQL(
        """
          insert into User(user_id, name, email, resignation_flg, admin_flg)
          values ({userId}, {name}, {email}, {resignationFlg}, {adminFlg})
        """
        ).on(
        'userId -> user.userId,
        'name -> user.name,
        'email -> user.email,
        'resignationFlg -> user.resignationFlg,
        'adminFlg -> user.adminFlg
      ).executeInsert(scalar[Long].single)
    }
  }

  def update(user: User): Unit = {
    DB.withConnection { implicit connection =>
      SQL(
        """
          UPDATE User SET
            user_id = {userId},
            name = {name},
            email = {email},
            resignation_flg = {resignationFlg},
            admin_flg = {adminFlg}
          WHERE id = {id}
        """
        ).on(
        'id -> user.id,
        'userId -> user.userId,
        'name -> user.name,
        'email -> user.email,
        'resignationFlg -> user.resignationFlg,
        'adminFlg -> user.adminFlg
      ).executeUpdate()
    }
  }

  def delete(id: String): Unit = {
    DB.withConnection { implicit connection =>
      SQL(
        """
          DELETE FROM User
          WHERE id = {id}
        """
        ).on(
        'id -> id
      ).executeUpdate()
    }
  }

}