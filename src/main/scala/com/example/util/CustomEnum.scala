package com.example.util

trait CustomEnum[T] {

  def values: Seq[T]

  final def fromString(input: String): Option[T] = values
    .find(_.toString.toLowerCase == input.toLowerCase)
}
