package models.validation

object CarValidator extends Val
{

  def features :List[(String, String, InputType)] =
    List(
      ("Fuel", "Choose a fuel type", EnumType(List(  "Diesel" ,  "Gasoline" ,  "Electric"  ))),
      ("Rims", "Choose rims type", EnumType(List(  "Aluminum" ,  "Other"  ))),
      ("RimsColor", "Choose rims color", EnumType(List(  "MatteBlack" ,  "Red"  ))),
      ("EnginePower", "Choose the power", IntType()),
      ("HeatedSeats", "Do you want heated seats?", BoolType()),
      ("ElectricWindows", "Do you want electric windows? ", BoolType())
    )

  def validate (m:Map[String,String]) :Boolean =
  {
    validationOption(m) match
    {
      case None => true
      case Some(e) => false
    }
  }


  def getError (m:Map[String,String]) :String =
  {
    validationOption(m) match
    {
      case None => ""
      case Some(e) => e
    }
  }

  def validationOption (m:Map[String,String]) :Option[String] =
  {
    if( (m("RimsColor")=="MatteBlack")  && !(( (m("Rims")=="Aluminum")  && ( (m("EnginePower")=="120")  ||  (m("EnginePower")=="150") ))))
      Some("RimsColor can only be MatteBlack if Rims is Aluminum and EnginePower is 120 or 150")
    else if( (m("HeatedSeats")=="true")  && !(( (m("Fuel")=="Diesel")  &&  (m("EnginePower")=="150") )))
      Some("RimsColor can only be MatteBlack if Rims is Aluminum and EnginePower is 120 or 150")
    else if( (m("ElectricWindows")=="true")  && !(( (m("Rims")=="Other")  ||  (m("Fuel")=="Gasoline") )))
      Some("RimsColor can only be MatteBlack if Rims is Aluminum and EnginePower is 120 or 150")
    else
      None
  }
}
