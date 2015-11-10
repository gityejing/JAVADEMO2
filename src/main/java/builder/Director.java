package builder;

import java.util.HashMap;
import java.util.Map;

public class Director {
	private Builder builder = new ConcreteBuilder();  
	  
    Object getProductOfBus() {  
        Map attributeMap = new HashMap();  
        attributeMap.put("name", "bus");  
        builder.setProduct(Bus.class,attributeMap);  
        Object obj = builder.getProduct();  
        return obj;  
    }  
    Object getProductOfCar() {  
        Map attributeMap = new HashMap();  
        attributeMap.put("name", "car"); 
        attributeMap.put("type", "type");
        builder.setProduct(Car.class,attributeMap);  
        Object obj = builder.getProduct();  
        return obj;  
    }  
}
