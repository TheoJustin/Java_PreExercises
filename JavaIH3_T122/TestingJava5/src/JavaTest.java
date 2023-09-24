import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class JavaTest {
	
	static ArrayList<String> username_arr = new ArrayList<>();
	static ArrayList<String> password_arr = new ArrayList<>();
	
	static ArrayList<String> foodname_arr = new ArrayList<>();
	static ArrayList<String> foodtype_arr = new ArrayList<>();
	static ArrayList<Integer> foodprice_arr = new ArrayList<>();
	static ArrayList<Integer> foodquantity_arr = new ArrayList<>();
	
	static ArrayList<String> cartname_arr = new ArrayList<>();
	static ArrayList<String> carttype_arr = new ArrayList<>();
	static ArrayList<Integer> cartprice_arr = new ArrayList<>();
	static ArrayList<Integer> cartquantity_arr = new ArrayList<>();
	static int totalfood = 0;
	static int totalcart = 0;
	
	public static void registerInt() {
		Scanner scan = new Scanner(System.in);
		String username;
		String password;
		System.out.println("username : ");
		username = scan.nextLine();
		System.out.println("password : ");
		password = scan.nextLine();
		
		File file = new File("credentials.txt");
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			FileWriter fileWriter = new FileWriter("credentials.txt", true);
			fileWriter.write(username + "#" + password + "\n");
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void loginInt() {
		Scanner scan = new Scanner(System.in);
		String username;
		String password;
		System.out.println("username : ");
		username = scan.nextLine();
		System.out.println("password : ");
		password = scan.nextLine();
		
		File file = new File("credentials.txt");
		String oneLine;
		try {
			Scanner myFile = new Scanner(file);
			while(myFile.hasNextLine()) {
				oneLine = myFile.nextLine();
				String data[] = oneLine.split("#");
				username_arr.add(data[0]);
				password_arr.add(data[1]);
			}
			myFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i<username_arr.size(); i++) {
			if(username_arr.get(i).equals(username)) {
				if(password_arr.get(i).equals(password)) {
					home(username);
				}
			}
		}
		if(username.equals("admin")) {
			if(password.equals("admin")) {
				adminhome(username);
			}
		}
	}
	
	public static void remove_cart() {
		System.out.println("remove cart");
		for(int i = 0; i<totalcart; i++) {
			System.out.printf("%d, %s, %s, %d, %d\n", i+1, cartname_arr.get(i), carttype_arr.get(i), cartprice_arr.get(i), cartquantity_arr.get(i));
		}
		
		System.out.printf("Choose product [%d - %d]: ", 1, totalcart);
		int input, quantity;
		Scanner scan = new Scanner(System.in);
		input = scan.nextInt(); scan.nextLine();
		System.out.printf("Quantity [%d - %d]: ", 1, cartquantity_arr.get(input-1));
		quantity = scan.nextInt(); scan.nextLine();
		
		if(foodname_arr.contains(cartname_arr.get(input-1))) {
			for(int i = 0; i<totalfood; i++) {
				if(foodname_arr.get(i) == cartname_arr.get(input-1)) {
					foodquantity_arr.set(i, foodquantity_arr.get(i) + cartquantity_arr.get(input-1));
					
					cartname_arr.remove(input-1);
					carttype_arr.remove(input-1);
					cartquantity_arr.remove(input-1);
					cartprice_arr.remove(input-1);
					totalcart--;
					break;
				}
			}
		}else {
			foodname_arr.add(cartname_arr.get(input-1));
			foodname_arr.add(carttype_arr.get(input-1));
			foodquantity_arr.add(cartquantity_arr.get(input-1));
			foodprice_arr.add(cartprice_arr.get(input-1));
			
			cartname_arr.remove(input-1);
			carttype_arr.remove(input-1);
			cartquantity_arr.remove(input-1);
			cartprice_arr.remove(input-1);
			totalcart--;
		}
		
	}
	
	public static void cart() {
		Scanner scan = new Scanner(System.in);
		int input;
		do {
			if(totalcart == 0) {
				input = 3;
			}else {
				System.out.println("cart");
				for(int i = 0; i<totalcart; i++) {
					System.out.printf("%d, %s, %s, %d, %d\n", i+1, cartname_arr.get(i), carttype_arr.get(i), cartprice_arr.get(i), cartquantity_arr.get(i));
				}
				
				System.out.println("1. Remove");
				System.out.println("2. Checkout");
				System.out.println("3. Back");
				input = scan.nextInt(); scan.nextLine();
				switch(input) {
				case 1:
					remove_cart();
					break;
				case 2:
					checkout_cart();
					break;
				}
			}
		}while(input != 3);
	}
	
	public static void checkout_cart() {
		Scanner scan = new Scanner(System.in);
		System.out.println("cart");
		int totalprice = 0;
		for(int i = 0; i<totalcart; i++) {
			System.out.printf("%d, %s, %s, %d, %d\n", i+1, cartname_arr.get(i), carttype_arr.get(i), cartprice_arr.get(i), cartquantity_arr.get(i));
			totalprice += (cartquantity_arr.get(i)*cartprice_arr.get(i));
		}
		System.out.print("Total : " + totalprice);
		System.out.println("Pay [more than total price]: ");
		int totalpay = scan.nextInt(); scan.nextLine();
		
		if(totalpay >= totalprice) {
			cartname_arr.removeAll(cartname_arr);
			cartquantity_arr.removeAll(cartquantity_arr);
			cartprice_arr.removeAll(cartprice_arr);
			carttype_arr.removeAll(carttype_arr);
			
			totalcart = 0;
			System.out.println("Success!");
			System.out.println("Your change : " + (totalpay-totalprice));
		}else {
			System.out.println("not enough money");
		}
	}
	
	public static void healthy() {
		int rand;
		do {
			rand = (int) (Math.random()*4000);
		}while(rand<1500);
		try {
			Thread.sleep(rand);
			System.out.println("Generating.");
			Thread.sleep(rand);
			System.out.println("Generating..");
			Thread.sleep(rand);
			System.out.println("Generating...");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int totalVegetable = 0;
		int totalFruit = 0;
		int totalProtein = 0;
		int totalCarbohydrat = 0;
		
		int dataVegetable[] = new int[100];
		int dataFruit[] = new int[100];
		int dataProtein[] = new int[100];
		int dataCarbohydrat[] = new int[100];
		for(int i = 0; i<totalfood;i++) {
			if(foodtype_arr.get(i).equalsIgnoreCase("vegetable")) {
				dataVegetable[totalVegetable] = i;
				totalVegetable++;
			}
			if(foodtype_arr.get(i).equalsIgnoreCase("fruit")) {
				dataFruit[totalFruit] = i;
				totalFruit++;
			}
			if(foodtype_arr.get(i).equalsIgnoreCase("protein")) {
				dataProtein[totalProtein] = i;
				totalProtein++;
			}
			if(foodtype_arr.get(i).equalsIgnoreCase("carbohydrat")) {
				dataCarbohydrat[totalCarbohydrat] = i;
				totalCarbohydrat++;
			}
		}
		
		if(totalProtein > 0 && totalFruit > 1 && totalVegetable > 1 && totalCarbohydrat > 0) {
			int a = (int) (Math.random()*totalVegetable);
			int a1;
			do {
				a1 = (int) (Math.random()*totalVegetable);
			}while(a1 == a);
			int b = (int) (Math.random()*totalFruit);
			int b1 = -1;
			do {
				b1 = (int) (Math.random()*totalFruit);
			}while(b1 == b);
			int c = (int) (Math.random()*totalProtein);
			int d = (int) (Math.random()*totalCarbohydrat);
			
			foodquantity_arr.set(dataVegetable[a], foodquantity_arr.get(dataVegetable[a]-1));
			foodquantity_arr.set(dataVegetable[a1], foodquantity_arr.get(dataVegetable[a1]-1));
			foodquantity_arr.set(dataFruit[b], foodquantity_arr.get(dataFruit[b]-1));
			foodquantity_arr.set(dataFruit[b1], foodquantity_arr.get(dataFruit[b1]-1));
			foodquantity_arr.set(dataProtein[c], foodquantity_arr.get(dataProtein[c]-1));
			foodquantity_arr.set(dataCarbohydrat[d], foodquantity_arr.get(dataCarbohydrat[d]-1));
			
			for(int i = 0; i<totalfood; i++) {
				if(foodquantity_arr.get(i) == 0) {
					foodname_arr.remove(i);
					foodtype_arr.remove(i);
					foodquantity_arr.remove(i);
					foodprice_arr.remove(i);
					totalfood--;
				}
			}
			
			System.out.println("Vegetable : ");
			System.out.println(foodname_arr.get(dataVegetable[a]));
			System.out.println(foodname_arr.get(dataVegetable[a1]));
			System.out.println("Fruit : ");
			System.out.println(foodname_arr.get(dataFruit[b]));
			System.out.println(foodname_arr.get(dataFruit[b1]));
			System.out.println("Protein : ");
			System.out.println(foodname_arr.get(dataProtein[c]));
			System.out.println("Carbohydrat : ");
			System.out.println(foodname_arr.get(dataCarbohydrat[d]));
			
			cartname_arr.add(foodname_arr.get(dataVegetable[a]));
			carttype_arr.add(foodtype_arr.get(dataVegetable[a]));
			cartprice_arr.add(foodprice_arr.get(dataVegetable[a]));
			cartquantity_arr.add(1);
			
			cartname_arr.add(foodname_arr.get(dataVegetable[a1]));
			carttype_arr.add(foodtype_arr.get(dataVegetable[a1]));
			cartprice_arr.add(foodprice_arr.get(dataVegetable[a1]));
			cartquantity_arr.add(1);
			
			cartname_arr.add(foodname_arr.get(dataFruit[b]));
			carttype_arr.add(foodtype_arr.get(dataFruit[b]));
			cartprice_arr.add(foodprice_arr.get(dataFruit[b]));
			cartquantity_arr.add(1);
			
			cartname_arr.add(foodname_arr.get(dataFruit[b1]));
			carttype_arr.add(foodtype_arr.get(dataFruit[b1]));
			cartprice_arr.add(foodprice_arr.get(dataFruit[b1]));
			cartquantity_arr.add(1);
			
			cartname_arr.add(foodname_arr.get(dataProtein[c]));
			carttype_arr.add(foodtype_arr.get(dataProtein[c]));
			cartprice_arr.add(foodprice_arr.get(dataProtein[c]));
			cartquantity_arr.add(1);
			
			cartname_arr.add(foodname_arr.get(dataCarbohydrat[d]));
			carttype_arr.add(foodtype_arr.get(dataCarbohydrat[d]));
			cartprice_arr.add(foodprice_arr.get(dataCarbohydrat[d]));
			cartquantity_arr.add(1);
			totalcart+=6;

			for(int i = 0; i<totalcart; i++) {
				System.out.printf("%d, %s, %s, %d, %d\n", i+1, cartname_arr.get(i), carttype_arr.get(i), cartprice_arr.get(i), cartquantity_arr.get(i));
			}
		}else {
			System.out.println("not enough data");
		}
	}
	
	public static void home(String username) {
		int input;
		File file = new File("foodmaterial.txt");
		String oneLine;
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Scanner myFile = new Scanner(file);
			while(myFile.hasNextLine()) {
				oneLine = myFile.nextLine();
//				System.out.println(oneLine);
				String data[] = oneLine.split("#");
				totalfood++;
				foodname_arr.add(data[0]);
				foodtype_arr.add(data[1]);
				foodprice_arr.add(Integer.parseInt(data[2]));
				foodquantity_arr.add(Integer.parseInt(data[3]));
			}
			myFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner scan = new Scanner(System.in);
		do {
			System.out.println("Welcome, " + username);
			System.out.println("1. Shop");
			System.out.println("2. Healthy Recommendation");
			System.out.println("3. Cart");
			System.out.println("4. Exit");
			System.out.println(">> ");	
			input = scan.nextInt();scan.nextLine();
			
			switch(input) {
			case 1:
				shop();
				break;
			case 2:
				healthy();
				break;
			case 3:
				cart();
				break;
			}
		}while(input!=4);
		
	}
	
	public static void shop() {
		if(totalfood == 0) {
			System.out.println("no data");
		}else {
			for(int i = 0; i<totalfood; i++) {
				System.out.printf("%d, %s, %s, %d, %d\n", i+1, foodname_arr.get(i), foodtype_arr.get(i), foodprice_arr.get(i), foodquantity_arr.get(i));
			}
			
			System.out.printf("Choose product [%d - %d]: ", 1, totalfood);
			int input, quantity;
			Scanner scan = new Scanner(System.in);
			input = scan.nextInt(); scan.nextLine();
			System.out.printf("Quantity [%d - %d]: ", 1, foodquantity_arr.get(input-1));
			quantity = scan.nextInt(); scan.nextLine();
			
			if(foodquantity_arr.get(input-1) > quantity) {
				foodquantity_arr.set(input-1, foodquantity_arr.get(input-1) - 1);
				
				cartname_arr.add(foodname_arr.get(input-1));
				carttype_arr.add(foodtype_arr.get(input-1));
				cartprice_arr.add(foodprice_arr.get(input-1));
				cartquantity_arr.add(quantity);
				
				totalcart++;
				System.out.println("product added to cart!");
				
			}else if(foodquantity_arr.get(input-1) < quantity) {
				System.out.println("Out of stock");
			}else if(foodquantity_arr.get(input-1) == quantity) {
				cartname_arr.add(foodname_arr.get(input-1));
				carttype_arr.add(foodtype_arr.get(input-1));
				cartprice_arr.add(foodprice_arr.get(input-1));
				cartquantity_arr.add(quantity);
				
				foodname_arr.remove(input-1);
				foodtype_arr.remove(input-1);
				foodquantity_arr.remove(input-1);
				foodprice_arr.remove(input-1);
				
				totalcart++;
				System.out.println("product added to cart!");
			}
		}
		
	}
	
	public static void adminhome(String username) {
		int input;
		File file = new File("foodmaterial.txt");
		String oneLine;
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Scanner myFile = new Scanner(file);
			while(myFile.hasNextLine()) {
				oneLine = myFile.nextLine();
//				System.out.println(oneLine);
				
				String data[] = oneLine.split("#");
				totalfood++;
				foodname_arr.add(data[0]);
				foodtype_arr.add(data[1]);
				foodprice_arr.add(Integer.parseInt(data[2]));
				foodquantity_arr.add(Integer.parseInt(data[3]));
			}
			myFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner scan = new Scanner(System.in);
		do {
			System.out.println("Welcome, " + username);
			System.out.println("1. Insert new product");
			System.out.println("2. Update product");
			System.out.println("3. Delete product");
			System.out.println("4. Exit");
			System.out.println(">> ");	
			input = scan.nextInt();scan.nextLine();
			
			switch(input) {
			case 1:
				insertProduct();
				break;
			case 2:
				updateProduct();
				break;
			case 3:
				deleteProduct();
				break;
			}
			
		}while(input!=4);
		
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			FileWriter fileWriter = new FileWriter("foodmaterial.txt");
			for(int i = 0; i<totalfood; i++) {
				fileWriter.write(foodname_arr.get(i)+"#"+foodtype_arr.get(i)+"#"+foodprice_arr.get(i)+"#"+foodquantity_arr.get(i)+"\n");
			}
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void insertProduct() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Current Product");
		System.out.println("| No | Food Material Name   | Food Type     | Food Price | Food Quantity |");
		
		for(int i = 0; i<totalfood; i++) {
			System.out.printf("%d, %s, %s, %d, %d\n", i+1, foodname_arr.get(i), foodtype_arr.get(i), foodprice_arr.get(i), foodquantity_arr.get(i));
		}
		
		System.out.println("Food Name [must not empty and unique]:");
		String foodname = scan.nextLine();
		System.out.println("Food Type [Vegetable | Carbohydrat| Fruit | Protein] [Case Insensitive]:");
		String foodtype = scan.nextLine();
		System.out.println("Price [min. 2000]: ");
		int foodprice = scan.nextInt(); scan.nextLine();
		System.out.println("Quantity [more than 0]: ");
		int foodquantity = scan.nextInt(); scan.nextLine();
		
		foodname_arr.add(foodname);
		foodtype_arr.add(foodtype);
		foodprice_arr.add(foodprice);
		foodquantity_arr.add(foodquantity);
		totalfood++;
		
		System.out.println("Insert success!");
	}
	
	public static void updateProduct() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Current Product");
		System.out.println("| No | Food Material Name   | Food Type     | Food Price | Food Quantity |");
		
		for(int i = 0; i<totalfood; i++) {
			System.out.printf("%d, %s, %s, %d, %d\n", i+1, foodname_arr.get(i), foodtype_arr.get(i), foodprice_arr.get(i), foodquantity_arr.get(i));
		}
		
		if(totalfood>0) {
			System.out.printf("Choose product [%d - %d]:", 1, totalfood);
			int input = scan.nextInt(); scan.nextLine();
			
			System.out.println("Food Name [must not empty and unique]:");
			String foodname = scan.nextLine();
			System.out.println("Food Type [Vegetable | Carbohydrat| Fruit | Protein] [Case Insensitive]:");
			String foodtype = scan.nextLine();
			System.out.println("Price [min. 2000]: ");
			int foodprice = scan.nextInt(); scan.nextLine();
			System.out.println("Quantity [more than 0]: ");
			int foodquantity = scan.nextInt(); scan.nextLine();
			
			foodname_arr.set(input-1, foodname);
			foodtype_arr.set(input-1, foodtype);
			foodprice_arr.set(input-1, foodprice);
			foodquantity_arr.set(input-1, foodquantity);
			
			System.out.println("Product Updated!");
		}else {
			System.out.println("No product");
		}
	}
	
	public static void deleteProduct() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Current Product");
		System.out.println("| No | Food Material Name   | Food Type     | Food Price | Food Quantity |");
		
		for(int i = 0; i<totalfood; i++) {
			System.out.printf("%d, %s, %s, %d, %d\n", i+1, foodname_arr.get(i), foodtype_arr.get(i), foodprice_arr.get(i), foodquantity_arr.get(i));
		}
		
		if(totalfood>0) {
			System.out.printf("Choose product [%d - %d]:", 1, totalfood);
			int input = scan.nextInt(); scan.nextLine();
			
			foodname_arr.remove(input-1);
			foodtype_arr.remove(input-1);
			foodquantity_arr.remove(input-1);
			foodprice_arr.remove(input-1);

			System.out.println("Product Deleted!");
		}else {
			System.out.println("No product");
		}
	}
	
	public JavaTest(){
		Scanner scan = new Scanner(System.in);
		int input;
		do {
			System.out.println("marget");
			System.out.println("1. Login");
			System.out.println("2. Register");
			System.out.println("3. Exit");
			System.out.print(">> ");
			input = scan.nextInt();scan.nextLine();
			
			switch(input) {
			case 1:
				loginInt();
				break;
			case 2:
				registerInt();
				break;
			}
		}while(input != 3);
		
	}
	
	public static void main(String[] args) {
		new JavaTest();
	}
}
