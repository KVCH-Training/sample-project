package com.example.model

import spray.json.DefaultJsonProtocol._
import spray.json.{DeserializationException, JsString, JsValue, RootJsonFormat}

object JsonFormat {

  implicit val branchJsonFormat: RootJsonFormat[Branch] = new RootJsonFormat[Branch] {
    override def write(obj: Branch): JsValue = JsString(obj.toString)

    override def read(json: JsValue): Branch = json match {
      case input: JsString =>
        Branch.fromString(input.value) match {
          case Some(branch) => branch
          case None => throw DeserializationException(s"Deserialization Exception: $json")
        }
      case _ => throw DeserializationException(s"Deserialization Exception: $json")
    }
  }

  implicit val sectionJsonFormat: RootJsonFormat[Section] = new RootJsonFormat[Section] {
    override def write(obj: Section): JsValue = JsString(obj.toString)

    override def read(json: JsValue): Section = json match {
      case input: JsString =>
        Section.fromString(input.value) match {
          case Some(section) => section
          case None => throw DeserializationException(s"Deserialization Exception: $json")
        }
      case _ => throw DeserializationException(s"Deserialization Exception: $json")
    }
  }

  implicit val studentJsonFormat: RootJsonFormat[Student] = jsonFormat5(Student)
}
