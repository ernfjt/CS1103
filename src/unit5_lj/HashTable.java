package unit5_lj;

/**
 * @author fujita
 *
 */
public class HashTable {

	public class Item {
		public String key;
		public String value;

		/**
		 * creating constructor
		 * 
		 * @param key
		 * @param value
		 */
		public Item(String key, String value) {
			this.key = key;
			this.value = value;
		}
	}

	private static int HASH_CAPACITY = 10; // HashTable site
	private Item[] data = new Item[HASH_CAPACITY];
	private int size = 0;

	/**
	 * Compute a hash code for the key; key cannot be null. 
	 * 
	 * @param key
	 * @return
	 */
	private int getHash(String key) {
		return (Math.abs(key.hashCode())) % HASH_CAPACITY;
	}

	/**
	 * Retrieve the value associated with the specified key in the table, if there
	 * is any. If not, the value null will be returned.
	 * 
	 * @param key The key whose associated value we want to find
	 * @return the associated value, or null if there is no associated value
	 */
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

	/**
	 * Associate the specified value with the specified key.
	 * 
	 * @param key
	 * @param value
	 */
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

	/**
	 * Remove the key and its associated value from the table, if the key occurs in
	 * the table. If it does not occur, then nothing is done.
	 * 
	 * @param key
	 * @return
	 */
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

	/**
	 * Return the number of key/value pairs in the table.
	 * @return
	 */
	public int size() {
		return size;
	}

	/**
	 * Test whether the specified key has an associated value in the table.
	 * @param key The key that we want to search for.
	 * @return true if the key exists in the table, false if not
	 */
	public boolean containsKey(String key) {
		int hash = getHash(key);
		while (data[hash] != null && data[hash].key.equals(key)) {
			return true;
		}
		return false;
	}

	/**
	 * @param argu
	 */
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
