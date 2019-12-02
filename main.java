package csi4017assignment1f;
import java.io.*;
import java.util.*;

import ir.vsr.*;
import ir.utilities.*;
//student name:Qifan Yang
//student number:7149329
//student name:Su Zhang
//student number:6809885
public class main {
	protected static Porter stemmer = new Porter();
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//======================Preparation============================
				//---------------read STOP Words file ---------------
				String fileName = "StopWords.txt";
				String line = null;
				String stopwordlist="";
				try {
					FileReader fileReader = new FileReader("/Users/bigxiaoxiao/Downloads/eclipse defult/csi4017assignment1f/src/csi4017assignment1f/"+fileName);
					BufferedReader bufferedReader = new BufferedReader(fileReader);
					while((line = bufferedReader.readLine()) != null) {
		                stopwordlist+=line;
		                stopwordlist+=" ";
					}
					 bufferedReader.close();
				}
				catch(FileNotFoundException ex) {
					System.out.println("Unable to open file '"+fileName+"'");
					}
				catch(IOException ex) {
		        System.out.println(
		            "Error reading file '" + fileName + "'");                

		    }
				//----------end reading Stop word file-----------------
				
				
				String[] stopwords = stopwordlist.split(" ");
				
				
				 fileName = "topics_MB1-49.txt";
				 String qurylist=null;
				 line = null;
				try {
					FileReader fileReader = new FileReader("/Users/bigxiaoxiao/Downloads/eclipse defult/csi4017assignment1f/src/csi4017assignment1f/"+fileName);
					BufferedReader bufferedReader = new BufferedReader(fileReader);
					while((line = bufferedReader.readLine()) != null) {
						int startPosition = line.indexOf("<title>") + "<title>".length();
						int endPosition = line.indexOf("</title>", startPosition);
						if(startPosition>0 &&endPosition >0) {
						String subS = line.substring(startPosition, endPosition);
						if(subS.length()>0) {
							qurylist+=subS;
							qurylist+=" qifansplit ";
						}
						}
					}
					 bufferedReader.close();
				}
				catch(FileNotFoundException ex) {
					System.out.println("Unable to open file '"+fileName+"'");
					}
				catch(IOException ex) {
		        System.out.println(
		            "Error reading file '" + fileName + "'");                

		    }
				//----------end reading query file-----------------
				
				String[] query_list = qurylist.split(" qifansplit ");
				String[]temp;
				String[]temp1;
				myquerylist mql[]=new myquerylist[query_list.length];
				for(int i =0;i<query_list.length;i++) {
					temp=query_list[i].split(" ");
					temp1=new String[temp.length-1];
					for(int j =0;j<temp1.length;j++) {
					temp1[j]=temp[j+1];
				}
					myquerylist tempOb=new myquerylist(temp1,i+1,temp1.length);
					mql[i]=tempOb;
				}
				
				//----------end store query -----------------
				String unclean = null;
				myquerylist tempq;
				for(int i =0;i<mql.length;i++) {
					for(int j=0;j<mql[i].query.length;j++) {
						if(mql[i].query[j].length()>0) {
						mql[i].query[j]=stemmer.stripAffixes(mql[i].query[j]);
						}
						for (int k=0;k<stopwords.length;k++) {
						if(mql[i].query[j].equals(stopwords[k])) {
							mql[i].query[j]="";
						}
						mql[i].query[j]=mql[i].query[j].replaceAll("[0-9]", "");
						}
					}
					int countWords=0;
					String[] tempArry = new String[mql[i].query.length];
					for(int l =0;l<mql[i].query.length;l++) {
						if(mql[i].query[l].length()>0) {
							tempArry[countWords]=mql[i].query[l];
							countWords=countWords+1;
						}
					}
					String[] token = new String[countWords];
					for(int l=0;l<countWords;l++) {
						token[l]=tempArry[l];
					}
					tempq=new myquerylist(token,mql[i].numb,token.length);
					mql[i]=tempq;
					
				}
				//----------end clean query--------------
				fileName = "Trec_microblog11.txt";
				 line = null;

				
				int numbofline=0;
				try {
					FileReader fileReader = new FileReader("/Users/bigxiaoxiao/Downloads/eclipse defult/csi4017assignment1f/src/csi4017assignment1f/"+fileName);
					BufferedReader bufferedReader = new BufferedReader(fileReader);
					while((line = bufferedReader.readLine()) != null) {
						numbofline+=1;
					}

				}
				catch(FileNotFoundException ex) {
					System.out.println("Unable to open file '"+fileName+"'");
					}
				catch(IOException ex) {
		        System.out.println(
		            "Error reading file '" + fileName + "'");                

		    }
				
				document[] mydoc=new document[numbofline];
				String temp2[];
				document tempdoc;
				int uselesscount=0;
				fileName = "Trec_microblog11.txt";
				 line = null;

				

				try {
					FileReader fileReader = new FileReader("/Users/bigxiaoxiao/Downloads/eclipse defult/csi4017assignment1f/src/csi4017assignment1f/"+fileName);
					BufferedReader bufferedReader = new BufferedReader(fileReader);
					while((line = bufferedReader.readLine()) != null) {
						line = line.replace('	', ' ');
						temp2=line.split(" ");
						tempdoc=new document(temp2,temp2[0],temp2.length-1);
						mydoc[uselesscount]=tempdoc;
						uselesscount+=1;
					}

				}
				catch(FileNotFoundException ex) {
					System.out.println("Unable to open file '"+fileName+"'");
					}
				catch(IOException ex) {
		        System.out.println(
		            "Error reading file '" + fileName + "'");                

		    }
				unclean = null;
				//------------ end store tweeter message-----------
				for(int i =0;i<mydoc.length;i++) {
					for(int j=0;j<mydoc[i].content.length;j++) {
						if(mydoc[i].content[j].length()>0) {
						mydoc[i].content[j]=stemmer.stripAffixes(mydoc[i].content[j]);
						}
						for (int k=0;k<stopwords.length;k++) {
						if(mydoc[i].content[j].equals(stopwords[k])) {
							mydoc[i].content[j]="";
						}
						mydoc[i].content[j]=mydoc[i].content[j].replaceAll("[0-9]", "");
						}
					}
					int countWords=0;
					String[] tempArry = new String[mydoc[i].content.length];
					for(int l =0;l<mydoc[i].content.length;l++) {
						if(mydoc[i].content[l].length()>0) {
							tempArry[countWords]=mydoc[i].content[l];
							countWords=countWords+1;
						}
					}
					String[] token = new String[countWords];
					for(int l=0;l<countWords;l++) {
						token[l]=tempArry[l];
					}
					tempdoc=new document(token,mydoc[i].id,token.length);
					mydoc[i]=tempdoc;
					
				}
				//----------------end clean message---------------------
				inverted_index mytable[][]=new inverted_index[mql.length][];
				//String cil;
				for(int i=0;i<mql.length;i++) {
					for(int j=0;j<mql[i].query.length;j++) {
						mytable[i]=new inverted_index[mql[i].query.length];
					}
				}
				for(int i=0;i<mytable.length;i++) {
					for(int j=0;j<mytable[i].length;j++) {
						mytable[i][j]=new inverted_index(mql[i].query[j]);
					}
				}
				
				boolean flag=false;
				for(int i=0;i<mytable.length;i++) {
					for(int j=0;j<mytable[i].length;j++) {
						for(int k=0;k<mydoc.length;k++) {
							flag=false;
							for(int l=0;l<mydoc[k].content.length;l++) {
								if(mytable[i][j].ci.equals( mydoc[k].content[l])) {
									if(!flag) {
										mytable[i][j].addDf();
										node temp_node=new node(mydoc[k].id,mydoc[k].content.length);
										mytable[i][j].addNode(temp_node);
										flag=true;
									}
									mytable[i][j].tail.addTF();
								}
							}
						}
					}
				}
				for(int i=0;i<mytable.length;i++) {
					for(int j=0;j<mytable[i].length;j++) {
						node index=mytable[i][j].root.next;
						
						while(index!=null) {
						mytable[i][j].tttf+=index.tf;
						index=index.next;
						}
					}
				}
				for(int i=0;i<mytable.length;i++) {
					for(int j=0;j<mytable[i].length;j++) {
						if(mytable[i][j].df>0) {
						mytable[i][j].wwwt=(float) ((mytable[i][j].tttf*0.5+0.5)*Math.log10((numbofline/mytable[i][j].df)));
						}else {
							mytable[i][j].wwwt=0;
						}
					}
				}
				
					for(int i=0;i<mytable.length;i++) {
					for(int j=0;j<mytable[i].length;j++) {
						node index=mytable[i][j].root.next;
						
						while(index!=null) {
						index.weight=(float) ((0.5+0.5*index.tf)* Math.log10((numbofline/mytable[i][j].df)));
						index=index.next;
						}
					}
				}
					float sum=0;
					for(int i=0;i<mytable.length;i++) {
						sum=0;
						for(int j=0;j<mytable[i].length;j++) {
							sum+=(mytable[i][j].wwwt)*(mytable[i][j].wwwt);
						}
						float tempfloat=(float) Math.sqrt(sum);
						for(int k=0;k<mytable[i].length;k++) {
							mytable[i][k].wwlength=tempfloat;	
							}
						}
					for(int i=0;i<mytable.length;i++) {
					
						for(int j=0;j<mytable[i].length;j++) {
							if(mytable[i][j].wwlength>0) {
							mytable[i][j].wwwnz=mytable[i][j].wwwt/mytable[i][j].wwlength;
							}else {
								mytable[i][j].wwwnz=0;
							}
						}
						}
					
					for(int i=0;i<mytable.length;i++) {
						
						for(int j=0;j<mytable[i].length;j++) {
							node index=mytable[i][j].root.next;
							
							while(index!=null) {
							index.nz=(float) (index.weight/Math.sqrt(index.lenggth));
							index=index.next;
							}
						}
						}
					
				
					
						node print[]; 
						node hold=null;
						float max=-1;
						int countstore=0;
						
						
						  for(int i=0;i<mytable.length;i++) {
							 print =new node[1000];
							  countstore=0;
							  max=-1;
							  hold=null;
							  while(countstore<1000) {			 
								 
								for(int j=0;j<mytable[i].length;j++) {
									node index=mytable[i][j].root.next;
									while(index!=null) {
										if(index.nz>max&&index.nz<1){
											if(countstore>0 &&index.nz<print[countstore-1].nz) {
												
											max=index.nz;
											hold=index;
											}else {
												max=index.nz;
												hold=index;	
											}
										}
									
										index=index.next;
										
									}
									
									}
								print[countstore]=hold;
								countstore+=1;
								max=-1;
								hold=null;
							  }
							 // System.out.println("i have added "+i+" element.");
							   try{
					  // Create file 
					  FileWriter fstream = new FileWriter("Result.txt",true);
					  BufferedWriter out = new BufferedWriter(fstream);
					  for(int l=0;l<print.length;l++) {
							out.write("MB0"+(l+1)+" Q"+i+" "+print[l].document_id+" "+(l+1)+" "+print[l].nz+" myRun");
							out.newLine();
							}
					  //Close the output stream
					  out.close();
					  }catch (Exception e){//Catch exception if any
					  System.err.println("Error: " + e.getMessage());
					  }
							   }
							  
								
							   try{
									  // Create file 
									  FileWriter fstream = new FileWriter("score.txt");
									  BufferedWriter out = new BufferedWriter(fstream);
									  for(int l=0;l<mytable.length;l++) {
										  for(int k=0;k<mytable[l].length;k++) {
											  node pp = mytable[l][k].root.next;
											  while(pp!=null) {
												  
											  String p="";
											  p+=pp.nz;
											out.write(p);
											out.write(" ");
											
											pp=pp.next;
											}
											}
										  out.newLine();
									  }
									  //Close the output stream
									  out.close();
									  }catch (Exception e){//Catch exception if any
									  System.err.println("Error: " + e.getMessage());
									  }
					
						  
				/*
				if(max<index.nz&& index.nz<1) {
											if(countstore!=0){
											if(print[countstore-1]!=null) {
												if( index.nz == print[countstore-1].nz ) {
													
												}else {
													max=index.nz;
													hold=index;
												}
											}else {
												max=index.nz;
												hold=index;
											}
										}else {
											
											max=index.nz;
											hold=index;
											
										}
											}
					  */
					  
				System.out.println("output has been printed");
				//System.out.println(mytable[0][2].root.next.tf);
				//
				// test
				/*for(int i =0;i<mql[0].query.length;i++) {
					System.out.println(mql[0].query[i]);
				}*/

				//System.out.println(mql[0].wlength);
				//System.out.println(mydoc[0].content[0].length());
				//System.out.println(mydoc[1].content[7]);
				
				/*
				 * 
				inverted_index myDtable[][]=new inverted_index[mydoc.length][];
				for(int i=0;i<mydoc.length;i++) {
					for(int j=0;j<mydoc[i].content.length;j++) {
						myDtable[i]=new inverted_index[mydoc[i].content.length];
					}
				}
				
				
				for(int i=0;i<myDtable.length;i++) {
					for(int j=0;j<myDtable[i].length;j++) {
				
						myDtable[i][j]=new inverted_index(mydoc[i].content[j]);
					}
				}
				
				
				//===
				//===
				
				
				
				
				
				
				
				boolean flag1=false;
				for(int i=0;i<myDtable.length;i++) {
					for(int j=0;j<myDtable[i].length;j++) {
						for(int k=0;k<mql.length;k++) {
							flag1=false;
							for(int l=0;l<mql[k].query.length;l++) {
								if(myDtable[i][j].ci.equals( mql[k].query[l])) {
									if(!flag1) {
										myDtable[i][j].addDf();
										Integer intInstance = new Integer(mql[k].numb);
										node temp_node=new node(intInstance.toString());
										myDtable[i][j].addNode(temp_node);
										flag1=true;
									}
									myDtable[i][j].tail.addTF();
								}
							}
						}
					}
				}
					for(int i=0;i<myDtable.length;i++) {
					
					for(int j=0;j<myDtable[i].length;j++) {
						node index=myDtable[i][j].root.next;
						
						while(index!=null) {
						index.weight=index.tf;
						index=index.next;
						}
					}
				}
					for(int i=0;i<myDtable.length;i++) {
						for(int j=0;j<myDtable[i].length;j++) {
							float sum =0;
							node index=myDtable[i][j].root.next;
							while(index != null) {
							sum+=index.weight*index.weight;
							index=index.next;
								}
							//System.out.println(sum);
							myDtable[i][j].length=(float) Math.sqrt(sum);
						}
					}
				inverted_index myscore[][]=mytable;
				for(int i =0;i<mytable.length;i++) {
					for(int j =0;j<mytable.length;i++) {
						node q=mytable[i][j].root.next;
						node d=myDtable[i][j].root.next;
						node s=myscore[i][j].root.next;
						while(q!=null) {
						if(q != null && d != null) {
							s.nz=q.nz*d.nz/(mytable[i][j].length*(mytable[i][j].length));
						}else {
							s.nz=0;
						}
						q=q.next;
						d=d.next;
						s=s.next;
						}
					}
				}*/
	}
	

}
