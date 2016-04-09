package j3l.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import j3l.util.check.ArgumentChecker;
import j3l.util.stream.StreamMode;


/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2016.03.14_0
 * @author Johannes B. Latzel
 */
public class BinaryTree<T, R extends Comparable<R>> implements Iterable<T> {
	
	
	/**
	 * <p></p>
	 */
	private final Function<T, R> attribute_function;
	
	
	/**
	 * <p></p>
	 */
	private BinaryNode head;
	
	
	/**
	 * <p></p>
	 */
	private long size;
	
	
	/**
	 * <p></p>
	 */
	private boolean length_changed;
	
	
	/**
	 * <p></p>
	 *
	 * @param comparator
	 */
	public BinaryTree(Function<T, R> attribute_function) {
		this.attribute_function = ArgumentChecker.checkForNull(attribute_function, "attribute_function");
		head = null;
		size = 0;
		length_changed = false;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public boolean isEmpty() {
		return head == null;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public long getSize() {
		if( !length_changed ) {
			return size;
		}
		long counter = 0;
		for(Iterator<T> iterator=iterator();iterator.hasNext();counter++) {
			iterator.next();
			if( counter < 0 ) {
				return Long.MAX_VALUE;
			}
		}
		size = counter;
		length_changed = false;
		return size;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param t
	 */
	public boolean add(T t) {
		length_changed = true;
		if( head == null ) {
			head = new BinaryNode(t);
			return true;
		}
		BinaryNode current_node = head;
		R attribute = attribute_function.apply(t);
		do {
			if( attribute.compareTo(attribute_function.apply(current_node.getValue())) <= 0 ) {
				if( !current_node.hasLeft() ) {
					current_node.setLeft(t);
					return true;
				}
				else {
					current_node = current_node.getLeft();
				}
			}
			else {
				if( !current_node.hasRight() ) {
					current_node.setRight(t);
					return true;
				}
				else {
					current_node = current_node.getRight();
				}
			}
			
		}
		while( current_node != null );
		return false;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public boolean addAll(Collection<T> collection) {
		boolean return_value = true;
		for( T t : collection ) {
			return_value &= add(t);
		}
		return return_value;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	private boolean add(BinaryNode node) {
		length_changed = true;
		if( head == null ) {
			head = node;
			return true;
		}
		BinaryNode current_node = head;
		R attribute = attribute_function.apply(node.getValue());
		do {
			if( attribute.compareTo(attribute_function.apply(current_node.getValue())) <= 0 ) {
				if( !current_node.hasLeft() ) {
					current_node.setLeft(node);
					return true;
				}
				else {
					current_node = current_node.getLeft();
				}
			}
			else {
				if( !current_node.hasRight() ) {
					current_node.setRight(node);
					return true;
				}
				else {
					current_node = current_node.getRight();
				}
			}
			
		}
		while( current_node != null );
		return false;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public boolean remove(T t) {
		if( head == null ) {
			return false;
		}
		BinaryNode current = head;
		BinaryNode previous = null;
		R attribute = ArgumentChecker.checkForNull(attribute_function.apply(t), "attribute");
		int comparison;
		do {
			comparison = attribute.compareTo(attribute_function.apply(current.getValue()));
			if( comparison < 0 ) {
				if( !current.hasLeft() ) {
					return false;
				}
				previous = current;
				current = current.getLeft();
			}
			else if( comparison == 0 ) {
				if( previous == null ) {
					if( head.hasLeft() ) {
						head = current.getLeft();
						if( current.hasRight() ) {
							add(current.getRight());
						}
					}
					else if( head.hasRight() ) {
						head = current.getRight();
					}
					else {
						head = null;
					}
					length_changed = true;
					return true;
				}
				else {
					if( attribute_function.apply(current.getValue()).compareTo(attribute_function.apply(previous.getValue())) <= 0 ) {
						previous.removeLeft();
					}
					else {
						previous.removeRight();
					}
					if( current.hasLeft() ) {
						add(current.getLeft());
					}
					if( current.hasRight() ) {
						add(current.getRight());
					}
					length_changed = true;
					return true;
				}
			}
			else {
				if( !current.hasRight() ) {
					return false;
				}
				previous = current;
				current = current.getRight();
			}
		}
		while( true );
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public T remove(ComparisonType comparison_type, R attribute) {
		if( head == null ) {
			return null;
		}
		ArgumentChecker.checkForNull(comparison_type, "comparison_type");
		BinaryNode current = head;
		BinaryNode previous = null;
		int comparison;
		Direction direction;
		do {
			comparison = attribute.compareTo(attribute_function.apply(current.getValue()));
			switch( comparison_type ) {
				case EqualTo:
					if( comparison < 0 ) {
						direction = Direction.Left;
					}
					else if( comparison == 0 ) {
						direction = Direction.Stay;
					}
					else {
						direction = Direction.Right;
					}
					break;
				case GreaterThan:
					if( comparison < 0 ) {
						direction = Direction.Stay;
					}
					else {
						direction = Direction.Right;
					}
					break;
				case GreaterThanOrEqualTo:
					if( comparison <= 0 ) {
						direction = Direction.Stay;
					}
					else {
						direction = Direction.Right;
					}
					break;
				case SmallerThan:
					if( comparison > 0 ) {
						direction = Direction.Stay;
					}
					else {
						direction = Direction.Left;
					}
					break;
				case SmallerThanOrEqualTo:
					if( comparison >= 0 ) {
						direction = Direction.Stay;
					}
					else {
						direction = Direction.Left;
					}
					break;
				case UnequalTo:
					if( comparison == 0 ) {
						// elements on the left could be <= to the current element
						// elements on the right are guaranteed to be unequal to the current element
						direction = Direction.Right;
					}
					else {
						direction = Direction.Stay;
					}
					break;
				default:
					throw new IllegalArgumentException("The type \"" + comparison_type.toString() + "\" is not supported.");
			}
			switch( direction ) {
				case Left:
					if( !current.hasLeft() ) {
						return null;
					}
					previous = current;
					current = current.getLeft();
					break;
				case Right:
					if( !current.hasRight() ) {
						return null;
					}
					previous = current;
					current = current.getRight();
					break;
				case Stay:
					if( previous == null ) {
						if( head.hasLeft() ) {
							head = current.getLeft();
							if( current.hasRight() ) {
								add(current.getRight());
							}
						}
						else if( head.hasRight() ) {
							head = current.getRight();
						}
						else {
							head = null;
						}
					}
					else {
						if( attribute_function.apply(current.getValue()).compareTo(attribute_function.apply(previous.getValue())) <= 0 ) {
							previous.removeLeft();
						}
						else {
							previous.removeRight();
						}
						if( current.hasLeft() ) {
							add(current.getLeft());
						}
						if( current.hasRight() ) {
							add(current.getRight());
						}
					}
					length_changed = true;
					return current.getValue();
				default:
					throw new IllegalArgumentException("The type \"" + direction.toString() + "\" is not supported.");				
			}
			
		}
		while( true );
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public T removeAny() {
		if( head == null ) {
			return null;
		}
		BinaryNode current = head;
		BinaryNode previous = null;
		do {
			if( current.hasLeft() ) {
				previous = current;
				current = current.getLeft();
			}
			else if( current.hasRight() ) {
				previous = current;
				current = current.getRight();
			}
			else {
				if( previous == null ) {
					if( head.hasLeft() ) {
						head = current.getLeft();
						add(current.getRight());
					}
					else if( head.hasRight() ) {
						head = current.getRight();
					}
					else {
						head = null;
					}
				}
				else {
					if( current == previous.getLeft() ) {
						previous.removeLeft();
					}
					else {
						previous.removeRight();
					}
					if( current.hasLeft() ) {
						add(current.getLeft());
					}
					if( current.hasRight() ) {
						add(current.getRight());
					}
				}
				length_changed = true;
				return current.getValue();
			}
		}
		while( true );
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public List<T> removeSome(long n) {
		LinkedList<T> list = new LinkedList<>();
		if( head == null || n < 1 ) {
			return list;
		}
		long remaining = n;
		LinkedList<BinaryNode> current_path = new LinkedList<>();
		current_path.add(head);
		BinaryNode current_node;
		BinaryNode previous_node;
		do {
			current_node = current_path.getLast();
			if( !current_node.hasChildren() ) {
				list.add(current_path.removeLast().getValue());
				if( current_path.isEmpty() ) {
					break;
				}
				previous_node = current_path.getLast();
				if( previous_node.hasLeft() ) {
					if( previous_node.getLeft() == current_node ) {
						previous_node.removeLeft();
					}
					else {
						previous_node.removeRight();
					}
				}
				else {
					previous_node.removeRight();
				}
				remaining--;
			}
			else {
				if( current_node.hasLeft() ) {
					current_path.add(current_node.getLeft());
				}
				else {
					current_path.add(current_node.getRight());
				}
			}
		}
		while( remaining > 0 && !current_path.isEmpty() );
		return list;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public List<T> removeAll() {
		List<T> list = toList();
		clear();
		return list;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public void clear() {
		head = null;
		size = 0;
		length_changed = false;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public T get(R attribute) {
		if( head == null ) {
			return null;
		}
		BinaryNode current = head;
		int comparison;
		do {
			comparison = attribute_function.apply(current.getValue()).compareTo(attribute);
			if( comparison < 0 ) {
				if( current.hasLeft() ) {
					return null;
				}
				current = current.getLeft();
			}
			else if( comparison == 0 ) {
				return current.getValue();
			}
			else {
				if( current.hasRight() ) {
					return null;
				}
				current = current.getRight();
			}
		}
		while( true );
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public List<T> toList() {
		LinkedList<T> list = new LinkedList<>();
		forEach(list::add);
		return list;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public Stream<T> stream() {
		return toList().stream();
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public Stream<T> stream(StreamMode stream_mode) {
		Stream<T> stream = stream();
		switch( stream_mode ) {
			case Sequential:
				return stream();
			case Parallel:
				return stream.parallel();
			default:
				return stream;
		}
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public Map<R, T> map() {
		HashMap<R, T> map = new HashMap<>();
		forEach(t -> {
			map.put(attribute_function.apply(t), t);
		});
		return map;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public boolean contains(T t) {
		if( head == null ) {
			return false;
		}
		BinaryNode current_node = head;
		R attribute = attribute_function.apply(t);
		T current_value;
		int comparison;
		do {
			current_value = current_node.getValue();
			comparison = attribute_function.apply(current_value).compareTo(attribute);
			if( comparison >= 0 ) {
				if( current_value == t ) {
					return true;
				}
				if( !current_node.hasLeft() ) {
					return false;
				}
				current_node = current_node.getLeft();
			}
			else {
				if( !current_node.hasRight() ) {
					return false;
				}
				current_node = current_node.getRight();
			}
		}
		while( true );
	}


	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override public Iterator<T> iterator() {
		return new BinaryTreeIterator(head);
	}
	
	
	/**
	 * <p></p>
	 * 
	 * @since JDK 1.8
	 * @version 2016.02.23_0
	 * @author Johannes B. Latzel
	 */
	private class BinaryTreeIterator implements Iterator<T> {
		
		
		/**
		 * <p></p>
		 */
		private final ArrayList<BinaryNode> next_node_list;
		
		
		/**
		 * <p></p>
		 *
		 * @param
		 * @return
		 */
		public BinaryTreeIterator(BinaryNode head) {
			next_node_list = new ArrayList<>(10);
			if( head != null ) {
				next_node_list.add(head);
			}
		}
		
		
		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		@Override public boolean hasNext() {
			return !next_node_list.isEmpty();
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override public T next() {
			if( !hasNext() ) {
				return null;
			}
			BinaryNode current_node = next_node_list.remove(next_node_list.size() - 1);
			if( current_node.hasLeft() ) {
				next_node_list.add(current_node.getLeft());
			}
			if( current_node.hasRight() ) {
				next_node_list.add(current_node.getRight());
			}
			return current_node.getValue();
		}
		
	}
	
	
	/**
	 * <p></p>
	 * 
	 * @since JDK 1.8
	 * @version 2016.02.23_0
	 * @author Johannes B. Latzel
	 */
	private class BinaryNode {
		
		
		/**
		 * <p></p>
		 */
		private final T value;
		
		
		/**
		 * <p></p>
		 */
		private BinaryNode left;

		
		/**
		 * <p></p>
		 */
		private BinaryNode right;
		
		
		/**
		 * <p></p>
		 *
		 * @param
		 * @return
		 */
		public BinaryNode(T value) {
			this.value = ArgumentChecker.checkForNull(value, "value");
			left = right = null;
		}
		
		
		/**
		 * <p></p>
		 *
		 * @param
		 * @return
		 */
		public void setLeft(T t) {
			if( left != null ) {
				throw new IllegalArgumentException("The left is not null!");
			}
			this.left = new BinaryNode(t);
		}
		
		
		/**
		 * <p></p>
		 *
		 * @param
		 * @return
		 */
		public void setRight(T t) {
			if( right != null ) {
				throw new IllegalArgumentException("The right is not null!");
			}
			this.right = new BinaryNode(t);
		}
		
		
		/**
		 * <p></p>
		 *
		 * @param
		 * @return
		 */
		public void setLeft(BinaryNode node) {
			if( left != null ) {
				throw new IllegalArgumentException("The right is not null!");
			}
			this.left = node;
		}
		
		
		/**
		 * <p></p>
		 *
		 * @param
		 * @return
		 */
		public void setRight(BinaryNode node) {
			if( right != null ) {
				throw new IllegalArgumentException("The right is not null!");
			}
			this.right = node;
		}
		
		
		/**
		 * <p></p>
		 *
		 * @param
		 * @return
		 */
		public boolean hasLeft() {
			return left != null;
		}
		
		
		/**
		 * <p></p>
		 *
		 * @param
		 * @return
		 */
		public boolean hasRight() {
			return right != null;
		}
		
		
		/**
		 * <p></p>
		 *
		 * @param
		 * @return
		 */
		public boolean hasChildren() {
			return hasLeft() || hasRight();
		}
		
		
		/**
		 * <p></p>
		 *
		 * @param
		 * @return
		 */
		public BinaryNode getLeft() {
			return left;
		}
		
		
		/**
		 * <p></p>
		 *
		 * @param
		 * @return
		 */
		public BinaryNode getRight() {
			return right;
		}
		
		
		/**
		 * <p></p>
		 *
		 * @param
		 * @return
		 */
		public T getValue() {
			return value;
		}
		
		
		/**
		 * <p></p>
		 *
		 * @param
		 * @return
		 */
		public void removeLeft() {
			left = null;
		}
		
		
		/**
		 * <p></p>
		 *
		 * @param
		 * @return
		 */
		public void removeRight() {
			right = null;
		}
		
		
	}
	
	
	private enum Direction {
		Left,
		Stay,
		Right;
	}
	
}
