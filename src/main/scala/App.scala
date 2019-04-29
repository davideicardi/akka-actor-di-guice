import actorsInjection.ActorDep
import com.google.inject.Inject

class App @Inject() (printer: ActorDep[PrinterActor]) {

  def start(): Unit = {

    printer.instance ! PrinterActor.PrintDocumentMsg("job1", collection.immutable.Seq("A", "B", "C"))
    printer.instance ! PrinterActor.PrintDocumentMsg("job2", collection.immutable.Seq("1", "2", "3"))

  }

}
