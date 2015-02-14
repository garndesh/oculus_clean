package garndesh.oculus.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@SuppressWarnings({"rawtypes","unchecked"})
public class NamedList<E> {

	private List list;
	private HashMap<String, Integer> names;
	
	public NamedList(){
		list = new ArrayList<E>();
		names = new HashMap<String, Integer>();
	}
	
	public NamedList(int capacity){
		list = new ArrayList<E>(capacity);
		names = new HashMap<String, Integer>((int) (capacity*1.5));
	}
	
	
	public void addToList(String name, E obj){
		if(!names.containsKey(name)){
			names.put(name, list.size());
			list.add(obj);
		} else {
			list.add(names.get(name), obj);
		}
	}
	
	public int getIndexOf(String name){
		return names.get(name);
	}
	
	public E getObject(int index){
		return (E) list.get(index);
	}
	
	public Object getObject(String name){
		if(names.containsKey(name)){
			return list.get(names.get(name));
		}
		return null;
	}
	
	public void addToList(int index, String name, Object obj){
		names.put(name, index);
		list.add(index, obj);
	}
	
	public void removeFromList(String name){
		if(names.containsKey(name)){
			list.set(names.get(name), null);
			names.remove(name);
		}
	}
	
	public void removeFromList(int index){
		list.set(index, null);
	}
}
