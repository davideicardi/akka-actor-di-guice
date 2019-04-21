
object Program {
  def main(args: Array[String]): Unit = {
    AppModule.injector.getInstance(classOf[App])
      .start()
  }
}
