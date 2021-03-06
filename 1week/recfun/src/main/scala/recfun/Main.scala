package recfun

import scala.annotation.tailrec

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
    * Exercise 1
    */
  def pascal(c: Int, r: Int): Int = {
    (c, r) match {
      case (0, 0) => 1
      case (-1, _) => 0
      case (_, -1) => 0
      case (y, x) => pascal(y - 1, x - 1) + pascal(y, x - 1)
    }
  }

  /**
    * Exercise 2
    */
  def balance(chars: List[Char]): Boolean = {

    @tailrec
    def loop(balance: Int, rest: List[Char]): Boolean = {
      if (balance < 0) false
      else {
        rest match {
          case Nil => balance == 0;
          case '(' :: tail => loop(balance + 1, tail)
          case ')' :: tail => loop(balance - 1, tail)
          case _ :: tail => loop(balance, tail)
        }
      }
    }

    loop(0, chars)
  }

  /**
    * Exercise 3
    */
  def countChange(money: Int, coins: List[Int]): Int = {

    def solutions(attempt: List[Int], coinsRemaing: List[Int]): Set[List[Int]] = {
      val sumSoFar = attempt.sum
      if (sumSoFar > money) Set.empty
      else if (sumSoFar == money) Set(attempt)
      else if (coinsRemaing.isEmpty) Set.empty
      else {
        val head :: tail = coinsRemaing
        solutions(attempt :+ head, head :: tail) ++
          solutions(attempt :+ head, tail) ++
          solutions(attempt, tail)
      }
    }

    solutions(Nil, coins).size
  }

}