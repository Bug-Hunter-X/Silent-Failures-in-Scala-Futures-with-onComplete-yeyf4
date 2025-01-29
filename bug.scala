```scala
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

class MyClass {
  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.global

  def myMethod(value: Int): Future[Int] = Future {
    if (value == 0) throw new Exception("Value cannot be zero")
    value * 2
  }
}

val myClass = new MyClass()

myClass.myMethod(0).onComplete {
  case Success(result) => println(s"Success: $result")
  case Failure(exception) => println(s"Failure: ${exception.getMessage}")
}

// This will not execute the println after failure because the onComplete only handles the future
// it is not possible to use recoverWith or recover in onComplete
```