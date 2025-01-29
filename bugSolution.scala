```scala
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

class MyClass {
  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.global

  def myMethod(value: Int): Future[Int] = Future {
    if (value == 0) throw new Exception("Value cannot be zero")
    value * 2
  }.recover {
    case e: Exception => 
      println(s"Recovered from exception: ${e.getMessage}")
      0 // Return a default value or handle the error appropriately
  }
}

val myClass = new MyClass()

myClass.myMethod(0).onComplete {
  case Success(result) => println(s"Success: $result")
  case Failure(exception) => println(s"Failure: ${exception.getMessage}")
}

//This prints out the recovered exception in addition to the failure
```
