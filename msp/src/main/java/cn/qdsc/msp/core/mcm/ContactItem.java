package cn.qdsc.msp.core.mcm;

import java.util.ArrayList;
import java.util.List;

// 一条联系人内容
public class ContactItem implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 姓名
	public String displayName;
	// 电话号码:<类型、号码>
	public List<MPair> phones;
	// 邮箱<类型、号码>
	public List<MPair> emails;

	public ContactItem() {
		phones = new ArrayList<MPair>();
		emails = new ArrayList<MPair>();
	}
	
	public static class MPair implements java.io.Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public String type;
		public String content;
		
		public MPair(String type, String content){
			this.type = type;
			this.content = content;
		}
	}
}