import PrinterActor.{JobCompletedMsg, PrintDocumentMsg}
import akka.actor._
import com.google.inject.Inject
import it.agilelab.akka.guice.ActorFactory

object PrinterActor {
  case class PrintDocumentMsg(jobName: String, lines: collection.immutable.Seq[String])
  case class JobCompletedMsg(job: ActorRef)
}

class PrinterActor @Inject() (jobFactory: ActorFactory[PrintJobActor]) extends Actor with Stash {
  def receive: Receive = idle

  def idle: Receive = {
    case document: PrintDocumentMsg =>
      context.become(processingDocument)

      val job = jobFactory.create(context, document.jobName)
      document.lines.foreach { line =>
        job ! line
      }

      self ! JobCompletedMsg(job)
  }
  def processingDocument: Receive = {
    case JobCompletedMsg(job) =>
      job ! PrintJobActor.EndJobMsg
      context.become(idle)
      unstashAll()
    case _ => stash()
  }
}
