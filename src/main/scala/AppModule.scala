import akka.actor.{ActorContext, ActorSystem, Props}
import com.google.inject.{AbstractModule, Provides, Singleton}

class AppModule extends AbstractModule {
  override def configure(): Unit = {

    bind(classOf[ActorSystem]).toInstance(ActorSystem.create())

    bind(classOf[PrinterService]).to(classOf[PrinterServiceImpl]).in(classOf[Singleton])
  }

  @Provides @Singleton
  def providePrinterActor(actorSystem: ActorSystem, jobFactory: PrintJobActor.Factory): PrinterActor.Ref = {
    () => actorSystem.actorOf(Props(new PrinterActor(jobFactory)))
  }

  @Provides @Singleton
  def providePrinterJobFactory(printerService: PrinterService): PrintJobActor.Factory = {
    context: ActorContext => context.actorOf(Props(new PrintJobActor(printerService)))
  }

}
