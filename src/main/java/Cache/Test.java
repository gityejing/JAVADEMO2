package Cache;

import org.apache.log4j.Logger;

//测试类，  
class Test {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Test.class);

	public static void main(String[] args) {
		logger.info(CacheManager.getSimpleFlag("alksd"));
		CacheManager.putCache("abc", new Cache());
		CacheManager.putCache("def", new Cache());
		CacheManager.putCache("ccc", new Cache());
		CacheManager.clearOnly("");
		Cache c = new Cache();
		for (int i = 0; i < 10; i++) {
			CacheManager.putCache("" + i, c);
		}
		CacheManager.putCache("aaaaaaaa", c);
		CacheManager.putCache("abchcy;alskd", c);
		CacheManager.putCache("cccccccc", c);
		CacheManager.putCache("abcoqiwhcy", c);
		logger.info("删除前的大小：" + CacheManager.getCacheSize());
		CacheManager.getCacheAllkey();
		CacheManager.clearAll("aaaa");
		logger.info("删除后的大小：" + CacheManager.getCacheSize());
		CacheManager.getCacheAllkey();

	}
}
