package main.menu;

import main.calculate.Converter;
import main.tabulate.SubNetting;

import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("請選擇類別。(數字)");
        System.out.println("1. Prefix Length / SubNet Mask");
        System.out.println("2. SubNetting Table");
        System.out.println("3. 退出");
        switch (scanner.nextInt()){
            case 1:
                new Converter().ConvertMenu();
                return;
            case 2:
                new SubNetting().userInput();
                return;
            case 3:
                System.out.println("已退出。");
                return;
            default:
                System.out.println("無效數值。");
                main(args);
        }
    }
}
