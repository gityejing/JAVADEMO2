package builder;

import java.util.Map;

public abstract class Builder {
	// 设置产品
	abstract void setProduct(Class clazz, Map<String, Object> setMap);

	// 返回产品
	abstract Object getProduct();
}
