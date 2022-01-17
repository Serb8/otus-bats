package me.chuwy.otusbats

import me.chuwy.otusbats.Monad.MonadOps
import me.chuwy.otusbats.Show.ShowOps

import scala.language.postfixOps

object App {

    def main(args: Array[String]): Unit = {

      val listOpt = List(Some(10), Some(1))
      val listStr = List("a", "v")

//      val x = listOpt show
      val y =  listStr show

      println(Show[Int].show(2))
      println(true show)
      println(1 mkString_(List(1, 2, 3), ",", "[", "]"))


      println(Monad[Option].flatMap(Some(1))(o => Some(o + 1)))

      println(Option(123).mapOp(l => l.toString + " as string").get)

    }
}
