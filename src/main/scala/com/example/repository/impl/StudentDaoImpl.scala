package com.example.repository.impl

import scala.concurrent.Future

import com.example.AppConfig.Slick.profile.api._
import com.example.model.{Branch, Section, Student}
import com.example.repository.StudentDao
import slick.lifted.ProvenShape

object StudentDaoImpl {

  val TABLE_NAME = "Student"

  final class Schema(tag: Tag) extends Table[Student](tag, TABLE_NAME) {

    def rollNumber: Rep[Int] = column[Int]("rollNumber", O.PrimaryKey)

    def name: Rep[String] = column[String]("name")

    def mobile: Rep[Option[String]] = column[Option[String]]("mobile")

    def branch: Rep[String] = column[String]("branch")

    def section: Rep[String] = column[String]("section")

    override def * : ProvenShape[Student] = (rollNumber, name, mobile, branch, section) <>[Student] ( {
      case (rNumber, nm2, mob, branch, section) =>
        Student(nm2, rNumber, Branch.fromString(branch).head, Section.fromString(section).head, mob)
    }, {
      case Student(name, rollNumber, branch, section, mobile) =>
        Some((rollNumber, name, mobile, branch.toString, section.toString))
    })
  }

  val studentQuery: TableQuery[Schema] = TableQuery(new Schema(_))
}

final class StudentDaoImpl extends StudentDao {

  import StudentDaoImpl._
  import com.example.AppConfig.Slick.db

  override def addStudent(student: Student): Future[Int] = db.run(studentQuery += student)

  override def getStudent(rollNumber: Int): Future[Option[Student]] =
    db.run(studentQuery.filter(_.rollNumber === rollNumber).result.headOption)

  override def updateMobile(rollNumber: Int, newMobileNumber: String): Future[Int] =
    db.run(studentQuery.filter(_.rollNumber === rollNumber).map(_.mobile).update(Some(newMobileNumber)))

  override def updateName(rollNumber: Int, newName: String): Future[Int] =
  db.run(studentQuery.filter(_.rollNumber === rollNumber).map(_.name).update(newName))

  override def removeStudent(rollNumber: Int): Future[Int] =
    db.run(studentQuery.filter(_.rollNumber === rollNumber).delete)
}
