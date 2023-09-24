import java.util.Random;
import java.util.Scanner;

public class JavaTest {
	public JavaTest(){
		
	}
	
	static String name;
	static String mode;
	
	static int p_maxhealth = 25;
	static int p_myhealth = 25;
	static int p_maxxp = 8;
	static int p_myxp = 0;
	static int p_level = 1;
	static int p_attack = 3;
	static int p_stamina = 8;
	static int p_dstamina = 8;
	static int p_evasion = 10;
	
	static int e_maxhealth;
	static int e_health;
	static int e_attack;
	static int e_experience;
	
	public static void clearScreen() {
	    for (int i = 0; i < 50; i++) {
	        System.out.println();
	    }
	}
	
	public static void sleep() {
		clearScreen();
		Scanner scan = new Scanner(System.in);
		System.out.println("taking a nap");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("you have woken up");
		System.out.println("stamina restored!");
		System.out.println("press enter to continue");
		
		p_stamina = p_dstamina;
		scan.nextLine();
	}
	
	public static void fight2() {
		Scanner scan = new Scanner(System.in);
		if(p_myhealth > 0 && e_health > 0) {
			clearScreen();
			
			System.out.printf("Enemy Turn!\n");
			System.out.printf("--------------\n");
			
			Random randNum = new Random();
			
			int rand = randNum.nextInt(100);

			if(rand > p_evasion) {
				System.out.printf("Oh no, you've been hitted %d damage!\n", e_attack);
				System.out.println("Press enter to continue");
				
				p_myhealth -= e_attack;
				scan.nextLine();
				fight();
			}else {
				System.out.printf("You dodged the enemy attack!\n");
				System.out.println("Press enter to continue");
				scan.nextLine();
				fight();
			}
			
			
		}else if(p_myhealth <= 0) {
			System.out.println("You lose");
			scan.nextLine();
			menu();
		}else if(e_health <= 0) {
			System.out.println("Congrats! you've defeated the enemy!");
			System.out.printf("You have gained %d exp!\n", e_experience);
			p_myxp += e_experience;
			
			if(p_myxp >= p_maxxp) {
				System.out.println("============================");
				System.out.println("Congrats! you've Leveled up!"); 
				System.out.println("============================");
				System.out.println("Stats Increase!");
				System.out.printf("MaxHP increase by %d\n", 5);
				System.out.printf("Atk increase by %d\n", 1);
				p_maxhealth += 5;
				p_attack += 1;
				p_level++;
				p_myhealth = p_maxhealth;
				p_myxp -= p_maxxp;
				System.out.printf("HP restored to %d\n", p_maxhealth);
				System.out.println("Press enter to continue");
				
				
				scan.nextLine();
			}
			scan.nextLine();

		}
	}
	
	
	private static int randNum(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void fight() {
		Scanner scan = new Scanner(System.in);
		if(p_myhealth > 0 && e_health > 0) {
			clearScreen();
			
			System.out.printf("your hp, %d\n", p_myhealth);
			System.out.printf("your attack, %d\n", p_attack);
			System.out.printf("enemy hp, %d\n\n", e_health);
			System.out.printf("1.Attack\n2.Run\n>> ");
			
			int input = scan.nextInt();
			scan.nextLine();
			switch(input) {
			case 1:
				System.out.printf("You hitted the enemy with %d damage!\n", p_attack);
				e_health -= p_attack;
				System.out.println("Press enter to continue\n");
				scan.nextLine();
				fight2();
				break;
			case 2:
				System.out.println("you run away!");
				break;
			}
			
		}else if(p_myhealth <= 0) {
			System.out.println("You lose");
			scan.nextLine();
		
		}else if(e_health <= 0) {
			System.out.println("Congrats! you've defeated the enemy!");
			System.out.printf("You have gained %d exp!\n", e_experience);
			scan.nextLine();
			
			p_myxp += e_experience;
			if(p_myxp >= p_maxxp) {
				System.out.println("============================");
				System.out.println("Congrats! you've Leveled up!"); 
				System.out.println("============================");
				System.out.println("Stats Increase!");
				System.out.printf("MaxHP increase by %d\n", 5);
				System.out.printf("Atk increase by %d\n", 1);
				p_level++;
				p_maxhealth += 5;
				p_attack += 1;
				p_myhealth = p_maxhealth;
				p_myxp -= p_maxxp;
				System.out.printf("HP restored to %d\n", p_maxhealth);
				System.out.println("Press enter to continue");
				
				
				scan.nextLine();
			}
		}
		
	}
	
	public static void train() {
		Scanner scan = new Scanner(System.in);
		int input;
		do {
			clearScreen();

			System.out.println("Welcome to train academy");
			System.out.println("1. Train attack");
			System.out.println("2. Train dodge");
			System.out.println("3. Exit");
			
			input = scan.nextInt();
			scan.nextLine();
			
			switch(input) {
			case 1:
				clearScreen();
				System.out.println("training..");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("attack damage increase by 1");
				System.out.println("press enter to continue..");
				scan.nextLine();
				
				p_attack += 1;
				break;
			case 2:
				clearScreen();
				System.out.println("training..");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("training dodgeee!");
				System.out.println("evasion increased by 1%");
				System.out.println("press enter to continue..");
				scan.nextLine();
				
				p_evasion += 1;
				break;
			}
			
		}while(input != 3);
	}
	
	public static void home() {
		Scanner scan = new Scanner(System.in);
		
		int input;
		do {
			if(p_myhealth <= 0) {
				break;
			}
			clearScreen();
			e_health = e_maxhealth;
			
			System.out.printf("Mode, %s\n", mode);
			System.out.printf("Username, %s\n", name);
			System.out.printf("Attack, %d\n", p_attack);
			System.out.printf("HP/MaxHp, %d/%d\n", p_myhealth, p_maxhealth);
			System.out.printf("XP/MaxXP %d/%d\n", p_myxp, p_maxxp);
			System.out.printf("Level, %d\n", p_level);
			System.out.printf("Stamina, %d\n\n", p_stamina);
			
			System.out.println("1.Fight");
			System.out.println("2.Train");
			System.out.println("3.Sleep");
			System.out.println("4.Exit");
			input = scan.nextInt();
			scan.nextLine();
			
			switch(input) {
			case 1:
				if(p_stamina > 0) {
					fight();
					p_stamina--;
				}else {
					System.out.println("you're tired, get a rest");
				}
				
				break;
			case 2:
				train();
				break;
			case 3:
				sleep();
				break;
			}
			
			
			
		}while(input != 4);
	}
	
	public static void validation() {
		clearScreen();
		int validate_name1, validate_name2, validate_name3;
		Scanner scan = new Scanner(System.in);
		do {
			System.out.print("Input name [Alphanumeric and 3-10 characters] : ");
			
			name = scan.nextLine();
			validate_name1 = 0;
			validate_name2 = 0;
			validate_name3 = 0;
			for (int i = 0; i < name.length(); i++){
	            if(Character.isLetter(name.charAt(i))==true) {
	            	validate_name1 = 1;
	            }
	            if(Character.isDigit(name.charAt(i))==true) {
	            	validate_name2 = 1;
	            }
	        }
			if(name.length()>=3 && name.length()<=10) {
				validate_name3 = 1;
			}
		}while(validate_name1 != 1 || validate_name2 != 1 || validate_name3 != 1);
		
		
		int validate_mode;
		do {

			System.out.print("Choose your mode [Easy | Medium | Hard] : ");
			
			mode = scan.nextLine();
			validate_mode = 0;
			
			
			if(mode.equalsIgnoreCase("Easy")) {
				e_maxhealth = 10;
				e_health = 10;
				e_attack = 2;
				e_experience = 3;
			}else if(mode.equalsIgnoreCase("Medium")) {
				e_maxhealth = 12;
				e_health = 12;
				e_attack = 3;
				e_experience = 5;
			}else if(mode.equalsIgnoreCase("Hard")) {
				e_maxhealth = 16;
				e_health = 16;
				e_attack = 4;
				e_experience = 8;
			}else {
				validate_mode = 1;
			}
			
		}while(validate_mode == 1);
		
		home();
	}
	
	
	
	public static void play() {
		clearScreen();
		validation();
	}
	
	public static void menu() {
		int input;
		do {

			clearScreen();
			System.out.println("SkyLrim");
			System.out.println("============");
			System.out.println("1. Play");
			System.out.println("2. Exit");
			System.out.print(">> ");
			
			Scanner scan = new Scanner(System.in);
			input = scan.nextInt();
			scan.nextLine();	
		
			switch(input) {
			case 1:
				play();
				p_myhealth = p_maxhealth;
				p_stamina = p_dstamina;
				p_myxp = 0;
				break;
			}
		}while(input != 2);
	}
	
	public static void main(String[] args) {
		menu();
	}
}
