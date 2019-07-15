package com.thread.demo.actor;

import akka.actor.UntypedActor;

public class AngryFoalActor extends UntypedActor {

	@Override
	public void onReceive(Object arg0) throws Exception {
		System.out.println("AngryFoalActor receive message : " + arg0);
		getSender().tell("hello! I am  AngryFoalActor!", getSelf());
	}

}
