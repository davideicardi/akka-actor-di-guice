import com.google.inject.Guice

object Program {
  def main(args: Array[String]): Unit = {
    val injector = Guice.createInjector(new AppModule)

    injector.getInstance(classOf[App])
      .start()
  }
}
