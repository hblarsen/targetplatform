package models.validation


object Validator
{
	sealed trait InputType
	case class IntType() extends InputType
	case class BoolType() extends InputType
	case class EnumType(vals:List[String]) extends InputType

	def features :List[(String, String, InputType)] = 
		List(
			("OS", "Choose an operating system", EnumType(List("Windows", "OSX", "Ubuntu", "Mint"))),
			("CPU", "Choose a cpu", EnumType(List("AMD", "Intel"))),
			("HD", "Choose a harddrive", EnumType(List("HDD", "SSD"))),
			("HDSize", "Choose the amount of memory", IntType()),
			("HDMI", "Do you want an hdmi port?", BoolType())
		)

	def hi = "Hi"

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
		if(m("HD")=="SSD" && !(m("HDSize")=="120" || m("HDSize")=="120"))
			Some("SDD storage units can only have sizes 120 or 240")
		else if(m("HDMI")=="true" && !(m("CPU")=="AMD" && (m("OS")=="OSX" || m("OS")=="Ubuntu")))
			Some("hdmi ports are only available on OSX and Ubuntu, with AMD chips")
		else if(m("OS")=="OSX" && !(!(m("CPU")=="AMD")))
			Some("AMD chips are not supported on OSX")
		else
			None
	}
}