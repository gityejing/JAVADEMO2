package threadpool;

/**
 * ����ӿ�
 * @author Administrator
 */
public abstract class Upload {
	protected String info;
    abstract boolean uploadPic();
    public String getInfo(){
        return info;
    }
}
