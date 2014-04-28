package controllers

import org.pac4j.play.scala.ScalaController
import models.User
import play.api.mvc.Action
import utils.Constant
import utils.JsonUtil
import play.api.libs.json.Json

object UserController extends ScalaController {

  def findAll() = RequiresAuthentication(Constant.GoogleOpenId) { profile =>
    Action { request =>
      val users = User.findAll()
      Ok(JsonUtil.toJson(users))
    }
  }

  def show(id: String) = RequiresAuthentication(Constant.GoogleOpenId) { profile =>
    Action { request =>
      val user = User.findById(id)
      Ok(JsonUtil.toJson(user))
    }
  }

  def create() = RequiresAuthentication(Constant.GoogleOpenId) { profile =>
    Action { request =>
      val user = request.body.asJson.get.as[User]
      val userId = User.create(user)
      val createdUser = User.findById(userId.toString)
      Ok(JsonUtil.toJson(createdUser))
    }
  }

  def update(id: String) = RequiresAuthentication(Constant.GoogleOpenId) { profile =>
    Action { request =>
      val user = request.body.asJson.get.as[User]
      User.update(user)
      Ok(JsonUtil.toJson(user))
    }
  }

  def delete(id: String) = RequiresAuthentication(Constant.GoogleOpenId) { profile =>
    Action { request =>
      val user = User.findById(id)
      User.delete(id)
      Ok(JsonUtil.toJson(user))
    }
  }

}
