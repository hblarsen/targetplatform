package controllers

import play.api.mvc._
import models.validation._

//main controller for the configuration application
object Application extends Controller {

  //Validator type is set here (could be loaded from config)
  //val v: IValidator = CarValidator
  val validator: IValidator = PcValidator

  //generates default product configuration view
  def productconfig = Action {
    Ok(views.html.productconfig(validator, "-"))
  }

  //validate submitted configuration and generate text for status view
  def parseRequest = Action { implicit request =>
    val userConfig = new UserConfiguration( confBuilder(0, validator.length, Map.empty[String,String], request) )
    val status = validator.validateConf( userConfig.read() )
    Ok(views.html.productconfig(validator, status.toString))
  }

  //builds map with selected configuration values from request
  def confBuilder(i: Int, featureCount: Int, confMap: Map[String, String], r: Request[AnyContent] ):
      Map[String, String] = {

    val paramName: String = validator.getParam(i)._1
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