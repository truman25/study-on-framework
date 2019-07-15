package com.thread.demo;

import com.thread.demo.actor.ActorSystemTools;
import com.thread.demo.actor.AngryFoalActor;
import com.thread.demo.actor.LazyFoalActor;

import akka.actor.ActorRef;

/**
 * Actor模式是一种并发模型，与另一种模型共享内存完全相反，Actor模型share nothing。
 * 所有的线程(或进程)通过消息传递的方式进行合作，这些线程(或进程)称为Actor。共享内存更适合单机多核的并发编程，而且共享带来的问题很多，编程也困难。
 * 随着多核时代和分布式系统的到来，共享模型已经不太适合并发编程，因此几十年前就已经出现的Actor模型又重新受到了人们的重视。
 * MapReduce就是一种典型的Actor模式，而在语言级对Actor支持的编程语言Erlang又重新火了起来，Scala也提供了Actor，但是并不是在语言层面支持，
 * Java也有第三方的Actor包，Go语言channel机制也是一种类Actor模型。
 * @author jaydelano
 *
 */
public class ActorTest {

	public static void main(String[] args) {
		ActorSystemTools.start();
		ActorRef angryFoal = ActorSystemTools.actorOf(AngryFoalActor.class);
		ActorRef lazyFoal = ActorSystemTools.actorOf(LazyFoalActor.class);
		angryFoal.tell("hello! I am  LazyFoalActor!", lazyFoal);
	}
}
