// Enhanced Map Utils is free software: you can redistribute it and/or modify
//     it under the terms of the GNU General Public License as published by
//     the Free Software Foundation, either version 3 of the License, or
//     (at your option) any later version.
//
//     Enhanced Map Utils is distributed in the hope that it will be useful,
//     but WITHOUT ANY WARRANTY; without even the implied warranty of
//     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//     GNU General Public License for more details.
//
//     You should have received a copy of the GNU General Public License
//     along with Enhanced Map Utils.  If not, see <https://www.gnu.org/licenses/>.package jge;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Utility methods for adding/removing items to a map in an easier way.
 * 
 * @author Miguel Alejandro Moreno Barrientos, (C) 2020
 */
final public class EnhancedMapUtils 
{
	private EnhancedMapUtils() {}
	
	/**
	 * Create and initialize a map of the specified type.
	 * 
	 * @param <K> key type
	 * @param <V> value type
	 * @param map map to store entries 
	 * @param keyValuePairs array of key/value pairs in the form 
	 *        <i><b>{key1,value1,key2,value2,...}</b></i>. This array must have an 
	 *        <b><i>even</i></b> number of elements
	 * @return the own map
	 * @throws IllegalArgumentException the number of items in <i>keyValuesPairs</i> is 
	 *         <font color=red>odd</font> or chaining from {@link Map#put(Object, Object)}
	 * @throws UnsupportedOperationException {@link Map#put(Object, Object)}
	 * @throws ClassCastException {@link Map#put(Object, Object)}
	 * @throws NullPointerException {@link Map#put(Object, Object)}
	 */
	@SuppressWarnings({ "unchecked" })
	public static <K,V> Map<K,V> putIntoMap( Map<K,V> map, Object... keyValuePairs )
	{
		if ( (keyValuePairs.length&1) == 1 )
			throw new IllegalArgumentException( 
						"The number of items must be even (key/value pairs)" );

		for ( int i = 0; i < keyValuePairs.length; i += 2 )
			map.put( (K) keyValuePairs[i], (V) keyValuePairs[i+1] );
		
		return map;
	}
	
	/**
	 * Removes several entries from a map using a key array.
	 * 
	 * @param <K> key type
	 * @param map map to remove entries
	 * @param keysToRemove keys to remove
	 * @return the own map with removed entries
	 * @throws UnsupportedOperationException {@link Map#remove(Object)}
	 * @throws ClassCastException {@link Map#remove(Object)}
	 * @throws NullPointerException {@link Map#remove(Object)}
	 */
	public static <K> Map<K,?> remove( Map<K,?> map, Object... keysToRemove )
	{
		Arrays.stream( keysToRemove ).forEach( k -> map.remove(k) );

		return map;
	}
	
	/**
	 * Formatted string for a map
	 * 
	 * @param map map to stringify
	 * @param mapPrefix initial prefix
	 * @param firstEntryFormat format for first entry or <i>null</i> for using <b><i>format</i></b>
	 * @param format format for every entry
	 * @param lastEntryFormat format for last entry or <i>null</i> for using <b><i>format</i></b>
	 * @param mapSufix final sufix
	 * @return custom string from this map
	 */
	public static String toString( Map<?,?> map, String mapPrefix, String firstEntryFormat, 
								   String format, String lastEntryFormat, String mapSufix ) 
	{
		final StringBuilder sb = new StringBuilder( mapPrefix );

		int idx = 1;		
		for ( Entry<?,?> entry : map.entrySet() )
		{
			String cformat;
			if ( idx == 1 && firstEntryFormat != null )
				cformat = firstEntryFormat;
			else if ( idx == map.size() && lastEntryFormat != null )
				cformat = lastEntryFormat;
			else
				cformat = format;
			idx++;
			sb.append( String.format( cformat, entry.getKey(), entry.getValue() ) ); 
		}
		
		return sb.append( mapSufix ).toString();
	}
	
	/**
	 * Put a value only to an existing key.
	 * 
	 * @param <K> key type
	 * @param <V> value type
	 * @param map map to update
	 * @param key key to assign value if exists
	 * @param value value to update
	 * @return the old value or null if map doesn't contain key
	 * @throws IllegalArgumentException {@link Map#put(Object, Object)}
	 * @throws UnsupportedOperationException {@link Map#put(Object, Object)}
	 * @throws ClassCastException {@link Map#put(Object, Object)} {@link Map#containsKey(Object)}
	 * @throws NullPointerException {@link Map#put(Object, Object)} {@link Map#containsKey(Object)}
	 */
	public static <K,V> V putIfPresent( Map<K,V> map, K key, V value )
	{
		return map.containsKey(key) ? map.put( key, value ) : null;
	}
	
}  // class EnhancedMapUtils
