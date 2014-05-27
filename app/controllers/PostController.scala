package controllers

import org.pac4j.play.scala.ScalaController
import play.api.mvc.Action
import play.api.libs.json.Json
import com.mongodb.casbah.Imports._

object PostController extends ScalaController {

  def jsontest() = Action { request =>
    val json: String = Json.stringify(request.body.asJson.get)
    val dbobj:DBObject = com.mongodb.util.JSON.parse(json).asInstanceOf[DBObject]

    val mongoClient = MongoClient("192.168.56.101", 27017)
    val db = mongoClient("mydb")
    val coll = db("test")
    coll.insert(dbobj)
    val count: String = coll.count().toString
    Ok(count)
  }

}
