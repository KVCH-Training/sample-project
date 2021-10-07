package com.example.model

final case class Student(name: String,
                         rollNumber: Int,
                         branch: Branch,
                         section: Section,
                         mobile: Option[String] = None)
