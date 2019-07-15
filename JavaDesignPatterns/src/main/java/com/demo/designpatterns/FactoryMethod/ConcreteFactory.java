package com.demo.designpatterns.FactoryMethod;

public class ConcreteFactory extends Factory2 {

	/**
	 * 工厂方式另一种写法：可以通过反射的方式
	 * 优点：新增产品时，无需再新建一个工厂实现类，只需要一个通用的工厂实现类即可
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Product> T createProduct(Class<T> clz) {
		Product  product = null;
        try {
            product= (Product) Class.forName(clz.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (T) product;
	}

}
