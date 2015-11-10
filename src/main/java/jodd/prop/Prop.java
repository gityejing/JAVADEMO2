package jodd.prop;

import java.io.File;
import java.util.Iterator;

import jodd.props.Props;
import jodd.props.PropsEntries;
import jodd.props.PropsEntry;

public class Prop {
	public static void main(String[] args) {
		Props props = new Props();
		File file = new File("E:\\Workspaces\\MyEclipse2013\\javaTest\\src\\jodd\\SystemGlobals.properties");
		try {
			props.load(file);
			PropsEntries ens = props.entries();
			Iterator<PropsEntry> iterator = ens.iterator();
			while (iterator.hasNext()) {
				PropsEntry propsEntry = iterator.next();
				System.out.println(propsEntry.getKey());
				System.out.println(propsEntry.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
