package unit4;

public class check {

	public static void main(String[] args) {
		
		// create two object
		String obj1 = new String("Abenezer");
		String obj2 = new String("Abenezer");
		
		if (obj1.equals(obj2)) {
			System.out.println("Using equals() method: same");
		}else {
			System.out.println("Using equals() method: not same");
		}
		
		if (obj1 == obj2) {
			System.out.println("Using == operator: same");
		}else {
			System.out.println("Using == operator: not same");
		}
	}

}
