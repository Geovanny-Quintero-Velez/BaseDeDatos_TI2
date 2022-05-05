package structures;


import java.util.Comparator;

public class ArbolBinarioAV<E ,C extends Comparator<E>> {
	
	private Node root;
	private C comparator;
	
	public ArbolBinarioAV(C comparetor) {
		this.comparator=comparetor;
	}
	
	public ArbolBinarioAV() {
		
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
	
	public void insert(Node current,Node toInsert) {
		if(comparator.compare(toInsert.getValue(),current.getValue())>=0) {
			if(current.getRight()==null) {
				current.setRight(toInsert);
				
			}else insert(current.getRight(),toInsert);
		}else {
			if(current.getLeft()==null) {
				current.setLeft(toInsert);
				
			}else insert(current.getLeft(),toInsert);
		}
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
			return root.search(toSearch)!=null;
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
				
				return true;
			}else {
				if(toDelete.getLeft()!=null) {
					if(parent!=null) {
						if(parent.getLeft()==toDelete) {
							parent.setLeft(toDelete.getLeft());
						}else {
							parent.setRight(toDelete.getLeft());
						}
						
						return true;
					}else {
						root=root.getLeft();
						
						return true;
					}
				}else {
					if(parent!=null) {
						if(parent.getLeft()==toDelete) {
							parent.setLeft(toDelete.getRight());
						}else {
							parent.setRight(toDelete.getRight());
						}
						
					}else {
						root=root.getRight();
					
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
		
		public void setParent(Node parent) {
			this.parent = parent;
		}
	}
}
