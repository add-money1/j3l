package j3l.util;

import java.util.Comparator;

import j3l.GlobalString;

/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2016.06.21_0
 * @author Johannes B. Latzel
 */
public final class LongRange implements IValidate {
	
	
	/**
	 * <p></p>
	 */
	public final static Comparator<LongRange> BY_BEGIN_COMPARATOR = (l, r) -> Long.compare(l.getBegin(), r.getBegin());
	
	
	/**
	 * <p></p>
	 */
	public final static Comparator<LongRange> BY_END_COMPARATOR = (l, r) -> Long.compare(l.getEnd(), r.getEnd());
	
	
	/**
	 * <p></p>
	 */
	public final static Comparator<LongRange> BY_RANGE_COMPARATOR = (l, r) -> Long.compare(l.getRange(), r.getRange());
	
	
	/**
	 * <p></p>
	 */
	private long begin;
	
	
	/**
	 * <p></p>
	 */
	private long end;
	
	
	/**
	 * <p></p>
	 */
	private boolean is_valid;
	
	
	/**
	 * <p></p>
	 * 
	 * @param
	 */
	public LongRange(long element) {
		this(element, element);
	}
	
	
	/**
	 * <p></p>
	 * 
	 * @param
	 */
	public LongRange(long begin, long end) {
		this.begin = begin;
		this.end = end;
		is_valid = begin <= end;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public long getBegin() {
		return begin;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public long getEnd() {
		return end;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public long getRange() {
		return end - begin;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public void invalidate() {
		is_valid = false;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public long reduceEnd() {
		ArgumentChecker.checkForValidation(this, GlobalString.LongRange.toString());
		// in case of underflow
		if( end - 1 > end ) {
			is_valid = false;
			return end;
		}
		return end--;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public long increaseBegin() {
		ArgumentChecker.checkForValidation(this, GlobalString.LongRange.toString());
		// in case of overflow
		if( begin + 1 < begin ) {
			is_valid = false;
			return begin;
		}
		return begin++;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public void add(long element) {
		if( elementIsAddableAtBegin(element) ) {
			begin = element;
		}
		else if( elementIsAddableAtEnd(element) ) {
			end = element;
		}
		else {
			if( isInRange(element) ) {
				throw new IllegalArgumentException("The element is already in the range!");
			}
			throw new IllegalArgumentException("The element \"" + element + "\" can not be added to the range!");
		}
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public boolean contains(long element) {
		ArgumentChecker.checkForValidation(this, GlobalString.LongRange.toString());
		return element >= begin && element <= end;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public boolean contains(LongRange range) {
		ArgumentChecker.checkForValidation(range, GlobalString.Range.toString());
		ArgumentChecker.checkForValidation(this, GlobalString.LongRange.toString());
		return range.begin >= begin && range.end <= end;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public boolean elementIsAddable(long element) {
		ArgumentChecker.checkForValidation(this, GlobalString.LongRange.toString());
		return elementIsAddableAtBegin(element) || elementIsAddableAtEnd(element);
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public boolean lapsOverBegin(LongRange range) {
		return !contains(range) && !range.contains(this) && range.begin <= begin && range.end >= begin;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public boolean lapsOverEnd(LongRange range) {
		return !contains(range) && !range.contains(this) && range.begin <= end && range.end >= end;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public boolean isAddable(LongRange range) {
		if( contains(range) ) {
			throw new IllegalArgumentException("The long_range already contains the range \"" + range + "\"!");
		}
		return range.contains(this)
				|| lapsOverBegin(range)
				|| lapsOverEnd(range);
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public boolean elementIsAddableAtBegin(long element) {
		ArgumentChecker.checkForValidation(this, GlobalString.LongRange.toString());
		return element == begin - 1;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public boolean elementIsAddableAtEnd(long element) {
		ArgumentChecker.checkForValidation(this, GlobalString.LongRange.toString());
		return element == end + 1;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public void add(LongRange range) {
		if( !isAddable(range) ) {
			throw new IllegalArgumentException("Can not add the range \"" + range + "\"!");
		}
		begin = Math.min(begin, range.begin);
		end = Math.max(end, range.end);
		range.invalidate();
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public boolean isInRange(long element) {
		return element >= begin && element <= end;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override public boolean equals(Object object) {
		if( object instanceof LongRange ) {
			LongRange range = (LongRange)object;
			return range.begin == begin && range.end == end;
		}
		return false;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override public int hashCode() {
		return Long.hashCode(begin ^ Long.rotateLeft(end, 32));
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override public String toString() {
		StringBuilder string_builder = new StringBuilder(60);
		string_builder.append("LongRange[");
		string_builder.append(begin);
		string_builder.append('|');
		string_builder.append(end);
		string_builder.append(']');
		return string_builder.toString();
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see j3l.util.check.IValidate#isValid()
	 */
	@Override public boolean isValid() {
		return is_valid && getRange() >= 0;
	}
	
}
