package com.example.console

import com.example.util.CustomEnum

sealed trait Operation

object Operation extends CustomEnum[Operation] {

  // add {"branch":"CSE","mobile":"Hello","name":"Amit","rollNumber":1,"section":"A"}
  case object Add extends Operation

  // get 1
  case object Get extends Operation

  // update 1 {}
  case object UpdateName extends Operation

  case object UpdateMobile extends Operation

  // delete 1
  case object Delete extends Operation

  def values: List[Operation] = List(Add, Get, UpdateName, UpdateMobile, Delete)
}
