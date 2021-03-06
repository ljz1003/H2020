package com.sample.demo;

import java.util.Scanner;

public class StringWorker {

    /*
    For a given string that only contains alphabet characters a-z,
    if 3 or more consecutive characters are identical, remove them from the string.
    Repeat this process until there is no more than 3 identical characters sitting besides each other.
    Example:
    Input: aabcccbbad
    Output:
           -> aabbbad
           -> aaad
           -> d
     */
    public String removeConsecutiveString3(String srcStr) {
        return doRemoveOrReplace(srcStr, new RemovePrinter());
    }

    /*
     #Stage 2 - advanced requirement
     Instead of removing the consecutively identical characters,
     replace them with a single character that comes before it alphabetically.
     Example:
          Input: aabcccbbad
          Output:
                -> aabbbad
                -> aaad
                -> d
     */
    public String replaceConsecutiveString(String srcStr) {
        return doRemoveOrReplace(srcStr, new ReplacePrinter());
    }

    //common function to do remove or replace operation.
    public String doRemoveOrReplace(String srcStr, Printer printer) {

        srcStr = preCheck(srcStr);

        int i = 0;
        while (i < srcStr.length()) {
            int endIndex = i + 1;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(srcStr.charAt(i));
            while (endIndex < srcStr.length()) {
                //only the char code value is equal, we think that the character is the same character,
                //for instance 'A' == 'A' only and 'A' !='a'
                if (srcStr.charAt(i) != srcStr.charAt(endIndex)) {
                    break;
                }
                stringBuilder.append(srcStr.charAt(endIndex));
                endIndex++;

            }

            String subStr = stringBuilder.toString();
            if (subStr.length() >= 3) {
                //print with remove format or replace format
                srcStr = printer.doPrint(srcStr, subStr);
                //backward the index so that we can start it over again.
                i = backwardIndex(srcStr, i);
            } else {
                i = endIndex;
            }
        }

        return srcStr;
    }

    //backward the index
    private int backwardIndex(String srcStr, int currentIndex) {
        int backwardIndex = currentIndex - 1;
        while(backwardIndex >= 0 && currentIndex < srcStr.length()) {
            if (srcStr.charAt(currentIndex) != srcStr.charAt(backwardIndex)) {
                break;
            }
            backwardIndex--;
        }
        return (backwardIndex < 0 ? 0 : backwardIndex + 1);
    }


    // fail-fast if the source is illegal
    private String preCheck(String src) {
        //System.out.println("source: " + srcStr);
        src = (src == null ? "" : src.trim());
        if ("".equals(src) || !src.matches("[a-z]+")) {
            throw new IllegalArgumentException("source string can not be blank or empty " +
                    "and it should only contain alphabet characters a-z");
        }
        return src;
    }


//    public static void main(String[] args) {
//        boolean optionOK = false;
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Press A for remove and B for replace,E to exit:");
//        StringWorker stringWorker = new StringWorker();
//        String option = scanner.nextLine();
//        option = (option == null ? "" : option.trim());
//
//        while(!"e".equalsIgnoreCase(option)) {
//
//            if ("a".equalsIgnoreCase(option)) {
//                System.out.print("Input:");
//                String src = scanner.nextLine();
//                stringWorker.removeConsecutiveString3(src);
//                System.out.print("removal done, what's your next operation:");
//                option = scanner.nextLine();
//
//            } else if ("b".equalsIgnoreCase(option)) {
//                System.out.print("Input:");
//                String src = scanner.nextLine();
//                stringWorker.replaceConsecutiveString(src);
//                System.out.print("replace done, what's your next operation:");
//                option = scanner.nextLine();
//
//            } else {
//                System.out.print("invalid option, please enter again:");
//            }
//        }
//
//    }


}
