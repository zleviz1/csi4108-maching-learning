package csi4017assignment1f;

public class document {
	public float length;
	public String content[];
	public String id;
	public int wlength;
	//public node dnode[];
	public document(String content[],String id,int length){
		this.content=content;
		this.id=id;
		this.wlength=length;
		//this.dnode=null;
	}
	
}
