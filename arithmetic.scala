// exp ::= 0 | 1 | exp + exp | exp * exp | -exp

// Abstract Syntax Tree - AST
sealed trait Exp
case object Zero extends Exp  // 0
case object One extends Exp   // 1
case class Plus(e1: Exp, e2: Exp) extends Exp // e1 + e2
case class Mult(e1: Exp, e2: Exp) extends Exp // e1 * e2
case class UnaryMinus(e: Exp) extends Exp // -e

object Arithmetic {
  def genExp: Iterator[Exp] = {
    Iterator(Zero, One) ++
    (for {
      e1 <- genExp
      e2 <- genExp
    } yield Plus(e1, e2)) ++
    (for {
      e1 <- genExp
      e2 <- genExp
    } yield Mult(e1, e2)) ++
    (for {
      e <- genExp
    } yield UnaryMinus(e))
  }

  def genExpBounded(maxDepth: Int): Iterator[Exp] = {
    val baseCases: Iterator[Exp] = Iterator(Zero, One)
    if (maxDepth <= 0) {
      baseCases
    } else {
      val newMaxDepth = maxDepth - 1
      baseCases ++ 
      (for {
        e1 <- genExpBounded(newMaxDepth)
        e2 <- genExpBounded(newMaxDepth)
      } yield Plus(e1, e2)) ++
      (for {
        e1 <- genExpBounded(newMaxDepth)
        e2 <- genExpBounded(newMaxDepth)
      } yield Mult(e1, e2)) ++
      (for {
        e <- genExpBounded(newMaxDepth)
      } yield UnaryMinus(e))
    }
  }
}
