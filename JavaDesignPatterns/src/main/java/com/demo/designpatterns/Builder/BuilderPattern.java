package com.demo.designpatterns.Builder;

/**
 * ������ģʽ������������ģʽ
 * 
 * �ŵ㣺
 *  �ͻ��˲���֪����Ʒ�ڲ���ɵ�ϸ�ڣ�����Ʒ�������Ʒ�Ĵ������̽��ʹ����ͬ�Ĵ������̿��Դ�����ͬ�Ĳ�Ʒ����
 *  �û�ʹ�ò�ͬ�ľ��彨���߼��ɵõ���ͬ�Ĳ�Ʒ���� 
 *  ���Ը��Ӿ�ϸ�ؿ��Ʋ�Ʒ�Ĵ�������
 *  �����µľ��彨���������޸�ԭ�����Ĵ���
 * @author jaydelano
 *
 */
public class BuilderPattern {
	
	public static void main(String[] args) {
        Director director = new Director();//����ָ����
        Builder builder = new ConcreteBuilder();//�������彨����
        director.Construct(builder);//ָ�����彨����
        Computer computer = builder.getComputer();//��ȡ���彨����
        computer.print();//���þ��彨���ߵķ���
    }

}
