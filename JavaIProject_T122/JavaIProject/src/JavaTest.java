import java.awt.RadialGradientPaint;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class JavaTest {
	
	
	static String[] toyList = {"Teddy Bear", "Toy Car", "Toy Plane", "RC Car", "Train Set", "Transform Robot"};
	static String name;
	static Integer money;
	static Integer difficulty;
	static Integer orders;
	static Integer work1;
	static Integer work2;
	static Integer work3;
	static Integer work4;
	static Integer work5;
	static Integer toyIdx;
	static Integer quantity;
	static Integer level;
	static Integer time;
	static Integer ordersLu;
	static Integer toy1;
	static Integer toy2;
	static Integer toy3;
	static Integer toy4;
	static Integer toy5;
	static Integer toy6;
	
	public static void newGame() {
		Scanner scan = new Scanner(System.in);
		do {
			System.out.println("Input player's name [0 to go back]: ");

			name = scan.nextLine();
			if(name.length()<3 && name.length()>20 ) {
				System.out.println("Name must be 3 to 20 characters");
			}
		}while(name.length()<3 && name.length()>20 );
		
		if(name.equals("0")) {
			
		}else {
			File file = new File("save.txt");
			if(!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			try {
				FileWriter fileWriter = new FileWriter("save.txt", true);
				fileWriter.write(name + "\n");
				fileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String fileName = name + ".txt";
			System.out.println(fileName);
			File file2 = new File(fileName);
			if(!file2.exists()) {
				try {
					file2.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			money = 0;
			difficulty = 1;
			orders = 0;
			work1 = 5;
			work2 = 0;
			work3 = 0;
			work4 = 0;
			work5 = 0;
			
			
			level = 1;
			Random rand = new Random();
			toyIdx = rand.nextInt(6);
			quantity = rand.nextInt(difficulty) + rand.nextInt(55 + difficulty - 1) + 100;
			time = (quantity/ (work1 + work2 + work3 + work4 + work5)) / (rand.nextInt(difficulty)+1);
			ordersLu = recursionLevel(level);
			toy1 = toy2 = toy3 = toy4 = toy5 = toy6 = 0;
			
			try {
				FileWriter fileWriter = new FileWriter(fileName);
				fileWriter.write(name + "\n");
				fileWriter.write(money + "\n");
				fileWriter.write(difficulty + "\n");
				fileWriter.write(orders + "\n");
				fileWriter.write(work1 + "\n");
				fileWriter.write(work2 + "\n");
				fileWriter.write(work3 + "\n");
				fileWriter.write(work4 + "\n");
				fileWriter.write(work5 + "\n");
				fileWriter.write(toyIdx + "\n");
				fileWriter.write(quantity + "\n");
				fileWriter.write(level + "\n");
				fileWriter.write(time + "\n");
				fileWriter.write(ordersLu + "\n");
				fileWriter.write(toy1 + "\n");
				fileWriter.write(toy2 + "\n");
				fileWriter.write(toy3 + "\n");
				fileWriter.write(toy4 + "\n");
				fileWriter.write(toy5 + "\n");
				fileWriter.write(toy6 + "\n");
				
				fileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			gameLobby();
		}
	}
	
	public static int recursionLevel(int level) {
		if(level == 1 || level == 2) {
			return 3;
		}else {
			return (int) (recursionLevel(level-1) + recursionLevel(level-2) + (0.5 * recursionLevel(level-1)));
		}
	}
	
	public static void gameLobby() {
		Scanner scan = new Scanner(System.in);
		int input;
		do {
			System.out.println("");
			System.out.println("Current Order");
			System.out.println("");
			System.out.println("Toy : " + toyList[toyIdx]);
			System.out.println("Level : " + level);
			System.out.println("Quantity : " + quantity);
			System.out.println("Time : " + time);
			System.out.println("Difficulty : " + difficulty);
			System.out.println("Money : " + money);
			System.out.println("Orders Done : " + orders);
			System.out.println("");
			System.out.println("");
			System.out.println("1. Show toy list");
			System.out.println("2. Produce toys");
			System.out.println("3. Manage workers");
			System.out.println("4. Save and exit game");
			System.out.println("5. End game");
			System.out.println(">> ");
			input = scan.nextInt(); scan.nextLine();
			
			switch(input) {
			case 1:
				showToy();
				break;
			case 2:
				produceToy();
				break;
			case 3:
				manageWorker();
				break;
			case 4:
				saveFile();
				break;
			case 5:
				System.out.println("Are you sure you want to end game (You cannot load this game after this!) ? [Y/N]:");
				String end = scan.nextLine();
				if(end.equalsIgnoreCase("n")) {
					input = -1;
				}
				break;
			}
		}while(input != 5 && time >= 0);
	}
	
	public static void cls() {
		for(int i = 0; i<40; i++) {
			System.out.println("");
		}
	}
	
	public static void produceToy() {
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		int randNum = rand.nextInt(1000)+1000;
		for(int i = 1; i<=10; i++) {
			cls();
			System.out.println("Producing Toys...");
			System.out.printf("[");
			for(int j = 1; j<=(i*2); j++) {
				System.out.print("#");
			}
			for(int k = 1; k<=20-(i*2); k++) {
				System.out.print(" ");
			}
			System.out.printf("]");
			System.out.printf("     %d%%", i*10);
			try {
				Thread.sleep(randNum);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		cls();
		
		int sum = 0;
		int workhour = rand.nextInt(5) + 7;
		int formula;
		for(int i = 0; i<work1; i++) {
			formula = (1 * workhour)/difficulty;
			sum+=formula;
		}
		for(int i = 0; i<work2; i++) {
			formula = (2 * workhour)/difficulty;
			sum+=formula;
		}
		for(int i = 0; i<work3; i++) {
			formula = (3 * workhour)/difficulty;
			sum+=formula;
		}
		for(int i = 0; i<work4; i++) {
			formula = (4 * workhour)/difficulty;
			sum+=formula;
		}
		for(int i = 0; i<work5; i++) {
			formula = (5 * workhour)/difficulty;
			sum+=formula;
		}
		
		System.out.println("");
		System.out.println("Current Order");
		System.out.println("");
		System.out.println("Toy : " + toyList[toyIdx]);
		System.out.println("Level : " + level);
		System.out.println("Quantity : " + quantity);
		System.out.println("Time : " + time);
		System.out.println("Difficulty : " + difficulty);
		System.out.println("Money : " + money);
		System.out.println("Orders Done : " + orders);
		System.out.println("");
		System.out.println("");
		
		if(toyIdx == 0) {
			toy1 += sum;
		}else if(toyIdx == 1) {
			toy2 += sum;
		}else if(toyIdx == 2) {
			toy3 += sum;
		}else if(toyIdx == 3) {
			toy4 += sum;
		}else if(toyIdx == 4) {
			toy5 += sum;
		}else if(toyIdx == 5) {
			toy6 += sum;
		}
		time--;
		
		changeDay();
		
		System.out.printf("Your factory has produced %d %s in %d hours\n", sum, toyList[toyIdx], workhour);
		System.out.println("Press Enter to Continue...");
		scan.nextLine();
		cls();
	}

	public static void changeDay() {
		if(toyIdx == 0) {
			if(quantity<toy1) {
				toy1-=quantity;
				orders++;
				money += quantity*2*(100 + (difficulty -1)*10)/100;
				Random rand = new Random();
				toyIdx = rand.nextInt(6);
				quantity = rand.nextInt(difficulty) + rand.nextInt(55 + difficulty - 1) + 100;
				level = (rand.nextInt(difficulty) + 1)%5;
				time = (quantity/ (work1 + work2 + work3 + work4 + work5)) / (rand.nextInt(difficulty)+1);
			}
		}else if(toyIdx == 1) {
			if(quantity<toy2) {
				toy2-=quantity;
				orders++;
				money += quantity*5*(100 + (difficulty -1)*10)/100;
				Random rand = new Random();
				toyIdx = rand.nextInt(6);
				quantity = rand.nextInt(difficulty) + rand.nextInt(55 + difficulty - 1) + 100;
				level = (rand.nextInt(difficulty) + 1)%5;
				time = (quantity/ (work1 + work2 + work3 + work4 + work5)) / (rand.nextInt(difficulty)+1);
			}
		}else if(toyIdx == 2) {
			if(quantity<toy3) {
				toy3-=quantity;
				orders++;
				money += quantity*7*(100 + (difficulty -1)*10)/100;
				Random rand = new Random();
				toyIdx = rand.nextInt(6);
				quantity = rand.nextInt(difficulty) + rand.nextInt(55 + difficulty - 1) + 100;
				level = (rand.nextInt(difficulty) + 1)%5;
				time = (quantity/ (work1 + work2 + work3 + work4 + work5)) / (rand.nextInt(difficulty)+1);
			}
		}else if(toyIdx == 3) {
			if(quantity<toy4) {
				toy4-=quantity;
				orders++;
				money += quantity*15*(100 + (difficulty -1)*10)/100;
				Random rand = new Random();
				toyIdx = rand.nextInt(6);
				quantity = rand.nextInt(difficulty) + rand.nextInt(55 + difficulty - 1) + 100;
				level = (rand.nextInt(difficulty) + 1)%5;
				time = (quantity/ (work1 + work2 + work3 + work4 + work5)) / (rand.nextInt(difficulty)+1);
			}
		}else if(toyIdx == 4) {
			if(quantity<toy5) {
				toy5-=quantity;
				orders++;
				money += quantity*25*(100 + (difficulty -1)*10)/100;
				Random rand = new Random();
				toyIdx = rand.nextInt(6);
				quantity = rand.nextInt(difficulty) + rand.nextInt(55 + difficulty - 1) + 100;
				level = (rand.nextInt(difficulty) + 1)%5;
				time = (quantity/ (work1 + work2 + work3 + work4 + work5)) / (rand.nextInt(difficulty)+1);
			}
		}else if(toyIdx == 5) {
			if(quantity<toy6) {
				toy6-=quantity;
				orders++;
				money += quantity*55*(100 + (difficulty -1)*10)/100;
				Random rand = new Random();
				toyIdx = rand.nextInt(6);
				quantity = rand.nextInt(difficulty) + rand.nextInt(55 + difficulty - 1) + 100;
				level = (rand.nextInt(difficulty) + 1)%5;
				time = (quantity/ (work1 + work2 + work3 + work4 + work5)) / (rand.nextInt(difficulty)+1);
			}
		}
		if(orders >= ordersLu) {
			orders = 0;
			difficulty++;
			Random rand = new Random();
			level = (rand.nextInt(difficulty) + 1)%5;
			ordersLu = recursionLevel(level);
		}
	}

	public static void showToy() {
		Scanner scan = new Scanner(System.in);
		System.out.println("|        Toy        | Price | Quantity |");
		System.out.printf("| Teddy Bear        |%7d|%10d|\n", 2, toy1);
		System.out.printf("| Toy Car           |%7d|%10d|\n", 5, toy2);
		System.out.printf("| Toy Plane         |%7d|%10d|\n", 7, toy3);
		System.out.printf("| RC Car            |%7d|%10d|\n", 15, toy4);
		System.out.printf("| Train Set         |%7d|%10d|\n", 25, toy5);
		System.out.printf("| Transform Robot   |%7d|%10d|\n", 55, toy6);
		System.out.println("Press enter to continue");
		scan.nextLine();
	}

	public static void manageWorker() {
		Scanner scan = new Scanner(System.in);
		int input;
		do {
			System.out.println("Level, Workers");
			System.out.println("");
			System.out.println("1," + work1);
			System.out.println("2," + work2);
			System.out.println("3," + work3);
			System.out.println("4," + work4);
			System.out.println("5," + work5);
			System.out.println("");
			System.out.println("Money : " + money);
			System.out.println("Manage workers");
			System.out.println("1. Hire new worker");
			System.out.println("2. Upgrade worker");
			System.out.println("3. Go back");
			System.out.print(">> ");
			input = scan.nextInt(); scan.nextLine();
			
			switch(input) {
			case 1:
				hire();
				break;
			case 2:
				upgrade();
				break;
			}
		}while(input != 3);
		
	}

	public static void upgrade() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Upgrade Price List");
		System.out.println("| No. | Upgrade | Price |");
		System.out.println("|  1  | 1 -> 2  | 600   |");
		System.out.println("|  2  | 2 -> 3  | 800   |");
		System.out.println("|  3  | 3 -> 4  | 1100  |");
		System.out.println("|  4  | 4 -> 5  | 1500  |");
		
		System.out.print("Input which upgrade you want to buy [only applies to one worker] : ");
		int inp = scan.nextInt(); scan.nextLine();
		
		switch(inp) {
		case 1:
			if(work1 == 0) {
				System.out.println("You don't have any worker with that level");
			}else if(money<600) {
				System.out.println("You don't have enough money");
			}else {
				System.out.println("You have successfully upgraded one worker from level 1 to level 2");
				work1--;
				work2++;
				money-=600;
			}
			break;
		case 2:
			if(work2 == 0) {
				System.out.println("You don't have any worker with that level");
			}else if(money<800) {
				System.out.println("You don't have enough money");
			}else {
				System.out.println("You have successfully upgraded one worker from level 2 to level 3");
				work2--;
				work3++;
				money-=800;
			}
			break;
		case 3:
			if(work3 == 0) {
				System.out.println("You don't have any worker with that level");
			}else if(money<1100) {
				System.out.println("You don't have enough money");
			}else {
				System.out.println("You have successfully upgraded one worker from level 3 to level 4");
				work3--;
				work4++;
				money-=1100;
			}
			break;
		case 4:
			if(work4 == 0) {
				System.out.println("You don't have any worker with that level");
			}else if(money<1500) {
				System.out.println("You don't have enough money");
			}else {
				System.out.println("You have successfully upgraded one worker from level 4 to level 5");
				work4--;
				work5++;
				money-=1500;
			}
			break;
		}
	}

	public static void hire() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Hire a level 1 worker for 500 [Y/N]:");
		String input = scan.nextLine();
		if(input.equalsIgnoreCase("y")) {
			if(money >= 500) {
				work1++;
				money-=500;
			}else {
				System.out.println("You don't have enough money");
			}
		}
	}

	public static void saveFile() {
		String filename = name + ".txt";
		File file = new File(filename);
		try {
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(name + "\n");
			fileWriter.write(money + "\n");
			fileWriter.write(difficulty + "\n");
			fileWriter.write(orders + "\n");
			fileWriter.write(work1 + "\n");
			fileWriter.write(work2 + "\n");
			fileWriter.write(work3 + "\n");
			fileWriter.write(work4 + "\n");
			fileWriter.write(work5 + "\n");
			fileWriter.write(toyIdx + "\n");
			fileWriter.write(quantity + "\n");
			fileWriter.write(level + "\n");
			fileWriter.write(time + "\n");
			fileWriter.write(ordersLu + "\n");
			fileWriter.write(toy1 + "\n");
			fileWriter.write(toy2 + "\n");
			fileWriter.write(toy3 + "\n");
			fileWriter.write(toy4 + "\n");
			fileWriter.write(toy5 + "\n");
			fileWriter.write(toy6 + "\n");
			
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void loadGame() {
	    System.out.println("Save files");
	    System.out.println("");
	    File file = new File("save.txt");
	    String oneLine;
	    try {
	        Scanner myFile = new Scanner(file);
	        while (myFile.hasNextLine()) {
	            oneLine = myFile.nextLine();
	            System.out.println(oneLine);
	        }
	    } catch (FileNotFoundException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }

	    System.out.println("Please select a save file to load [0 to go back]: ");
	    Scanner scan = new Scanner(System.in);
	    String nameFile = scan.nextLine();
	    if (nameFile.equals("0")) {

	    } else {
	        String fileName = nameFile + ".txt";
	        File file2 = new File(fileName);
	        if (!file2.exists()) {
	            System.out.println("File does not exist!");
	            return;
	        }
	        try {
	            Scanner myFile = new Scanner(file2);
	            int i = 0;
	            while (myFile.hasNextLine()) {
	                oneLine = myFile.nextLine();
	                switch (i) {
	                    case 0:
	                        name = oneLine;
	                        i++;
	                        break;
	                    case 1:
	                        money = Integer.parseInt(oneLine);
	                        i++;
	                        break;
	                    case 2:
	                        difficulty = Integer.parseInt(oneLine);
	                        i++;
	                        break;
	                    case 3:
	                        orders = Integer.parseInt(oneLine);
	                        i++;
	                        break;
	                    case 4:
	                        work1 = Integer.parseInt(oneLine);
	                        i++;
	                        break;
	                    case 5:
	                        work2 = Integer.parseInt(oneLine);
	                        i++;
	                        break;
	                    case 6:
	                        work3 = Integer.parseInt(oneLine);
	                        i++;
	                        break;
	                    case 7:
	                        work4 = Integer.parseInt(oneLine);
	                        i++;
	                        break;
	                    case 8:
	                        work5 = Integer.parseInt(oneLine);
	                        i++;
	                        break;
	                    case 9:
	                        toyIdx = Integer.parseInt(oneLine);
	                        i++;
	                        break;
	                    case 10:
	                        quantity = Integer.parseInt(oneLine);
	                        i++;
	                        break;
	                    case 11:
	                        level = Integer.parseInt(oneLine);
	                        i++;
	                        break;
	                    case 12:
	                        time = Integer.parseInt(oneLine);
	                        i++;
	                        break;
	                    case 13:
	                        ordersLu = Integer.parseInt(oneLine);
	                        i++;
	                        break;
	                    case 14:
	                        toy1 = Integer.parseInt(oneLine);
	                        i++;
	                        break;
	                    case 15:
	                        toy2 = Integer.parseInt(oneLine);
	                        i++;
	                        break;
	                    case 16:
	                        toy3 = Integer.parseInt(oneLine);
	                        i++;
	                        break;
	                    case 17:
	                        toy4 = Integer.parseInt(oneLine);
	                        i++;
	                        break;
	                    case 18:
	                        toy5 = Integer.parseInt(oneLine);
	                        i++;
	                        break;
	                    case 19:
	                        toy6 = Integer.parseInt(oneLine);
	                        i++;
	                        break;
	                }
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	    }

	    gameLobby();
	}
	
	public static void highScore() {
		Scanner scan = new Scanner(System.in);
		System.out.println("No high score data");
		System.out.println("Press Enter to Continue...");
		scan.nextLine();
	}
	
	public JavaTest(){
		Scanner scan = new Scanner(System.in);
		int input;
		do {
			System.out.println("Toy Vactory Manager");
			System.out.println("1. New Game");
			System.out.println("2. Load Game");
			System.out.println("3. High Score");
			System.out.println("4. Exit");
			System.out.print(">> ");
			input = scan.nextInt();scan.nextLine();
			
			switch(input) {
			case 1:
				newGame();
				break;
			case 2:
				loadGame();
				break;
			case 3:
				highScore();
			}
		}while(input != 4);
	}

	public static void main(String[] args) {
		new JavaTest();
	}
}
