package com.example.service

import java.sql.SQLIntegrityConstraintViolationException

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

import com.example.model.JsonFormat._
import com.example.model.Student
import com.example.repository.StudentDao
import spray.json._

object StudentService {

  def apply(studentDao: StudentDao)(implicit ec: ExecutionContext): StudentService =
    new StudentService(studentDao)
}

final class StudentService private(studentDao: StudentDao)(implicit ec: ExecutionContext) {

  def addStudent(student: Student): Future[String] =
    studentDao.addStudent(student).transform {
      case Success(1) => Success("User Inserted Successfully.")
      case Success(x) => Success(s"Something bad happened for student $Student with return value: $x.")
      case Failure(_: SQLIntegrityConstraintViolationException) => Success(s"Student already exists: $student.")
      case Failure(exception) => Failure(new Exception(s"Exception inserting student $student", exception))
    }

  def getStudent(rollNumber: Int): Future[String] =
    studentDao.getStudent(rollNumber).transform {
    case Success(Some(student)) => Success(student.toJson.compactPrint)
    case Success(None) => Success(s"No student with rollNumber $rollNumber is found.")
    case Failure(exception) =>
      Failure(new Exception(s"Exception fetching student with rollNumber $rollNumber", exception))
  }

  def updateStudent(rollNumber: Int, mobile: Option[String], name: Option[String]): Future[String] = {
    val output = if(name.isDefined) {
      studentDao.updateName(rollNumber, name.get).transform {
        case Success(1) => Success("Updated")
        case Success(x) => Success(s"Something bad happened for rollNumber $rollNumber with return value: $x.")
        case Failure(exception) =>
          Failure(new Exception(s"Exception updating student name with rollNumber $rollNumber", exception))
      }
    } else Future.successful("Nothing to Update")

    val output1 = if (mobile.isDefined) {
      studentDao.updateMobile(rollNumber, mobile.get).transform {
        case Success(1) => Success("Updated")
        case Success(x) => Success(s"Something bad happened for mobile $mobile with return value: $x.")
        case Failure(exception) =>
          Failure(new Exception(s"Exception updating student name with rollNumber $rollNumber", exception))
      }
    } else Future.successful("Nothing to Update")

    for {
      out1 <- output
      out2 <- output1
    } yield s"Name:- $out1, Mobile: $out2"
  }

  def removeStudent(rollNumber: Int): Future[String] = studentDao.removeStudent(rollNumber).transform {
    case Success(1) => Success("Deleted")
    case Success(x) => Success(s"Something bad happened for rollNumber $rollNumber with return value: $x.")
    case Failure(exception) =>
      Failure(new Exception(s"Exception removing student name with rollNumber $rollNumber", exception))
  }
}
