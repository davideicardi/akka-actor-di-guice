
trait PrinterService {
  def print(msg: String): Unit
}

class PrinterServiceImpl extends PrinterService {
  def print(msg: String): Unit = {
    println(s"[PrinterService] $msg")
  }
}
