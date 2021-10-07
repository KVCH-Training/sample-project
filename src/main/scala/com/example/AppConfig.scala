package com.example

import com.typesafe.config.{Config, ConfigFactory}
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

object AppConfig {

  lazy val config: Config = ConfigFactory.load().getConfig("sample-project")

  object Slick {

    lazy val dbConfig: DatabaseConfig[JdbcProfile] =
      DatabaseConfig.forConfig("slick", config)

    lazy val profile: JdbcProfile = dbConfig.profile

    lazy val db = dbConfig.db
  }
}
