import com.google.inject.Inject

class App @Inject() (printer: PrinterActor.Ref) {

  def start(): Unit = {

    printer.get ! PrinterActor.PrintDocumentMsg(collection.immutable.Seq("A", "B", "C"))
    printer.get ! PrinterActor.PrintDocumentMsg(collection.immutable.Seq("1", "2", "3"))

  }

}
