import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;


public class JavaTest {

	static String password;
	static String name;
	static int balance = 0;
	static int total_product = 0;
	
	static ArrayList<Integer> quantity = new ArrayList<>();
	static ArrayList<String> cart = new ArrayList<>();
	
	static ArrayList<String> uuidarr = new ArrayList<>();
	static ArrayList<String> productarr = new ArrayList<>();
	static ArrayList<Integer> pricearr = new ArrayList<>();
	static ArrayList<String> typearr = new ArrayList<>();
	
	public JavaTest() {
		Scanner scan = new Scanner(System.in);
		int input;
		do {
			clearScreen();
			System.out.println("H&Mc");
			System.out.println("1. Login");
			System.out.println("2. Register");
			System.out.println("3. Exit");
			
			input = scan.nextInt();
			scan.nextLine();
			switch(input) {
			case 1:
				loginInt();
				break;
			case 2:
				registerInt();
				break;
			}
		}while(input !=3);
	}
	
	public static void clearScreen() {
		for(int i = 0; i<30; i++) {
			System.out.println("");
		}
	}
	
	public static void registerInt() {
		Scanner scan = new Scanner(System.in);
		
		do {
			System.out.print("Input username [> 5 char] : ");
			name = scan.nextLine();
		}while(name.length()<=5);
		
		int validate_name1, validate_name2, validate_name3;
		do {
			System.out.print("Input password alphanumeric && > 5 char : ");
			
			password = scan.nextLine();
			validate_name1 = 0;
			validate_name2 = 0;
			validate_name3 = 0;
			for (int i = 0; i < password.length(); i++){
	            if(Character.isLetter(password.charAt(i))==true) {
	            	validate_name1 = 1;
	            }
	            if(Character.isDigit(password.charAt(i))==true) {
	            	validate_name2 = 1;
	            }
	        }
			if(password.length()>=3 && password.length()<=10) {
				validate_name3 = 1;
			}
		}while(validate_name1 != 1 || validate_name2 != 1 || validate_name3 != 1);
		UUID uuid=UUID.randomUUID();
		
		File fp = new File("customer.txt");
		try {
			fp.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter("customer.txt", true);
			bw = new BufferedWriter(fw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bw.write(uuid + "," + name +","+ password + ","+"Customer"+","+balance+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		home();
		
	}
	
	public static void addItem() {
		
	}
	
	public static void loginInt() {
		Scanner scan = new Scanner(System.in);
		do {
			System.out.print("Input username [> 5 char] : ");
			name = scan.nextLine();
		}while(name.length()<=5);
		
		int validate_name1, validate_name2, validate_name3;
		do {
			System.out.print("Input password alphanumeric && > 5 char : ");
			
			password = scan.nextLine();
			validate_name1 = 0;
			validate_name2 = 0;
			validate_name3 = 0;
			for (int i = 0; i < password.length(); i++){
	            if(Character.isLetter(password.charAt(i))==true) {
	            	validate_name1 = 1;
	            }
	            if(Character.isDigit(password.charAt(i))==true) {
	            	validate_name2 = 1;
	            }
	        }
			if(password.length()>=3 && password.length()<=10) {
				validate_name3 = 1;
			}
		}while(validate_name1 != 1 || validate_name2 != 1 || validate_name3 != 1);
		File fw = new File("customer.txt");
		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(fw);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(fileScanner.hasNextLine()) {
			String input = fileScanner.nextLine();
			String data[] = input.split(",");
			if(data[1].equals(name) && data[2].equals(password) && data[3].equals("Customer")) {
				home();
				scan.nextLine();
			}else if(data[1].equals(name) && data[2].equals(password) && data[3].equals("Admin")) {
				home2();
				scan.nextLine();
			}
		}
		
		
	}
	
	
	public static void topup() {
		Scanner scan = new Scanner(System.in);
		int input;
		System.out.print("Input amount you want to topup (press 0 to go back) : ");
		input = scan.nextInt(); scan.nextLine();
		
		if(input != 0) {
			balance += input;
		}
	}
	
	public static void insertProduct1() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Input product name [5 char]: ");
		String product;
		product = scan.nextLine();
		
		int price;
		System.out.println("Input price : ");
		price = scan.nextInt(); scan.nextLine();
		
		UUID uuid=UUID.randomUUID();
		
		System.out.println("Input product type [5 char]: ");
		String type;
		type = scan.nextLine();
		
		uuidarr.add(uuid.toString());
		productarr.add(product);
		pricearr.add(price);
		typearr.add(type);
		total_product++;
	}
	
	public static void viewProduct() {
		if(total_product == 0) {
			System.out.println("no data");
		}else {
			for(int i = 0; i<total_product; i++) {
				System.out.printf("%s, %s, %d, %s\n", uuidarr.get(i), productarr.get(i), pricearr.get(i), typearr.get(i));
			}
		}
	}
	
	public static void home() {
		Scanner scan = new Scanner(System.in);
		int input;
		do {
			System.out.println("Welcome, " + name);
			System.out.println("Your balance, " + balance);
			System.out.println("");

			System.out.println("1. Add new item to cart");
			System.out.println("2. View cart content");
			System.out.println("3. Remove cart content");
			System.out.println("4. Update cart content");
			System.out.println("5. Check out cart");
			System.out.println("6. Top up balance");
			System.out.println("7. Logout");
			

			System.out.print(">> ");
			input = scan.nextInt();
			scan.nextLine();
			
			switch(input) {
			case 1:
				addItem();
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				topup();
				break;
			case 7:
				break;
			}
			
		}while(input != 7);
	}
	
	public static void deleteProduct() {
		viewProduct();
		Scanner scan = new Scanner(System.in);
		int index = scan.nextInt(); scan.nextLine();
		
		total_product--;
		uuidarr.remove(index-1);
		productarr.remove(index-1);
		pricearr.remove(index-1);
		typearr.remove(index-1);
	}
	
	public static void writeFile() {
		File fp = new File("cart.txt");
		try {
			fp.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter("cart.txt", true);
			bw = new BufferedWriter(fw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			for(int i = 0; i<total_product; i++) {
				bw.write(uuidarr.get(i) + "," + productarr.get(i) + "," + pricearr.get(i) + "," + typearr.get(i) + "\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	public static void home2() {
		Scanner scan = new Scanner(System.in);
		int input;
		do {
			System.out.println("Welcome, " + name);

			System.out.println("1. Insert a new product");
			System.out.println("2. View all product");
			System.out.println("3. Delete a product");
			System.out.println("4. Logout");
			

			System.out.print(">> ");
			input = scan.nextInt();
			scan.nextLine();
			
			switch(input) {
			case 1:
				insertProduct1();
				break;
			case 2:
				viewProduct();
				break;
			case 3:
				deleteProduct();
				break;
			}
			
		}while(input != 4);
		writeFile();
	}
	
	
	private static void insertProduct() {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		new JavaTest();	
	}
}
