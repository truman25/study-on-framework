package com.demo.designpatterns.FactoryMethod;

/**
 * 工厂方法模式
 * 
 * 工厂类、产品类，可以用抽象类也可以用接口
 * 
 * @author jaydelano
 *
 */
public class FactoryMethodPattern {
	public static void main(String[] args){
		//方式一
        //客户要产品A
        FactoryA mFactoryA = new FactoryA();
        mFactoryA.Manufacture().Show();
 
        //客户要产品B
        FactoryB mFactoryB = new FactoryB();
        mFactoryB.Manufacture().Show();
        
        //方式二
        //工厂方式另一种写法：可以通过反射的方式
        //优点：新增产品时，无需再新建一个工厂实现类，只需要一个通用的工厂实现类即可
        Factory2 factory2 = new ConcreteFactory();
        Product product = factory2.createProduct(ProductA.class);
        product.Show();
    }

}
