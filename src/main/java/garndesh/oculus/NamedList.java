package garndesh.oculus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@SuppressWarnings({"rawtypes","unchecked"})
public class NamedList {

	private List list = new ArrayList();
	private HashMap<String, Integer> names = new HashMap<String, Integer>();
	
	public void addToList(String name, Object obj){
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
	
	public Object getObject(int index){
		return list.get(index);
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
