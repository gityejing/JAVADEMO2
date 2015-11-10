package pinyin4j;

import org.junit.Test;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinYinDemo {

	public static void main(String[] args) {
		 Hanyu hanyu = new Hanyu();
         // 中英文混合的一段文字
         String str = "荆溪白石出，Hello 天寒红叶稀。Android 山路元无雨，What's up? 空翠湿人衣。";
         String strPinyin = hanyu.getStringPinYin(str);
         System.out.println(strPinyin);
	}
	
	@Test
	public void test(){
		String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray('单');
		for (int i = 0; i < pinyinArray.length; ++i) {
			System.out.println(pinyinArray[i]);
		}
	}
	
	@Test
	public void test1() {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
		format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);

		String[] pinyinArray = null;
		try {
			pinyinArray = PinyinHelper.toHanyuPinyinStringArray('黄', format);
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		for (int i = 0; i < pinyinArray.length; ++i) {
			System.out.println(pinyinArray[i]); // huáng
		}
	}

}
