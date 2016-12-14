package models.validation

sealed trait InputType
case class IntType() extends InputType
case class BoolType() extends InputType
case class EnumType(vals:List[String]) extends InputType

//Interface for code generated configurations with a few helpers for the template engine
trait IValidator {
  def features :List[(String, String, InputType)]
  def validate (m:Map[String,String]) :Boolean
  def getError (m:Map[String,String]) :String
  def validationOption (m:Map[String,String]) :Option[String]

  def getType(i: Integer): Integer = {
    val p = getParam(i)
    p._3 match {
      case _: EnumType => 1
      case _: IntType => 2
      case _: BoolType => 3
    }
  }

  def getList(i: Integer): List[String] = {
    if (getType(i) == 1) {
      features(i)._3.asInstanceOf[EnumType].vals
    }
    else {
      List("")
    }
  }

  def getParam(i: Integer): (String, String, InputType) = {
    features(i)
  }

  def validateConf(m: Map[String, String]): String = {
    getError(m)
  }

  def length: Integer = features.length
}
