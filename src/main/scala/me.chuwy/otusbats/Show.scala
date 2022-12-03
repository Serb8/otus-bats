package me.chuwy.otusbats


trait Show[A] {
  def show(a: A): String
}

object Show {

  // 1.1 Instances (`Int`, `String`, `Boolean`)

  implicit val showInt: Show[Int] = fromJvm[Int]

  implicit val showString: Show[String] = fromJvm[String]

  implicit val showBoolean: Show[Boolean] = fromJvm[Boolean]


  // 1.2 Instances with conditional implicit

  implicit def listShow[A](implicit ev: Show[A]): Show[List[A]] = new Show[List[A]] {
    override def show(a: List[A]): String = a.toString()
  }


  // 2. Summoner (apply)

  def apply[A](implicit ev: Show[A]): Show[A] = ev

  // 3. Syntax extensions

  implicit class ShowOps[A](a: A) {
    def show(implicit ev: Show[A]): String = a.toString()

    /** Transform list of `A` into `String` with custom separator, beginning and ending.
     *  For example: "[a, b, c]" from `List("a", "b", "c")`
     *
     *  @param separator. ',' in above example
     *  @param begin. '[' in above example
     *  @param end. ']' in above example
     */
    def mkString_(list: List[A], separator: String, begin: String, end: String)(implicit ev: Show[A]): String = {
      begin + list.mkString(separator) + end
    }
  }

  // 4. Helper constructors

  /** Just use JVM `toString` implementation, available on every object */
  def fromJvm[A]: Show[A] = new Show[A] {
    def show(a: A): String = a.toString()
  }

  /** Provide a custom function to avoid `new Show { ... }` machinery */
  def fromFunction[A](f: A => String): Show[A] = new Show[A] {
    def show(a: A): String = f(a)
  }

}