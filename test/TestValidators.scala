import models.validation.CarValidator
import org.specs2.mutable.Specification

class TestValidators extends Specification {

  "Car Validation" should {

    "ensure that RimsColor can only be MatteBlack if Rims is Aluminum and EnginePower is 120 or 150" in {

      CarValidator.validate(
        Map("Fuel" -> "",
          "Rims" -> "Aluminum",
          "RimsColor" -> "MatteBlack",
          "EnginePower" -> "120",
          "HeatedSeats" -> "",
          "ElectricWindows" -> "")) must beTrue

      CarValidator.validate(
        Map("Fuel" -> "",
          "Rims" -> "Aluminum",
          "RimsColor" -> "MatteBlack",
          "EnginePower" -> "240",
          "HeatedSeats" -> "",
          "ElectricWindows" -> "")) must beFalse
    }

    "ensure that Heated seats are only possible on vehicle with 150 HP Diesel engine" in {

      CarValidator.validate(
        Map("Fuel" -> "Diesel",
          "Rims" -> "",
          "RimsColor" -> "",
          "EnginePower" -> "150",
          "HeatedSeats" -> "true",
          "ElectricWindows" -> "")) must beTrue

      CarValidator.validate(
        Map("Fuel" -> "Electric",
          "Rims" -> "",
          "RimsColor" -> "",
          "EnginePower" -> "150",
          "HeatedSeats" -> "true",
          "ElectricWindows" -> "")) must beFalse
    }

    "ensure that Electric windows is only possible on a vehicle with Gasoline Engine and Rim Type Other" in {

      CarValidator.validate(
        Map("Fuel" -> "Gasoline",
          "Rims" -> "Other",
          "RimsColor" -> "",
          "EnginePower" -> "",
          "HeatedSeats" -> "",
          "ElectricWindows" -> "true")) must beTrue

      CarValidator.validate(
        Map("Fuel" -> "Diesel",
          "Rims" -> "",
          "RimsColor" -> "",
          "EnginePower" -> "150",
          "HeatedSeats" -> "",
          "ElectricWindows" -> "true")) must beFalse
    }
  }
}




