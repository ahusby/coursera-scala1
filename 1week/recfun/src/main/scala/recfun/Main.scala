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

    def bbb(solutions: Set[List[Int]], attempt: List[Int], coins: List[Int]): Set[List[Int]] = {
      if (coins.isEmpty) solutions // Her glemmer vi å sjekke det nye attemptet
      else if (attempt.sum > money)
        bbb(solutions, List(coins.head), coins.tail)
      else if (attempt.sum == money)
        bbb(solutions + attempt, List(coins.head), coins) ++ bbb(solutions + attempt, List(coins.head), coins.tail)
      else
        bbb(solutions, attempt :+ coins.head, coins) ++ bbb(solutions, attempt :+ coins.head, coins.tail)
    }

    bbb(Set.empty, Nil, coins).size
  }





}