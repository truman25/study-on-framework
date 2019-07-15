package com.demo.designpatterns.Decorator;

/**
 * װ����
 * @author jaydelano
 *
 */
public abstract class Decorator implements Component{

	private Component mComponent;
    public void decoratorObj(Component component){
        mComponent = component;
    }

    @Override
    public void show() {
        if(mComponent != null){
            mComponent.show();
        }
    }
}
