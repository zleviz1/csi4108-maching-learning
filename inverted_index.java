package csi4017assignment1f;

public class inverted_index {
public String ci;
public float wwlength;
public int df;
public node root;
public node tail;
public int tttf;
public float wwwt;
public float wwwnz;
public inverted_index(String ci) {
	this.ci=ci;
	this.wwlength=0;
	this.df=0;
	this.root=new node(" ",0);
	this.tail=root;
	this.tttf=0;
	this.wwwt=0;
	this.wwwnz=0;
	
}
public void addDf() {
	this.df+=1;
}
public void addNode(node n) {
	this.tail.next=n;
	this.tail=tail.next;
}
}
