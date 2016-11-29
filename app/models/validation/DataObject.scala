package models.validation
import models.validation.Validator.{BoolType, EnumType, InputType, IntType}

// Thin wrapper around the Validator with a few helpers for the templates

class DataObject() {

  def length: Integer = Validator.features.length

  def getType(i: Integer): Integer = {
    val p = getParam(i)
    p._3 match {
      case _ : EnumType => 1
      case _ : IntType => 2
      case _ : BoolType => 3
    }
  }

  def getList(i: Integer): List[String] = {
    if(getType(i) == 1){
      Validator.features(i)._3.asInstanceOf[EnumType].vals
    }
    else {
      List("")
    }
  }

  def getParam(i: Integer): (String, String, InputType) = {
    Validator.features(i)
  }

  def validate(m: Map[String, String]) : Option[String] = {
    if( !Validator.validate(m) ){
      Validator.validationOption(m)
    }
    else {
      Some("OK")
    }
  }
}
