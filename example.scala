// additive monad - you have these four operations
//
// def unit[A](a: A): M[A]
// def bind[A, B](on: M[A], f: A => M[B]): M[B]  ; and
// def mplus[A](a: M[A], b: M[A]): M[A]  ; or
// def mzero[A](): M[A]  ; fail

class NaturalNumbers(private var currentNumber: Int) extends Iterator[Int] {
  def hasNext: Boolean = true
  def next(): Int = {
    val retval = currentNumber
    currentNumber += 1
    retval
  }
}

// trait Iterator[A] {
//   // and
//   def flatMap[B](f: A => Iterator[B]): Iterator[B]
// }

// Prolog: nondeterministic execution: multiple answers from the same problem
// 0 - n answers
// integer(x) && x > 9 && x < 20
//
// Deterministic execution: 0 - 1 answers for a problem (might crash)
// 3 + 7: 10
//
// Iterator[Int]: deterministically is an Iterator[Int]
//                nondeterministically is an Int

object Example {
  // nat ::= 0 | succ(nat)
  def nat: Iterator[Int] = {
    Iterator(0) ++ nat.flatMap(n => Iterator(n + 1))
  }

  // generates everything between min and max
  def between(min: Int, max: Int): Iterator[Int] = {
    if (min > max) {
      Iterator()
    } else {
      Iterator(min) ++ between(min + 1, max)
    }
  }

  def pairNats: Iterator[(Int, Int)] = {
    between(1, 3).iterator.flatMap(n1 =>
      between(5, 7).iterator.flatMap(n2 =>
        Iterator((n1, n2))))
    // for {
    //   n1 <- List(1, 2, 3).iterator
    //   n2 <- List(4, 5, 6).iterator
    // } yield (n1, n2)
  }
}
