package unit5_lj;

public class HashTable {

	public class Item {
		public String key;
		public String value;

		public Item(String key, String value) {
			this.key = key;
			this.value = value;
		}
	}

	private static int HASH_CAPACITY = 10;
	private Item[] data = new Item[HASH_CAPACITY];
	private int size = 0;

	private int getHash(String key) {
		return (Math.abs(key.hashCode())) % HASH_CAPACITY;
	}

	public String get(String key) {
		if (key != null) {
			int hash = getHash(key);
			while (data[hash] != null && !data[hash].key.equals(key)) {
				hash = (hash + 1) % HASH_CAPACITY;
			}
			return data[hash] != null ? data[hash].value : null;
		}
		return null;
	}

	public void put(String key, String value) {
		if (key != null) {
			int hash = getHash(key);
			while (data[hash] != null && !data[hash].key.equals(key)) {
				hash = (hash + 1) % HASH_CAPACITY;
			}
			data[hash] = new Item(key, value);
			size++;
		}
	}

	public Object remove(String key) {
		Object removed = null;
		if (key != null) {
			int hash = getHash(key);
			while (data[hash] != null && !data[hash].key.equals(key)) {
				hash = (hash + 1) % HASH_CAPACITY;
			}
			if (data[hash] != null) {
				removed = data[hash];
				data[hash] = null;
				size--;
			}
		}
		return removed;
	}

	public int size() {
		return size;
	}

	public boolean containsKey(String key) {
		int hash = getHash(key);
		while (data[hash] != null && data[hash].key.equals(key)) {
			return true;
		}
		return false;
	}
	
	public static void main(String argu[]) {
		HashTable hashTable = new HashTable();

		hashTable.put("Tokyo", "Japan");
		hashTable.put("Seoul", "Korea");
		hashTable.put("Beijing", "China");
		hashTable.put("Paris", "France");
		hashTable.put("Washington", "USA");
		hashTable.put("Brazilia", "Brazil");
		System.out.println("Size: " + hashTable.size());

		System.out.println((String) hashTable.get("Tokyo"));
		System.out.println((String) hashTable.get("Seoul"));
		System.out.println((String) hashTable.get("Beijing"));
		System.out.println((String) hashTable.get("Paris"));
		System.out.println((String) hashTable.get("Washington"));
		System.out.println((String) hashTable.get("Brazilia"));

		hashTable.remove("Brazilia");
		System.out.println("Size: " + hashTable.size());
		System.out.println((String) hashTable.get("Brazilia"));
		System.out.println(hashTable.containsKey("Seoul"));
	}

}
