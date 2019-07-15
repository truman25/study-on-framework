package com.thread.demo.actor;

import akka.actor.UntypedActor;

public class LazyFoalActor extends UntypedActor {

	@Override
	public void onReceive(Object arg0) throws Exception {
		System.out.println("LazyFoalActor receive message : " + arg0);
	}

}
