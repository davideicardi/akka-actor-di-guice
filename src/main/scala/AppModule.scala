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

  // Provide a reference to printer actor
  @Provides @Singleton
  def providePrinterActor(actorSystem: ActorSystem): PrinterActor.Ref = {
    val instance = actorSystem.actorOf(Props(classOf[GuiceActorProducer], classOf[PrinterActor]))

    () => instance
  }

  // Provide a factory of print job actors
  @Provides @Singleton
  def providePrinterJobFactory(): PrintJobActor.Factory = {
    context: ActorContext => context.actorOf(Props(classOf[GuiceActorProducer], classOf[PrintJobActor]))
  }
}

class GuiceActorProducer(ac: Class[_ <: Actor]) extends IndirectActorProducer {
  override def produce: Actor = AppModule.injector.getInstance(ac)
  override def actorClass: Class[_ <: Actor] = ac
}