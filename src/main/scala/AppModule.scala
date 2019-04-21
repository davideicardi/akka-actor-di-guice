import akka.actor.{Actor, ActorContext, ActorSystem, IndirectActorProducer, Props}
import com.google.inject.{AbstractModule, Guice, Injector, Provides, Singleton}

object AppModule {
  // Global instance of the injector that can be used by the GuiceActorProducer
  lazy val injector: Injector = Guice.createInjector(new AppModule)
}

class AppModule extends AbstractModule {
  override def configure(): Unit = {

    // Actor system
    bind(classOf[ActorSystem]).toInstance(ActorSystem.create())

    // Other components
    bind(classOf[PrinterService]).to(classOf[PrinterServiceImpl]).in(classOf[Singleton])

    // Bind actors
    bind(classOf[PrintJobActor])
    bind(classOf[PrinterActor])
  }

  // Provide a reference to an actor
  @Provides @Singleton
  def providePrinterActor(actorSystem: ActorSystem): PrinterActor.Ref = {
    val instance = actorSystem.actorOf(Props(classOf[GuiceActorProducer[PrinterActor]]))

    () => instance
  }

  // Provide a factory of other actors
  @Provides @Singleton
  def providePrinterJobFactory(): PrintJobActor.Factory = {
    context: ActorContext => context.actorOf(Props(classOf[GuiceActorProducer[PrintJobActor]]))
  }

  class GuiceActorProducer[T <: Actor]() extends IndirectActorProducer {
    override def actorClass: Class[_ <: Actor] = classOf[T]

    // Get the injector instance from a static variable to avoid passing it to Props
    override def produce: Actor = AppModule.injector.getInstance(classOf[T])
  }
}
