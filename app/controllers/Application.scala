package controllers

import org.pac4j.play.scala.ScalaController

import play.api.libs.json.Json
import play.api.mvc.Action
import utils.Constant

object Application extends ScalaController {

  def index = Action { request =>
    Redirect("/home/")
  }

  def googleauth = RequiresAuthentication(Constant.GoogleOpenId) { profile =>
    Action { request =>
      Redirect("/home/")
    }
  }

  def currentuser = RequiresAuthentication(Constant.GoogleOpenId) { profile =>
    Action { request =>
      val name = String.format("%s %s", profile.getFamilyName(), profile.getFirstName())
      val currentuser = Json.toJson(
              Map(
                "email" -> profile.getEmail(),
                "displayName" -> name))
      Ok(currentuser)
    }
  }

}
