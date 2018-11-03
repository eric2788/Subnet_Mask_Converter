package main.calculate;

import java.util.Scanner;

public class Converter {
    public static void main(String[] args) {
        try {
            Scanner choose = new Scanner(System.in);
            System.out.println("請選擇需要轉換的類型。 (數字)");
            System.out.println("1. Prefix Length");
            System.out.println("2. Subnet Mask");
            System.out.println("3. 退出");
            while (choose.hasNext()) {
                int type = Integer.parseInt(choose.next());
                switch (type) {
                    case 1:
                        prefixToSubMask();
                        return;
                    case 2:
                        subMaskToPrefix();
                        return;
                    case 3:
                        System.out.println("已退出。");
                        return;
                    default:
                        throw new NumberFormatException();
                }
            }
            choose.close();
        } catch (NumberFormatException e) {
            System.out.println("無效的數值!");
            main(args);
        }
    }

    private static void subMaskToPrefix(){
        try {
            Scanner submask = new Scanner(System.in);
            System.out.println("請輸入網絡遮罩 IP:");
            String[] ip = submask.nextLine().split("\\.");
            int prefix = 0;
            for (int i = 0; i < ip.length; i++) {
                if (Integer.parseInt(ip[i]) == 255) {
                    prefix += 8;
                    continue;
                }
                if (Integer.parseInt(ip[i]) == 0){
                    break;
                }
                prefix += 8 - Math.log(256 - Integer.parseInt(ip[i])) / Math.log(2);
            }
            System.out.println("The prefix length is " + prefix);
        }catch (NumberFormatException e){
            System.out.println("無效的數值!");
            subMaskToPrefix();
        }
    }

    private static void prefixToSubMask() {
        try {
            int prefixLength;
            Scanner prefix = new Scanner(System.in);
            System.out.println("請輸入Prefix length:");
            prefixLength = Integer.parseInt(prefix.nextLine());
            if (prefixLength >= 32){
                throw new NumberFormatException();
            }
            int[] subnet = new int[4];
            for (int i = 0; i < subnet.length; i++) {
                if (prefixLength - 8 >= 0) {
                    subnet[i] = 255;
                    prefixLength -= 8;
                } else if (prefixLength > 0) {
                    subnet[i] = 256 - (int) (Math.pow(2, 8 - prefixLength));
                    prefixLength = 0;
                } else {
                    subnet[i] = 0;
                }
            }
            StringBuilder SubNetResult = new StringBuilder();
            for (int i = 0; i < subnet.length; i++) {
                int nodeMask = subnet[i];
                SubNetResult.append(nodeMask).append((i == subnet.length - 1 ? "" : "."));
            }
            System.out.println("The subnet mask is " + SubNetResult.toString());
            prefix.close();
        }catch (NumberFormatException e){
            System.out.println("無效的數值!");
            prefixToSubMask();
        }
    }
}
