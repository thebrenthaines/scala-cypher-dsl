package me.manishkatoch.scala.cypherDSL.spec.entities
import me.manishkatoch.scala.cypherDSL.spec.Context

private[cypherDSL] trait CypherEntity {
  def toQuery(context: Context = new Context()): String
}