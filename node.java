package csi4017assignment1f;

public class node {
public String document_id;
public int tf;
public float nz;
public float weight;
public node next;
public float score;
public int lenggth;
public boolean has_added;
public node(String id,int l) {
this.document_id=id;
this.lenggth=l;
this.tf=0;
this.nz=0;
this.weight=0;
next=null;
score=0;
has_added=false;
}
public void addTF() {
	this.tf+=1;
}

}
