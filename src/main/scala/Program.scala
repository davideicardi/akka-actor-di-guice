import com.google.inject.Guice
import it.agilelab.akka.guice.InjectorInstance

object Program {
  def main(args: Array[String]): Unit = {
    val injector = Guice.createInjector(new AppModule)
    InjectorInstance.set(injector)

    injector.getInstance(classOf[App])
      .start()
  }
}
