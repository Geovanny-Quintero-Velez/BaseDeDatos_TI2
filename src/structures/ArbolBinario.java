package structures;

import java.util.ArrayList;
import java.util.Comparator;

public class ArbolBinario<E ,C extends Comparator<E>> {
	
	private Node root;
	private C comparator;
	
	public ArbolBinario(C comparetor) {
		this.comparator=comparetor;
	}
	
	public ArbolBinario() {
		
	}
	
	public void setComparator(C comparator) {
		this.comparator=comparator;
	}
	
	public E get( E search) {
		Node searched=new Node(search);
		Node nodeFound=root.search(searched);
		if(nodeFound!=null) {
			return nodeFound.getValue();
		}
		return null;
	}
	
	public void insert(E value) {
		Node newNode=new Node(value);
		if(root==null) {
			root=newNode;
		}else {
			insert(root,newNode);
		}
	}
	
	public int size() {
		return root.countNodes();
	}
	
	
	public E left() {
		return root.getLeft().getValue();
	}
	
	public Node getRoot() {
		return root;
	}
	
	public E right() {
		return root.getRight().getValue();
	}
	
	public int factor() {
		return root.factorBalance();
	}
	
	public void insert(Node current,Node toInsert) {
		if(comparator.compare(toInsert.getValue(),current.getValue())>=0) {
			if(current.getRight()==null) {
				current.setRight(toInsert);
				balance(toInsert);
			}else insert(current.getRight(),toInsert);
		}else {
			if(current.getLeft()==null) {
				current.setLeft(toInsert);
				balance(toInsert);
			}else insert(current.getLeft(),toInsert);
		}
	}
	
	public void balance(Node node) {
		if(node==root) {
			if(!root.isBalanced()) {
				int fb=root.factorBalance();
				if(fb==2) {
					int fbs=root.getRight().factorBalance();
					if(fbs==0||fbs==1) {
						root=root.leftRotation();
					}else if(fbs==-1){
						root.getRight().rightRotation();
						root=root.leftRotation();
					}
				}else if(fb==-2){
					int fbs=root.getLeft().factorBalance();
					if(fbs==0||fbs==-1) {
						root=root.rightRotation();
					}else if(fbs==1){
						root.getLeft().leftRotation();
						root=root.rightRotation();
					}
				}
			}
			
		}else if(node.isLeaf()) {
			balance(node.getParent());
		}else {
			if(node.balance()==null) {
				balance(node.getParent());
			}
		
		}
		
	}
	
	public ArrayList<E> getGreater(E value) {
		ArrayList<E> greater=new ArrayList<>();
		return getGreater(root,greater,value);
	}
	public ArrayList<E> getGreater(Node current,ArrayList<E> array,E value) {
		ArrayList<E> greater=array;
		if(root!=null) {
			
			if(comparator.compare( current.getValue(),value)>=0 ) {
				greater.add(current.getValue());
			}
			if(current.getRight()!=null) {
				greater=getGreater(current.getRight(), greater, value);
			}
			if(current.getLeft()!=null) {
				greater=getGreater(current.getLeft(), greater, value);
			}
		}
		return greater;
	}
	public boolean isBalanced() {
		return root.isBalanced();
	}
	
	public int countNodes() {
		if(root!=null) {
			return root.countNodes();
		}
		return 0;
	}
	
	public int countLeafts() {
		if(root!=null) {
			return root.countLeafts();
		}
		return 0;
	}
	
	public boolean search(E value) {
		Node toSearch=new Node(value);
		if(root==null) {
			return false;
		}else {
			return root.search(toSearch)!=null;//search(root,toSearch);
		}
	}
	
	public int height() {
		return root.height();
	}
	
	
	public Node getNode(E value) {
		Node toSearch=new Node(value);
		if(root==null) {
			return null;
		}else {
			return getNode(root,toSearch);
		}
	}
	
	private Node getNode(Node current,Node search) {
		
		if(current==null) {
			return null;
		}
		if(current.isLeaf()) {
			if(current.getValue().equals(search.getValue())) {
				return current;
			}else {
				return null;
			}
		}else {
			if(current.getValue()==search.getValue()) {
				return current;
			}else if(comparator.compare(search.getValue(),current.getValue())>=0) {
				return getNode( current.getRight(),search);
			}else {
				return getNode(current.getLeft(),search);
			}
		}
	}
	
	
	public boolean delete(E value) {
		if(root==null) {
			return false;
		}else{
			Node node=new Node(value);
			Node toDelete=root.search(node);
			if(toDelete==null) {
				return false;
			}
			if(toDelete==root) {
				if(root.isLeaf()) {
					root=null;
					return true;
				}
			}
			Node parent=toDelete.getParent();
			if(toDelete.isLeaf()) {
				if(parent.getLeft()==toDelete) {
					parent.setLeft(null);
					return true;
				}else {
					parent.setRight(null);
					return true;
				}
			}else if(toDelete.getLeft()!=null&&toDelete.getRight()!=null) {
				Node succesor=getSuccesor(toDelete,toDelete);
				Node parentSuccesor= succesor.getParent();
				
				if(parentSuccesor.getLeft()==succesor) {
					parentSuccesor.setLeft(succesor.getRight());
				}else {
					parentSuccesor.setRight(succesor.getRight());
					
				}
				
				toDelete.setValue(succesor.getValue());
				balance(toDelete);
				return true;
			}else {
				if(toDelete.getLeft()!=null) {
					if(parent!=null) {
						if(parent.getLeft()==toDelete) {
							parent.setLeft(toDelete.getLeft());
						}else {
							parent.setRight(toDelete.getLeft());
						}
						balance(parent);
						return true;
					}else {
						root=root.getLeft();
						balance(root);
						return true;
					}
				}else {
					if(parent!=null) {
						if(parent.getLeft()==toDelete) {
							parent.setLeft(toDelete.getRight());
						}else {
							parent.setRight(toDelete.getRight());
						}
						balance(parent);
					}else {
						root=root.getRight();
						balance(root);
					}
					return true;
				}
			}
			
		}
	}
	
	
	public String preOrden() {
		if(root!=null) {
			return "["+root.preOrden()+"]";
		}else {
			return "[]";
		}
	}
	
	public Node getSuccesor(Node current,Node parent) {
		if(parent.getRight()==null) {
			if(parent.getLeft()!=null) {
				return parent.getLeft();
			}else
			return null;
		}else if(parent.getRight().getLeft()==null) {
			return parent.getRight();
		}else if(current.getLeft()==null) {
				return current;
		}else if(current==parent) {
			return getSuccesor(current.getRight(),parent);
		}else {
			return getSuccesor(current.getLeft(),parent);
		}	
	}
	
	public Node getAntecesor(Node current,Node parent) {
		if(parent.getLeft()==null) {
			return null;
		}else if(parent.getLeft().getRight()==null) {
			return parent.getLeft();
		}else if(current.getRight()==null) {
				return current;
		}else if(current==parent) {
			return getAntecesor(current.getLeft(),parent);
		}else {
			return getAntecesor(current.getRight(),parent);
		}
	}
	
	
	public Node tryGsa() {
		Node a=root;
		return getAntecesor(root,a);
	}
	
	public String toString() {
		String out="[";
		if(root!=null) {
			out+=root.inorder();
		}
		out+="]";
		return out;
	}
	
	private class Node{
		private E value;
		private Node left;
		private Node right;
		private Node parent;
		
		@SuppressWarnings("unused")
		public Node() {
			
		}
		
		public Node(E value) {
			this.value=value;
		}
		
		public void setValue(E value) {
			this.value=value;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			if(left!=null) {
				left.setParent(this);
			}
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			if(right!=null) {
				right.setParent(this);
			}
			this.right = right;
		}

		public E getValue() {
			return value;
		}
		
		
		public boolean isLeaf() {
			return left==null&&right==null;
		}
		
		public int countNodes() {
			int count=1;
			
				if(right!=null) {
					count+=right.countNodes();
				}
				if(left!=null) {
					count+=left.countNodes();
				}
			return count;
		}
		
		public int height() {
		int hLeaft=0;
		int hRight=0;
		if(left!=null) {
			hLeaft= left.height();
		}
		if(right!=null) {
			hRight=right.height();
		}
		return Math.max(hLeaft, hRight)+1;
		}
		
		public String toString() {
			String out=""+value;			
		return out;
		}
		public String inorder() {
			String out="";
			if(isLeaf()) {
				return ""+value;
			}else {
				
				if(left!=null) {
					out+=left.inorder()+",";
				}
				out+=value;
				if(right!=null) {
					out+=","+right.inorder();
				}
				
			}
			
		return out;
		}
		
		 public Node search(Node node){
		        if(comparator.compare(node.value,value)==0){
		            return this;
		        } else if (comparator.compare(node.value, value) <0){ 
		            if (this.left == null){
		                return  null;
		            }else {
		                return this.left.search(node);
		            }
		        }else { 
		            if (this.right == null){
		                return  null;
		            }else {
		                return this.right.search(node);
		            }
		        }
		}
		 
		public String preOrden() {
			String out="";
			if(isLeaf()) {
				return ""+value;
			}else {
				out+=value;
				if(right!=null) {
					out+=","+right.inorder();
				}
				
				if(left!=null) {
					out+=","+left.inorder();
				}
				
			}
			
		return out;
		}
		
		
		public int countLeafts() {
			int count=0;
			if(isLeaf()) {
				return 1;
			}	else {
				if(right!=null) {
					count+=right.countLeafts();
				}
				if(left!=null) {
					count+=left.countLeafts();
				}
			}
			
			return count;
		}

		public Node getParent() {
			return parent;
		}
		
		public boolean isBalanced() {
			int fb=Math.abs(factorBalance());
			return fb==1||fb==0;
		}
		
		public int factorBalance() {
			int rightN=0;
			int leftN=0;
			if(right!=null) {
				rightN=right.height();
			}
			if(left!=null) {
				leftN=left.height();
			}
			
			return rightN-leftN;
		}
		
		public Node leftRotation() {
			 Node p=this;
			 Node q=right;
			 Node thisParent=parent;
			 Node b=q.left;
			 Node c=q.getRight();
			 Node a=p.left;
			 if(thisParent!=null) {
				 if(thisParent.getLeft()==p) {
					 thisParent.setLeft(q);
					
				 }else {
					 thisParent.setRight(q);
					
				 } 
			 }else {
				 q.setParent(null);
			 }
			 q.setLeft(p);
			 q.setRight(c);
			 p.setLeft(a);
			 p.setRight(b);
		   return q;
		}
		
		public Node rightRotation() {
			Node p=this;
			Node thisParent=parent;
			Node q=p.left;
			Node a=p.right;
			Node b=q.right;
			Node c=q.left;
			if(thisParent!=null) {
				 if(thisParent.getLeft()==p) {
					 thisParent.setLeft(q);
					 
				 }else {
					 thisParent.setRight(q);
					 
				 }
			}else {
				q.setParent(null);
			}
			 q.setRight(p);
			 q.setLeft(c);
			 p.setLeft(b);			
			 p.setRight(a);
			 return q;
		}
		
		
		public Node balance() {
			
			int fb=factorBalance();
			if(fb==2) {
				int fbs=right.factorBalance();
				if(fbs==0||fbs==1) {
					return leftRotation();
				}else if(fbs==-1){
					return doubleRotationR();
				} 
				
			}else if(fb==-2) {
				int fbs=left.factorBalance();
				if(fbs==-1||fbs==0) {
					return rightRotation();
				}else if(fbs==1) {
					return doubleRotationL();
				}
				
			}
			return null;
		}
		
		
		
		
		public Node doubleRotationR() {
			right.rightRotation();
			return leftRotation();
		}
		
		public Node doubleRotationL() {
			left.leftRotation();
			return rightRotation();
		}
		
		public void setParent(Node parent) {
			this.parent = parent;
		}
	}
}
