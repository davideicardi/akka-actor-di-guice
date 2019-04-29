package it.agilelab.akka.guice

import java.util.concurrent.atomic.AtomicReference

import com.google.inject.Injector

// NOTE: This ugly trick is required to get Injector instance inside GuiceActorProducer
//  and to have Props with just serializable parameters
object InjectorInstance {
  private val instance = new AtomicReference[Option[Injector]](None)

  def set(injector: Injector): Unit = {
    instance.get() match {
      case None => instance.set(Some(injector))
      case _ => throw new UnsupportedOperationException("Instance already set")
    }
  }

  def get: Injector = {
    instance.get() match {
      case None => throw new UnsupportedOperationException("Instance not yet created")
      case Some(ctx) => ctx
    }
  }
}

