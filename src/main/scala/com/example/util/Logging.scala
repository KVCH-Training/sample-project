package com.example.util

import org.slf4j.{Logger, LoggerFactory}

trait Logging {

  lazy val log: Logger = LoggerFactory.getLogger(getClass)
}
