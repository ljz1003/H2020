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
            //ensure every character is alphabetic
            if(!Character.isAlphabetic(srcStr.codePointAt(i))) {
                throw new IllegalArgumentException("source string must an alphabetic string.");

            } else {

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


    private String preCheck(String src) {
        //System.out.println("source: " + srcStr);
        src = (src == null ? "" : src.trim());
        if ("".equals(src)) {
            throw new IllegalArgumentException("source string can not blank or empty.");
        }
        return src;
    }

//    public static void main(String[] args) {
//        System.out.println("Input (Press enter to continue):");
//        Scanner scanner = new Scanner(System.in);
//        String src = scanner.next();
//    }
}
