import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class JavaTest {

	static ArrayList<String> name_arr = new ArrayList<>();
	static ArrayList<Integer> quantity_arr = new ArrayList<>();
	static int totalData = 0;


	public static void cls() {
		for(int i = 0; i<40; i++) {
			System.out.println(" ");
		}
	}

	public static void loginInt() {
		cls();
		Scanner scan = new Scanner(System.in);

		int validate = 0;
		do {
			String username = scan.nextLine();
			String password = scan.nextLine();
			if(username.equals("admin") && password.equals("admin")) {
				home();
				validate = 1;
			}else {
				System.out.println("Invalid username and password!");
				System.out.println("Press ENTER to continue...");
				scan.nextLine();
			}	
		}while(validate == 0);
	}

	public static void home() {
		Scanner scan = new Scanner(System.in);
		File file = new File("data/inventory.data");
		if(!file.exists()) {
			try {
				System.out.println("File not found");
				System.out.println("Press enter to continue");
				scan.nextLine();
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			String oneLine;
			try {
				Scanner myFile = new Scanner(file);
				while(myFile.hasNextLine()) {
					oneLine = myFile.nextLine();
					String data[] = oneLine.split("#");
					name_arr.add(data[0]);
					quantity_arr.add(Integer.parseInt(data[1]));
					totalData++;
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			home2();
		}
	}

	public static void insertData() {
		Scanner scan = new Scanner(System.in);
		String name;
		do {
			System.out.println("Input Name (5 - 50 characters) :");
			name = scan.nextLine();
		}while(name.length()<5 || name.length()>50);

		int quantity;
		do {
			System.out.println("Input Quantity (more than 0) :");
			quantity = scan.nextInt(); scan.nextLine();
		}while(quantity<0);

		name_arr.add(name);
		quantity_arr.add(quantity);
		totalData++;
		System.out.println("Data added successfully!");
		System.out.println("Press enter to continue");

		try {
			FileWriter fileWriter = new FileWriter("data/inventory.data");
			for(int i = 0; i<totalData; i++) {
				fileWriter.write(name_arr.get(i) + "#" + quantity_arr.get(i) + "\n");
			}
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scan.nextLine();
	}

	public static void deleteData() {
		Scanner scan = new Scanner(System.in);
		System.out.println("no, inventory name, quantity");
		for(int i = 0; i<totalData; i++) {
			System.out.printf("%d, %s, %d\n", (i+1), name_arr.get(i), quantity_arr.get(i));
		}
		System.out.println();
		System.out.println("Choose inventory number to be deleted (0 to exit)");
		System.out.print(">> ");
		int input = scan.nextInt(); scan.nextLine();

		name_arr.remove(input-1);
		quantity_arr.remove(input-1);
		System.out.println("data deleted successfully!");
		System.out.println("press enter to continue");
		scan.nextLine();
		totalData--;
		try {
			FileWriter fileWriter = new FileWriter("data/inventory.data");
			for(int i = 0; i<totalData; i++) {
				fileWriter.write(name_arr.get(i) + "#" + quantity_arr.get(i) + "\n");
			}
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateData() {
		cls();
		Scanner scan = new Scanner(System.in);
		System.out.println("no, inventory name, quantity");
		for(int i = 0; i<totalData; i++) {
			System.out.printf("%d, %s, %d\n", (i+1), name_arr.get(i), quantity_arr.get(i));
		}
		System.out.println("Choose inventory number to be updated (0 to exit):");
		int input = scan.nextInt(); scan.nextLine();

		String name;
		do {
			System.out.print("Input Name (5 - 50 characters, should unique) : ");
			name = scan.nextLine();
		}while(name.length()<5 || name.length()>50);

		int quantity;
		do {
			System.out.println("Input Quantity (more than 0) : ");
			quantity = scan.nextInt(); scan.nextLine();
		}while(quantity < 0);
		name_arr.set(input-1, name);
		quantity_arr.set(input-1, quantity);
		System.out.println("Data updated successfully!");
		System.out.println("Press ENTER to continue...");
		scan.nextLine();

		try {
			FileWriter fileWriter = new FileWriter("data/inventory.data");
			for(int i = 0; i<totalData; i++) {
				fileWriter.write(name_arr.get(i) + "#" + quantity_arr.get(i) + "\n");
			}
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void home2() {
		cls();
		Scanner scan = new Scanner(System.in);
		System.out.println("Jack's Storage");
		System.out.println("no, inventory name, quantity");
		if(totalData == 0) {
			System.out.println("There are no inventory!");
		}
		for(int i = 0; i<totalData; i++) {
			System.out.printf("%d, %s, %d\n", (i+1), name_arr.get(i), quantity_arr.get(i));
		}
		int input = 10;
		do {
			System.out.println("Main Menu\n1. Insert Data\n2. Update Data\n3. Delete Data\n4. Sort\n0. Exit");
			input = scan.nextInt(); scan.nextLine();
			switch(input) {
			case 1:
				insertData();
				break;
			case 2:
				updateData();
				break;
			case 3:
				deleteData();
				break;
			case 4:
				sort();
				break;
			}
		}while(input != 0);

	}

	public JavaTest(){
		Scanner scan = new Scanner(System.in);
		int input;
		do {
			System.out.println("Jack's Storage");
			System.out.println("1. Login");
			System.out.println("2. Exit");
			System.out.print(">> ");
			input = scan.nextInt();scan.nextLine();

			switch(input) {
			case 1:
				loginInt();
				break;
			}
		}while(input != 3);

	}

	public static void sortNameAsc(int left, int right) {
		if(left == right) {
			return;
		}

		int mid = (right - left) / 2 + left;

		sortNameAsc(left, mid);
		sortNameAsc(mid + 1, right);

		ArrayList<String> tempNameArray = new ArrayList<>();
		ArrayList<Integer> tempQuantityArray = new ArrayList<>();
		int leftIndex = left;
		int leftLimit = mid;
		int rightIndex = mid + 1;
		int rightLimit = right;

		while(leftIndex <= leftLimit && rightIndex <= rightLimit) {
			if(name_arr.get(leftIndex).compareTo(name_arr.get(rightIndex)) < 0) {
				tempNameArray.add(name_arr.get(leftIndex));
				tempQuantityArray.add(quantity_arr.get(leftIndex));
				leftIndex++;
			} else {
				tempNameArray.add(name_arr.get(rightIndex));
				tempQuantityArray.add(quantity_arr.get(rightIndex));
				rightIndex++;
			}
		}

		while(leftIndex <= leftLimit) {
			tempNameArray.add(name_arr.get(leftIndex));
			tempQuantityArray.add(quantity_arr.get(leftIndex));
			leftIndex++;
		}

		while(rightIndex <= rightLimit) {
			tempNameArray.add(name_arr.get(rightIndex));
			tempQuantityArray.add(quantity_arr.get(rightIndex));
			rightIndex++;
		}

		int tempIndex = 0;
		for(int i = left; i <= right; i++) {
			name_arr.set(i, tempNameArray.get(tempIndex));
			quantity_arr.set(i, tempQuantityArray.get(tempIndex));
			tempIndex++;
		}
	}

	public static void sortNameDesc(int left, int right) {
		
		if (left == right) {
			return;
		}

		int mid = (right - left) / 2 + left;

		sortNameDesc(left, mid);
		sortNameDesc(mid + 1, right);

		ArrayList<String> tempNameArray = new ArrayList<>();
		ArrayList<Integer> tempQuantityArray = new ArrayList<>();
		int leftIndex = left;
		int leftLimit = mid;
		int rightIndex = mid + 1;
		int rightLimit = right;

		while (leftIndex <= leftLimit && rightIndex <= rightLimit) {
			if (name_arr.get(leftIndex).compareTo(name_arr.get(rightIndex)) > 0) {
				tempNameArray.add(name_arr.get(leftIndex));
				tempQuantityArray.add(quantity_arr.get(leftIndex));
				leftIndex++;
			} else {
				tempNameArray.add(name_arr.get(rightIndex));
				tempQuantityArray.add(quantity_arr.get(rightIndex));
				rightIndex++;
			}
		}

		while (leftIndex <= leftLimit) {
			tempNameArray.add(name_arr.get(leftIndex));
			tempQuantityArray.add(quantity_arr.get(leftIndex));
			leftIndex++;
		}

		while (rightIndex <= rightLimit) {
			tempNameArray.add(name_arr.get(rightIndex));
			tempQuantityArray.add(quantity_arr.get(rightIndex));
			rightIndex++;
		}

		int tempIndex = 0;
		for (int i = left; i <= right; i++) {
			name_arr.set(i, tempNameArray.get(tempIndex));
			quantity_arr.set(i, tempQuantityArray.get(tempIndex));
			tempIndex++;
		}
	}

	public static void sortQuantityAsc(int left, int right) {
		if (left == right) {
			return;
		}

		int mid = (right - left) / 2 + left;

		sortQuantityAsc(left, mid);
		sortQuantityAsc(mid + 1, right);

		ArrayList<String> tempNameArray = new ArrayList<>();
		ArrayList<Integer> tempQuantityArray = new ArrayList<>();
		int leftIndex = left;
		int leftLimit = mid;
		int rightIndex = mid + 1;
		int rightLimit = right;

		while (leftIndex <= leftLimit && rightIndex <= rightLimit) {
			if (quantity_arr.get(leftIndex) < quantity_arr.get(rightIndex)) {
				tempNameArray.add(name_arr.get(leftIndex));
				tempQuantityArray.add(quantity_arr.get(leftIndex));
				leftIndex++;
			} else {
				tempNameArray.add(name_arr.get(rightIndex));
				tempQuantityArray.add(quantity_arr.get(rightIndex));
				rightIndex++;
			}
		}

		while (leftIndex <= leftLimit) {
			tempNameArray.add(name_arr.get(leftIndex));
			tempQuantityArray.add(quantity_arr.get(leftIndex));
			leftIndex++;
		}

		while (rightIndex <= rightLimit) {
			tempNameArray.add(name_arr.get(rightIndex));
			tempQuantityArray.add(quantity_arr.get(rightIndex));
			rightIndex++;
		}

		int tempIndex = 0;
		for (int i = left; i <= right; i++) {
			name_arr.set(i, tempNameArray.get(tempIndex));
			quantity_arr.set(i, tempQuantityArray.get(tempIndex));
			tempIndex++;
		}
	}


	public static void sort() {
		cls();
		Scanner scan = new Scanner(System.in);
		int input;
		do {
			System.out.println("1. Name ascendingly");
			System.out.println("2. Name descendingly");
			System.out.println("3. Quantity ascendingly");
			System.out.println("4. Quantity descendingly");
			System.out.print(">> ");
			input = scan.nextInt();scan.nextLine();

			switch(input) {
			case 1:
				sortNameAsc(0, totalData-1);
				input = 0;
				try {
					FileWriter fileWriter = new FileWriter("data/inventory.data");
					for(int i = 0; i<totalData; i++) {
						fileWriter.write(name_arr.get(i) + "#" + quantity_arr.get(i) + "\n");
					}
					fileWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				//sortNameDesc(0, totalData-1);
				sortNameAsc(0, totalData-1);
				Collections.reverse(name_arr);
				Collections.reverse(quantity_arr);
				input = 0;
				try {
					FileWriter fileWriter = new FileWriter("data/inventory.data");
					for(int i = 0; i<totalData; i++) {
						fileWriter.write(name_arr.get(i) + "#" + quantity_arr.get(i) + "\n");
					}
					fileWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:
				sortQuantityAsc(0, totalData-1);
				input = 0;
				try {
					FileWriter fileWriter = new FileWriter("data/inventory.data");
					for(int i = 0; i<totalData; i++) {
						fileWriter.write(name_arr.get(i) + "#" + quantity_arr.get(i) + "\n");
					}
					fileWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4:
				sortQuantityAsc(0, totalData-1);
				Collections.reverse(name_arr);
				Collections.reverse(quantity_arr);
				input = 0;
				try {
					FileWriter fileWriter = new FileWriter("data/inventory.data");
					for(int i = 0; i<totalData; i++) {
						fileWriter.write(name_arr.get(i) + "#" + quantity_arr.get(i) + "\n");
					}
					fileWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}while(input != 0);
	}

	public static void main(String[] args) {
		new JavaTest();
	}
}
