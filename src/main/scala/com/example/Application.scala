package com.example

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

import com.example.console.Operation
import com.example.model.JsonFormat._
import com.example.model.Student
import com.example.repository.StudentDao
import com.example.repository.impl.StudentDaoImpl
import com.example.service.StudentService
import com.example.util.Logging
import spray.json._

final class Application(studentService: StudentService)(implicit ec: ExecutionContext) extends Logging {

  def run(): Unit = Try {
    val input = scala.io.StdIn.readLine()
    val split = input.split("----")

    Operation.fromString(split.head) match {
      case Some(operation) =>
        handleOperation(operation, split.tail)
          .onComplete {
            case Success(value) => log.info(s"Successful $value")
            case Failure(exception) => log.error(exception.getMessage, exception)
          }
        run()
      case None =>
        log.info("Not a valid option")
        run()
    }
  }.recover {
    case ex: Exception =>
      log.error(ex.getMessage, ex)
      run()
  }

  def handleOperation(operation: Operation, input: Array[String]): Future[String] = operation match {
    case Operation.Add =>
      Future(input.head.parseJson.convertTo[Student])
        .flatMap(studentService.addStudent)

    case Operation.Get =>
      Future(input.head)
        .map(_.toInt)
        .flatMap(studentService.getStudent)

    case Operation.UpdateName =>
      for {
        rollNumber <- Future(input.head).map(_.toInt)
        name <- Future(input.tail.head)
        out <- studentService.updateStudent(rollNumber, None, Some(name))
      } yield out

    case Operation.UpdateMobile =>
      for {
        rollNumber <- Future(input.head).map(_.toInt)
        mobile <- Future(input.tail.head)
        out <- studentService.updateStudent(rollNumber, Some(mobile), None)
      } yield out

    case Operation.Delete =>
      Future(input.head).map(_.toInt).flatMap(studentService.removeStudent)
  }
}

object Application extends App with Logging {

  implicit val ec: ExecutionContext = ExecutionContext.global
  val dao: StudentDao = new StudentDaoImpl
  val service = StudentService(dao)
  new Application(service).run()
}
