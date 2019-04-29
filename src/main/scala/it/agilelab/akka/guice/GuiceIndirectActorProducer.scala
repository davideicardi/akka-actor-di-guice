package it.agilelab.akka.guice

import akka.actor.{Actor, IndirectActorProducer}

class GuiceIndirectActorProducer(ac: Class[_ <: Actor]) extends IndirectActorProducer {
  override def produce: Actor = InjectorInstance.get.getInstance(ac)
  override def actorClass: Class[_ <: Actor] = ac
}
