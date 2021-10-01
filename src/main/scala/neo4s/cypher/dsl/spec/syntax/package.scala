package neo4s.cypherDSL.spec

import neo4s.cypherDSL.spec.entities.{NodeType, RelationType}

import scala.reflect.runtime.universe.{weakTypeOf, WeakTypeTag}

package object syntax {
  val patterns: Patterns.type           = Patterns
  def any[T <: Product: WeakTypeTag]    = NodeType(weakTypeOf[T])
  def anyRel[T <: Product: WeakTypeTag] = RelationType(weakTypeOf[T])
  case object anyLength
}