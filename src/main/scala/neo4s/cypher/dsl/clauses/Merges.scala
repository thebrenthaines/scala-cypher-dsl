package neo4s.cypher.dsl.clauses

import neo4s.cypher.dsl.entities.{Node, NodeType}
import neo4s.cypher.dsl.{Context, DSLResult, Path, PathLink, QueryProvider}
import shapeless.{HList, HNil}
import shapeless.ops.hlist.ToTraversable

private[dsl] class Merges(path: Path) extends Clause {
  override def toQuery(context: Context = new Context()): DSLResult = {
    val result = path.toQuery(context)
    result.copy(query = s"MERGE ${result.query}")
  }
}

private[dsl] object Merges {
  def apply[T <: Product, TH <: HList](element: Node[T, TH])(
    implicit i0: ToTraversable.Aux[TH, List, Symbol]): Merges = {
    val path = new Path(PathLink(None, element, None))
    new Merges(path)
  }
  def apply(element: NodeType): Merges = {
    val path = new Path(PathLink(None, element, None))
    new Merges(path)
  }

  def apply[T <: Product, TH <: HList](element: T)(implicit queryProvider: QueryProvider[T],
                                                   i0: ToTraversable.Aux[TH, List, Symbol]): Merges = {
    val path = new Path(PathLink(None, Node(element, HNil), None))
    new Merges(path)
  }

  def apply(path: Path) = new Merges(path)
}
