package com.example.model

import com.example.util.CustomEnum

sealed trait Branch {

  def fullName: String
}

object Branch extends CustomEnum[Branch] {

  case object CSE extends Branch {
    override def fullName: String = "Computer Science & Engineering"
  }

  case object ME extends Branch {
    override def fullName: String = "Mechanical Engineering"
  }

  case object CE extends Branch {
    override def fullName: String = "Civil Engineering"
  }

  def values: List[Branch] = List(CSE, ME, CE)
}
