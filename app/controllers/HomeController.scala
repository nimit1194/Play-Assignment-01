package controllers

import javax.print.attribute.standard.JobOriginatingUserName
import play.api._
import javax.inject._
import play.api.mvc._

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController extends Controller {


  def index () = Action { implicit request =>

    Ok(views.html.index())

  }

  def receiveInfo() = Action {
    implicit request =>

    val agentId = request.session.get("agentId")
    val agentName = request.session.get("agentName")

    val (keyOfMessage, message) =
    {if (agentId.contains("7") && agentName.contains("JB"))
      ("S", "Welcome 007, Your next mission is to save the world from Aliens. Message will be automatically deleted once seen !!!")
    else
      ("E"," Agency couldn't recognize you! You will be executed. Runnnnnnnnnnnnnn!!!!!!!!!!!!")
    }
    Redirect(routes.HomeController.displayInfo()).flashing(keyOfMessage -> message)

  }
  def sendInfo (agentId: Int, agentName: String) = Action { implicit request =>


    Redirect(routes.HomeController.receiveInfo()).withSession("agentId" -> s"$agentId", "agentName" -> s"$agentName")

  }

  def displayInfo() = Action {
    implicit request =>

      Ok(views.html.message())

  }

}


