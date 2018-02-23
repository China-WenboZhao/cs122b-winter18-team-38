package Entity;

import java.util.LinkedList;

public class Table {

	public Table(String name) {
		// TODO Auto-generated constructor stub
		tablename = name;
		Field = new LinkedList<String>();
		Type = new LinkedList<String>();
		Null = new LinkedList<String>();
		Key = new LinkedList<String>();
		Default = new LinkedList<String>();
		Extra = new LinkedList<String>();
	}


	public LinkedList<String> Field = new LinkedList<String>();
	int num_Field=0;
	public LinkedList<String> Type;
	public LinkedList<String> Null;
	public LinkedList<String> Key;
	public LinkedList<String> Default;
	public LinkedList<String> Extra;
	public String tablename;

	public String gettablename() {
		return tablename;
	}

	public void settablename(String name) {
		tablename=name;
	}

	public void setField(String s) {
		Field.add(s);
	}
	
	public LinkedList<String> getField() {
		return Field;
	}
	
	public int getnum_field() {
		return num_Field;
	}

	public void setType(String s) {
		Type.add(s);
	}
	
	public LinkedList<String> getType() {
		return Type;
	}
	
	public void setNull(String s) {
		Null.add(s);
	}
	
	public LinkedList<String> getNull() {
		return Null;
	}
	
	public void setKey(String s) {
		Key.add(s);
	}
	
	public LinkedList<String> getKey() {
		return Key;
	}
	
	public void setDefault(String s) {
		Default.add(s);
	}
	
	public LinkedList<String> getDefault() {
		return Default;
	}
	
	public void setExtra(String s) {
		Extra.add(s);
	}
	
	public LinkedList<String> getExtra() {
		return Extra;
	}
	
}
