package come.mavenDemo;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Logger logger = Logger.getLogger(App.class);
        logger.debug("开始");
        System.out.println( "Hello World!" );
        logger.debug("结束");
    }
    
    @Test
    private void test() {
    	
	}
}
