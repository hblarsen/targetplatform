package models.validation

/**
  * Created by HEBL on 15-12-2016.
  */
case class UserConfiguration( currentConfig: Map[String,String] ){

  //dummy configuration class
  write(currentConfig)

  def read(): Map[String,String] = {
    println("config loaded from file")
    currentConfig
  }

  def write(c: Map[String,String]) = {
    println("config saved to file")
  }
}
