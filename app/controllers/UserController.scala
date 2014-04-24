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
      User.create(user)
      Ok("ユーザ登録完了")
    }
  }

  def update(id: String) = RequiresAuthentication(Constant.GoogleOpenId) { profile =>
    Action { request =>
      val user = request.body.asJson.get.as[User]
      User.update(user)
      Ok("ユーザ更新完了")
    }
  }

  def delete(id: String) = RequiresAuthentication(Constant.GoogleOpenId) { profile =>
    Action { request =>
      User.delete(id)
      Ok("ユーザ削除完了")
    }
  }

}
