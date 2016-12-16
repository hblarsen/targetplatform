package models.validation

//dummy configuration class - persistence layer goes here
case class UserConfiguration( currentConfig: Map[String,String] ){

  write(currentConfig)

  def read(): Map[String,String] = {
    println("config loaded from file")
    currentConfig
  }

  def write(c: Map[String,String]) = {
    println("config saved to file")
  }
}
