package it.agilelab.akka.guice

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

import scala.reflect.ClassTag

class GuiceActorDep[T <: Actor](actorSystem: ActorSystem)(implicit actorClass: ClassTag[T]) extends ActorDep[T] {
  override val instance: ActorRef = actorSystem.actorOf(
    Props(classOf[GuiceIndirectActorProducer], actorClass.runtimeClass),
    actorClass.runtimeClass.getSimpleName
  )
}
