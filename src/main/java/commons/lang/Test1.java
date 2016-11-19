package commons.lang;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConfigurationFactory.FileConfigurationFactory;
import org.apache.commons.configuration.ConfigurationFactory.PropertiesConfigurationFactory;
import org.apache.commons.configuration.FileSystem;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration.PropertiesReader;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;

public class Test1 {

	@Test
	public void test1(){
		int i = StringUtils.indexOf("abcdefg", "d");// 索引位子，从0开始
		System.out.println(i);
		String str = StringUtils.capitalize("abcdefghijk");// 第一个字母变大
		System.out.println(str);
		
		i = CharUtils.toIntValue('3');
		System.out.println(i);
		
		str = ClassUtils.getPackageName(this.getClass().getCanonicalName());
		System.out.println(str);
		
		System.out.println(SystemUtils.JAVA_CLASS_PATH);
		System.out.println(SystemUtils.USER_DIR);
		System.out.println(SystemUtils.IS_OS_WINDOWS_7);
		System.out.println(SystemUtils.getJavaHome().getAbsolutePath());
		
		str = RandomStringUtils.randomAscii(2);
		System.out.println(str);
		FileSystem fileSystem = FileSystem.getDefaultFileSystem();
		String bsePathe = fileSystem.getBasePath("SystemGlobals.properties");
		System.out.println(bsePathe);
		
		File file  = new File("src\\main\\java\\commons\\lang\\SystemGlobals.properties");
		try {
			PropertiesConfiguration pf = new PropertiesConfiguration(file);
			PropertiesReader reader = new PropertiesReader(new FileReader(file));
			List<String> strs = reader.getCommentLines();
			for (String string : strs) {
				System.out.println(string);
			}
//			pf.load(file);
			System.out.println(pf.getProperty("DeFilePath").toString());
		} catch (ConfigurationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
}
