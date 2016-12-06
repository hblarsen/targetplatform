package models.validation

sealed trait InputType
case class IntType() extends InputType
case class BoolType() extends InputType
case class EnumType(vals:List[String]) extends InputType

trait Val {
  def features :List[(String, String, InputType)]
  def validate (m:Map[String,String]) :Boolean
  def getError (m:Map[String,String]) :String
  def validationOption (m:Map[String,String]) :Option[String]
}
