package controllers

import play.api.mvc._
import models.validation._

//main controller for the configuration application
object Application extends Controller {

  val v: IValidator = CarValidator

  //generates default product configuration view
  def productconfig = Action {
    Ok(views.html.productconfig(v, "-"))
  }

  //validate configuration and generate status view
  def parseRequest = Action { implicit request =>
    val status = v.validateConf( confBuilder(0, v.length, Map.empty[String,String], request) )
    Ok(views.html.productconfig(v, status.toString))
  }

  //builds map with selected configuration values from request
  def confBuilder(i: Int, featureCount: Int, confMap: Map[String, String], r: Request[AnyContent] ):
      Map[String, String] = {

    val paramName: String = v.getParam(i)._1
    val paramVal: String = r.body.asFormUrlEncoded.get(paramName).head
    val cm = confMap ++ Map( paramName -> paramVal )
    //println(cm)
    if( i < featureCount-1 ){
      confBuilder( i+1, featureCount, cm, r )
    }
    else {
      cm
    }
  }
}