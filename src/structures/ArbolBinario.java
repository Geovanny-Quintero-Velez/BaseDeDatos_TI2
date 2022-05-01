package structures;

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
	
	public void insert(E value) {
		Node newNode=new Node(value);
		if(root==null) {
			root=newNode;
		}else {
			insert(root,newNode);
		}
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
		boolean flag=false;
		
		flag=comparator.compare(current.getValue(),toInsert.getValue())>=0;
		
		if(flag) {
			if(current.getRight()==null) {
				current.setRight(toInsert);
				toInsert.setParent(current);
				balance(toInsert);
			}else insert(current.getRight(),toInsert);
		}else {
			if(current.getLeft()==null) {
				current.setLeft(toInsert);
				toInsert.setParent(current);
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
			if(!node.isBalanced()) {
				node.balance();
			}
			balance(node.getParent());
		}
		
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
			return search(root,toSearch);
		}
	}
	
	public int height() {
		return root.height();
	}
	
	public boolean search(Node current,Node search) {
		
		if(current==null) {
			return false;
		}
		if(current.isLeaf()) {
			if(current.getValue().equals(search.getValue())) {
				return true;
			}else {
				return false;
			}
		}else {
			if(current.getValue()==search.getValue()) {
				return true;
			}else if(comparator.compare(current.getValue(),search.getValue())>=0) {
				return search( current.getRight(),search);
			}else {
				return search(current.getLeft(),search);
			}
		}
	}
	
	
	
	public E getElement(E value) {
		Node toSearch=new Node(value);
		if(root==null) {
			return root.getValue();
		}else {
			return get(root,toSearch);
		}
	}
	
	private E get(Node current,Node search) {
		
		if(current==null) {
			return null;
		}
		if(current.isLeaf()) {
			if(current.getValue().equals(search.getValue())) {
				return current.getValue();
			}else {
				return null;
			}
		}else {
			if(current.getValue()==search.getValue()) {
				return current.getValue();
			}else if(comparator.compare(current.getValue(),search.getValue())>=0) {
				return get( current.getRight(),search);
			}else {
				return get(current.getLeft(),search);
			}
		}
	}
	
	public boolean delete(E value) {
		if(root==null) {
			return false;
		}else {
			if(value==root.getValue()) {
				if(root.isLeaf()) {
					root=null;
				}else if(root.getLeft()==null&&root.getRight()!=null) {
					root.getRight().setLeft(root.getLeft());
					root.getRight().setParent(null);
					root=root.getRight();
					balance(root);
				}else {
					Node succesor=getSuccesor(root,root);
					if(succesor.getParent()!=null) {
						Node parentSuccesor=succesor.getParent();
						if(parentSuccesor.getLeft()==succesor) {
							if(succesor.getRight()!=null) {
								parentSuccesor.setLeft(succesor.getRight());
							}else parentSuccesor.setLeft(null);
						}else {
							if(succesor.getRight()!=null) {
								parentSuccesor.setRight(succesor.getRight());
							}else parentSuccesor.setRight(null);
						}
						balance(parentSuccesor);
					}
					balance(succesor);
					succesor.setParent(null);
					succesor.setLeft(root.getLeft());
					succesor.setRight(root.getRight());
					root=succesor;
				}
				
				return true;
			}else {
				return delete(root,value);
			}
		}
	}
	
	public boolean delete(Node current,E toDelete) {
		if(current.getValue()==toDelete) {
			if(current.isLeaf()) {
				Node parent=current.getParent();
				if(parent.getLeft()==current) {
					parent.setLeft(null);
				}else {
					parent.setRight(null);
				}
				balance(parent);
				
				return true;
			}else if(current.getLeft()==null&&current.getRight()!=null){
				Node parent=current.getParent();
				if(parent.getLeft()==current) {
					parent.setLeft(current.getRight());
				}else {
					parent.setRight(current.getRight());
				}
				balance(parent);
				return true;
			}else {
				Node parent=current.getParent();
				Node succesor=getSuccesor(current,current);
				if(succesor.getParent()!=null) {
					Node parentSuccesor=succesor.getParent();
					if(parentSuccesor.getLeft()==succesor) {
						parentSuccesor.setLeft(null);
					}else {
						parentSuccesor.setRight(null);
					}
				}
				succesor.setParent(current.getParent());
				succesor.setLeft(current.getLeft());
				succesor.setRight(current.getRight());
				if(parent.getLeft()==current) {
					parent.setLeft(succesor);
				}else {
					parent.setRight(succesor);
				}
				balance(parent);
				return true;
			}
		}else if(comparator.compare(current.getValue(), toDelete)>=1) {
			if(current.getRight()!=null) {
				return delete(current.getRight(),toDelete);
			}else {
				return false;
			}
		}else {
			if(current.getLeft()!=null) {
				return delete(current.getLeft(),toDelete);
			}else {
				return false;
			}			
		}
	}
	
	public Node search(Node node, E value) {
		if(node == null || value==node.getValue()) {
			return node;
		}
		if(comparator.compare(node.getValue(),value)<0) {
			return search(node.getLeft(),value);
		}else {
			return search(node.getRight(),value);
		}
	}
	
	public void remove(E value) {
		Node node = search(root, value);
		Node actual = null;
		Node temp = null;
		if(node.getLeft() == null || node.getRight() == null) {
			actual = node;
		}else {
			actual = successor(node);
		}
		if(actual.getLeft() != null) {
			temp = actual.getLeft();
		}else {
			temp = actual.getRight();
		}
		if(searchFather(root, actual.getKey())==null) {
			root = temp;
		}else if(actual.getKey() == searchFather(root, actual.getKey()).getLeft().getKey()) {
			searchFather(root, actual.getKey()).setLeft(temp);
		}else {
			searchFather(root, actual.getKey()).setRight(temp);
		}
		if(actual.getKey() != node.getKey()) {
			node.setKey(actual.getKey());
		}
	}
	
	public String inorderReverse() {
		if(root!=null) {
			return root.preOrden();
		}else {
			return "";
		}
	}
	
	public Node getSuccesor(Node current,Node parent) {
		if(parent.getRight()==null) {
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
	
	
	public Node tryGs() {
		Node a=root;
		return getSuccesor(root,a);
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

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
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
		
		public void rightRotation(int noSirve) {
			Node toRotate = this;
			Node rightSon = toRotate.right;
			Node parent = toRotate.parent;
			Node grandParent = parent.parent;
			if(grandParent.getLeft()==parent) {
				grandParent.setLeft(toRotate);				
			}else if(grandParent.getRight()==parent) {
				grandParent.setRight(toRotate);
			}
			parent.setLeft(rightSon);
			parent.setParent(toRotate);
			toRotate.setRight(parent);
			toRotate.setParent(grandParent);
			if(rightSon != null) {
				rightSon.setParent(parent);
			}
		}
	
		public void leftRotation(int noSirve) {
			Node toRotate = this;
			Node leftSon = toRotate.left;
			Node parent = toRotate.parent;
			Node grandParent = parent.parent;
			if(grandParent.getLeft()==parent) {
				grandParent.setLeft(toRotate);				
			}else if(grandParent.getRight()==parent) {
				grandParent.setRight(toRotate);
			}
			parent.setRight(leftSon);
			parent.setParent(toRotate);
			toRotate.setLeft(parent);
			toRotate.setParent(grandParent);
			if(leftSon != null) {
				leftSon.setParent(parent);
			}
		}
		
		public void searchUnbalance(boolean unbalance) {
			if(factorBalance() == 1) {
				if(unbalance != true) {
					
				}
			}else if(factorBalance() == -1) {
				
			}else if(factorBalance() == 2 || factorBalance() == 2) {
				unbalance = true;
			}
			
			if(factorBalance() == 2) {
				
			}else if(factorBalance() == 2) {
				
			}else if(factorBalance() == 1) {
				
			}else if(factorBalance() == -1) {
				
			}
			
			if( Math.abs(factorBalance())== 1 || factorBalance()== 0) {
				
			}
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
					 q.setParent(thisParent);
				 }else  {
					 thisParent.setRight(q);
					 q.setParent(thisParent);
				 } 
			 }else {
				 q.setParent(null);
			 }
			 q.setLeft(p);
			 p.setParent(q);
			 q.setRight(c);
			 if(c!=null) {
				 c.setParent(q);
			 }
			 p.setLeft(a);
			 if(a!=null) {
				 a.setParent(p);
			 }
			 p.setRight(b);
			 if(b!=null) {
				 b.setParent(p);
			 }
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
					 q.setParent(thisParent);
				 }else  {
					 thisParent.setRight(q);
					 q.setParent(thisParent);
				 }
			}else {
				q.setParent(null);
			}
			
			 q.setRight(p);
			 
			 p.setParent(q);
			 
			 q.setLeft(c);
			 
			 if(c!=null) {
				 c.setParent(q);
			 }
			 
			 p.setLeft(b);
			 
			 if(b!=null) {
				 b.setParent(q);
			 }
			 
			 p.setRight(a);
			 
			 if(a!=null) {
				 a.setParent(q);
			 }
			 
			
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
