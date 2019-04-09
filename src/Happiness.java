import java.util.Random;
import java.util.Scanner;

public class Happiness {
    public static void main(String[] args) {
Random random = new Random();
Scanner enter = new Scanner(System.in);

int Happiness, Luck, Wealth, Health, Intelligence, Age;
Happiness = 0;
Luck = 0;
Wealth = 0;
Health = 0;
Intelligence = 0;

        System.out.println("Введите Ваш возраст");
Age = enter.nextInt();

  while ( Age <100){
      Happiness += random.nextInt(100);
      Luck += random.nextInt(100);
      Wealth += random.nextInt(100);
      Health += random.nextInt(100);
      Intelligence += random.nextInt(100);
      Age++;
        }
        System.out.println("В Ваши " + Age + " лет Вы довольно неплохи");
        System.out.println("Уровень счастья равен" + Happiness + "!!!");
        System.out.println("Уровень удачи равен" + Luck + "!!!");
        System.out.println("Уровень благосостояния равен" + Wealth + "!!!");
        System.out.println("Уровень здоровья равен" + Health + "!!!");
        System.out.println("Уровень интеллекта равен" + Intelligence + "!!!");
    }
}
