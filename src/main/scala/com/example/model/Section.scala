package com.example.model

import com.example.util.CustomEnum

sealed trait Section

object Section extends CustomEnum[Section] {

  case object A extends Section

  case object B extends Section

  case object C extends Section

  def values: List[Section] = List(A, B, C)
}
