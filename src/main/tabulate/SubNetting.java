package main.tabulate;

import main.menu.MainMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SubNetting {
    private String ip;
    private int[] node;
    public void userInput(){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("請輸入你的IP (連帶Prefix Length)。");
            ip = scanner.nextLine();
            if (!ip.contains("/") || !ip.contains(".")) {
                throw new NumberFormatException();
            }
                System.out.println("請輸入你考慮的因素。(數字)");
                System.out.println("1. More Host number");
                System.out.println("2. More Network Number");
                System.out.println("3. 返回上一頁");
            switch (scanner.nextInt()){
                case 1:
                    networkTabulate();
                    return;
                case 2:
                    hostTabulate();
                    return;
                case 3:
                    System.out.println("正在返回上一頁....");
                    MainMenu.main(new String[10]);
                    return;
            }
            scanner.close();
        }catch (NumberFormatException e){
            System.out.println("無效的數值!");
            userInput();
        }

    }

    private void networkTabulate(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("請輸入你所需要的 Network Number 之最大數量");
        int number = scanner.nextInt();
        scanner.close();
        int root = 0;
        int max = 0;
        for (; (int)Math.pow(2,root) - 1  < number; root++) {
            max = (int)Math.pow(2,root) - 1;
        }
        String[] ip = this.ip.replace("/",".").split("\\.");
        if (ip.length != 5) {
            System.out.println("Wrong ip format.");
            return;
        }
        int[] node = new int[5];
        for (int i = 0; i < ip.length; i++) {
            node[i] = Integer.parseInt(ip[i]);
        }
        int prefix = node[4] + root;
        int remain = (int)Math.pow(2,32 - prefix);
        System.out.printf("You have %d host number can use.",remain);
        int Byte = 0;
        int remainBit = 32 - prefix;
        while (true) {
            if (remainBit/8 > 0 &&  remainBit > 0){
                Byte++;
                remainBit -= 8;
            }else{
                break;
            }
        }
        System.out.println("\nAvaliable IP Range:");
        int fuckyou = node[node.length - 2];
        int intital = (int) Math.pow(2,remainBit);
        int count = 0;
        while(count < number) {
            System.out.println(NodetoString(node));
            showIPRange(node,remain,Byte);
            clearOldData(node,Byte, fuckyou);
            node[node.length - 2 - Byte] += intital;
            count++;
        }
    }
    private int count;
    private void clearOldData(int[] node,int Byte,int fuckyou){
        String[] ip = this.ip.replace("/",".").split("\\.");
        final int[] orignode = new int[5];
        for (int i = 0; i < ip.length; i++) {
            orignode[i] = Integer.parseInt(ip[i]);
        }
        node[node.length - 2 - Byte] -= count;
        count = 0;
        node[node.length - 2] = fuckyou;
    }

    private void showIPRange(int[] node, int numberPerHost, int Byte){
            node[node.length - 2] += 1;
            System.out.println("Start host: " + NodetoString(node));
            node[node.length - 2] += numberPerHost - 2;
            if (node[node.length - 2] > 255) {
                node[node.length - 2 - Byte] += 1;
                node[node.length - 2] -= 256 + 1;
                count++;
            }
            System.out.println("End Host: " + NodetoString(node));
    }

    private String NodetoString(int[] node){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < node.length - 1; i++) {
            builder.append(node[i]).append((i == node.length - 2 ? "" : "."));
        }
        return builder.toString();
    }

    private void hostTabulate(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("請輸入你所需要的 Host Number 之最大數量");
        int number = scanner.nextInt();
        scanner.close();
        int root = 0;
        int max = 0;
        while (max < number){
            max = (int)Math.pow(2,root) - 1;
            root++;
        }
        String[] ip = this.ip.replace("/",".").split("\\.");
        if (ip.length != 5) {
            System.out.println("Wrong ip format.");
            return;
        }
        int[] node = new int[5];
        for (int i = 0; i < ip.length; i++) {
            node[i] = Integer.parseInt(ip[i]);
        }
        int prefix = node[4] + (32 - node[4] - root);
        int remain = (int)Math.pow(2,root);
        System.out.printf("You have %d network number can use.",root - 2);

        List<String> vaildIPRange = new ArrayList<>();
    }
}
