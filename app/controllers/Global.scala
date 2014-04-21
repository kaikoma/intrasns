package controllers

import scala.concurrent.Future

import org.pac4j.core.client.Clients
import org.pac4j.openid.client.GoogleOpenIdClient
import org.pac4j.play.Config
import org.pac4j.play.Constants
import org.slf4j.LoggerFactory

import play.api.Application
import play.api.GlobalSettings
import play.api.Play
import play.api.Play.current
import play.api.mvc.Action
import play.api.mvc.Handler
import play.api.mvc.RequestHeader
import play.api.mvc.Results.InternalServerError
import play.api.mvc.Results.Redirect

object Global extends GlobalSettings {

  protected val logger = LoggerFactory.getLogger("controllers.Global")

  override def onError(request: RequestHeader, t: Throwable) = {
    Future.successful(InternalServerError(
      views.html.error500.render()))
  }

  override def onStart(app: Application) {
    Config.setErrorPage401(views.html.error401.render().toString())
    Config.setErrorPage403(views.html.error403.render().toString())

    val baseUrl = Play.application.configuration.getString("baseUrl").get

    // OpenID
    val googleOpenIdClient = new GoogleOpenIdClient()

    val clients = new Clients(baseUrl + "/callback", googleOpenIdClient)
    Config.setClients(clients)
    // for test purposes : profile timeout = 60 seconds
    //Config.setProfileTimeout(60)
  }

  override def onRouteRequest(request: RequestHeader): Option[Handler] = {
    val reqStr = request.path
    //logger.debug("request.path: " + reqStr);
    if (reqStr.endsWith(".css")
      || reqStr.endsWith(".js")
      || reqStr.endsWith(".ico")) {
      super.onRouteRequest(request)
    } else if (!reqStr.endsWith("/home/")) {
      // publicのhtmlのパス以外は認証チェックしない。
      // play のアクションで認証チェックしている。
      super.onRouteRequest(request)
    } else {
      // publicのhtmlのパスのみ認証チェックする。
      var newSession = request.session
      val optionSessionId = newSession.get(Constants.SESSION_ID)
      if (!optionSessionId.isDefined) {
        Some(Action{ Redirect("/login")})
      } else {
        super.onRouteRequest(request)
      }
    }
  }
}
