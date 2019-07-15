package com.thread.demo;

import com.thread.demo.actor.ActorSystemTools;
import com.thread.demo.actor.AngryFoalActor;
import com.thread.demo.actor.LazyFoalActor;

import akka.actor.ActorRef;

/**
 * Actorģʽ��һ�ֲ���ģ�ͣ�����һ��ģ�͹����ڴ���ȫ�෴��Actorģ��share nothing��
 * ���е��߳�(�����)ͨ����Ϣ���ݵķ�ʽ���к�������Щ�߳�(�����)��ΪActor�������ڴ���ʺϵ�����˵Ĳ�����̣����ҹ������������ܶ࣬���Ҳ���ѡ�
 * ���Ŷ��ʱ���ͷֲ�ʽϵͳ�ĵ���������ģ���Ѿ���̫�ʺϲ�����̣���˼�ʮ��ǰ���Ѿ����ֵ�Actorģ���������ܵ������ǵ����ӡ�
 * MapReduce����һ�ֵ��͵�Actorģʽ���������Լ���Actor֧�ֵı������Erlang�����»���������ScalaҲ�ṩ��Actor�����ǲ����������Բ���֧�֣�
 * JavaҲ�е�������Actor����Go����channel����Ҳ��һ����Actorģ�͡�
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
