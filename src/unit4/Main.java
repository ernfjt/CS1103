package unit4;

public class Main {

	public static void main(String[] args) {
		
		Integer A = Integer.valueOf(150);
		Integer B = Integer.valueOf(150);
		Integer C = Integer.valueOf(130);
		
		System.out.println("A:"+A);
		System.out.println("B:"+B);
		System.out.println("C:"+C+"\n");
		System.out.println("Evaluate using == operator");
		System.out.print("A and B is ");
		System.out.println(A == B ? "same." : "not same.");
		System.out.print("A and C is ");
		System.out.println(A == C ? "same.":"not same.\n");

		System.out.println("Evaluate using equals method");
		System.out.print("A and B is ");
		System.out.println(A.equals(B) ? "same." : "not same.");
		System.out.print("A and C is ");
		System.out.println(A.equals(C) ? "same." : "not same.\n");
		
		System.out.println("Evaluate using compareTo method");
		System.out.print("A and B is ");
		System.out.println(A.compareTo(B) == 0 ? "same." : "not same.");
		System.out.print("A and C is ");
		System.out.println(A.compareTo(C) == 0 ? "same." : "not same.");
	}
}
