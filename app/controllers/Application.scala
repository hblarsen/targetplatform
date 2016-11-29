package controllers

import play.api.mvc._
import models.validation.DataObject

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object Application extends Controller {

  def productconfig = Action {
    Ok(views.html.productconfig(new DataObject, "-"))
  }

  def parseRequest = Action { implicit request =>
    //TODO make recursive
    val dao: DataObject = new DataObject
    var currentConfig = Map.empty[String, String]

    var pfields = new ListBuffer[String]()

    for( t <- 0 to dao.length - 1 ){
      pfields += dao.getParam(t)._1
    }
    for( p <- pfields ) {
      currentConfig += (p -> request.body.asFormUrlEncoded.get(p).head)
    }

    val status = dao.validate(currentConfig)
    println(status)

    //Ok(views.html.main(currentConfig))
    Ok(views.html.productconfig(dao, status.toString))
  }

}
///////////////////////////////////////////////////////////////////////////////
/*

  // Not used
  def validate = Action { implicit request =>
    val ConfigData = ConfigForm.bindFromRequest.get
    //val params = Seq(ConfigData)
    Ok(views.html.result(ConfigData))
  }

  def ConfigForm = Form(mapping("os" -> text, "hd" -> text, "ram" -> text)(ConfigData.apply)(ConfigData.unapply))
  case class ConfigData(os: String, hd: String, ram: String)

 */