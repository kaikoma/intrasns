package models
 
import play.api.db._
import play.api.Play.current
import anorm._
import anorm.SqlParser._
import play.api.libs.json.Json
 
case class User(userId: String, position: Option[String], workLocation: String)

object User {

  implicit val userFormat = Json.format[User]
 
  val simple = {
    get[String]("user_id") ~
    get[Option[String]]("position") ~
    get[String]("work_location") map {
      case user_id~position~work_location => User(user_id, position, work_location)
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
        """select * from User where user_id = {userId}"""
      ).on("userId" -> id).as(User.simple.single)
    }
  }

  def create(user: User): Unit = {
    DB.withConnection { implicit connection =>
      SQL(
        """
          insert into User(user_id, position, work_location)
          values ({userId}, {position}, {workLocation})
        """
        ).on(
        'userId -> user.userId,
        'position -> user.position,
        'workLocation -> user.workLocation
      ).executeUpdate()
    }
  }

  def update(user: User): Unit = {
    DB.withConnection { implicit connection =>
      SQL(
        """
          UPDATE User SET
            user_id = {userId},
            position = {position},
            work_location = {workLocation}
          WHERE user_id = {userId}
        """
        ).on(
        'userId -> user.userId,
        'position -> user.position,
        'workLocation -> user.workLocation
      ).executeUpdate()
    }
  }

  def delete(id: String): Unit = {
    DB.withConnection { implicit connection =>
      SQL(
        """
          DELETE FROM User
          WHERE user_id = {userId}
        """
        ).on(
        'userId -> id
      ).executeUpdate()
    }
  }

}