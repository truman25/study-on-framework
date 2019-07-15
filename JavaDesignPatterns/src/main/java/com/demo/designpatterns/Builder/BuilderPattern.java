package com.demo.designpatterns.Builder;

/**
 * 建造者模式，又名生成器模式
 * 
 * 优点：
 *  客户端不必知道产品内部组成的细节，将产品本身与产品的创建过程解耦，使得相同的创建过程可以创建不同的产品对象。
 *  用户使用不同的具体建造者即可得到不同的产品对象 
 *  可以更加精细地控制产品的创建过程
 *  增加新的具体建造者无须修改原有类库的代码
 * @author jaydelano
 *
 */
public class BuilderPattern {
	
	public static void main(String[] args) {
        Director director = new Director();//创建指挥者
        Builder builder = new ConcreteBuilder();//创建具体建造者
        director.Construct(builder);//指定具体建造者
        Computer computer = builder.getComputer();//获取具体建造者
        computer.print();//调用具体建造者的方法
    }

}
