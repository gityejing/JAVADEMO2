package builder;

public class Main {
	public static void main(String[] args) {
		// 实列化导演类
		Director director = new Director();
		// 我需要得到小汽车这个产品
		Object car = director.getProductOfCar();
		car.toString();
		// 我需要得到公共汽车这个产品
		Object bus = director.getProductOfBus();
		bus.toString();
	}
}
