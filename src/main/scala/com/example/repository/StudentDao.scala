package com.example.repository

import scala.concurrent.Future

import com.example.model.Student

trait StudentDao {

  def addStudent(student: Student): Future[Int]

  def getStudent(rollNumber: Int): Future[Option[Student]]

  def updateMobile(rollNumber: Int, newMobileNumber: String): Future[Int]

  def updateName(rollNumber: Int, newName: String): Future[Int]

  def removeStudent(rollNumber: Int): Future[Int]
}
