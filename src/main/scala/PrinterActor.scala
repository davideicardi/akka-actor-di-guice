import PrinterActor.PrintDocumentMsg
import akka.actor.{Actor, ActorRef}
import com.google.inject.Inject

object PrinterActor {
  case class PrintDocumentMsg(lines: collection.immutable.Seq[String])

  trait Ref {
    def get(): ActorRef
  }
}

class PrinterActor @Inject() (jobFactory: PrintJobActor.Factory) extends Actor {
  def receive: Receive = {
    case document: PrintDocumentMsg =>
      val job = jobFactory.create(context)
      document.lines.foreach { line =>
        job ! line
      }

      job ! PrintJobActor.EndJobMsg
  }
}
