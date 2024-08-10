package com.unicodeMM.unicodeMyanmar.serviceUtil;

import java.util.Arrays;

public class MMUnicode {
        public static String fixFont(String text) {
            if (text.length() < 1) return null;
            String[] sentences = text.split("\n");
            StringBuilder stringBuilder = new StringBuilder();
            for (String sentence : sentences) {
                String[] charArr = sentence.split("");
                String[] fixCharArr = new String[charArr.length];
                for (int i = 0; i < charArr.length; i++) {
                    if (charArr[i].equals("\u1031")) { //ေ
                        if (!charArr[i - 1].equals("\u103c") && !charArr[i - 1].equals("\u103b") && !charArr[i - 1].equals("\u103d")) { //if not ေ colse to ြ   && //if not ေ colse to ျ
                            fixCharArr[i - 1] = charArr[i];
                            fixCharArr[i] = charArr[i - 1];
                        } else if (charArr[i - 1].equals("\u103d")) {// - ွ ေ (ေ with - ွ)
                            fixCharArr[i - 2] = charArr[i];
                            fixCharArr[i] = charArr[i - 1];
                            fixCharArr[i - 1] = charArr[i - 2];
                        }

                    } else if (charArr[i].equals("\u103a")&&(i + 2) < charArr.length && charArr[i + 2].equals("\u103c")) {//  ် with ြ
                        charArr = Arrays.copyOf(charArr, charArr.length + 1);
                        fixCharArr = Arrays.copyOf(fixCharArr,fixCharArr.length+1);
                        int max = charArr.length-1;
                        int location = i + 1;
                        for (int j = max;j > location; j--) {
                            charArr[j] = charArr[j - 1];
                        }
                        charArr[location] = " ";
                        fixCharArr[i] = charArr[i];
                        fixCharArr[location] = charArr[location];

                    } else if (charArr[i].equals("\u103c")) {// ြ
                        if ((i + 1) < charArr.length && charArr[i + 1] != null && charArr[i + 1].equals("\u1031")) { //ြ   with ေ
                            if (i > 0 && i < charArr.length - 1) {
                                fixCharArr[i] = charArr[i];
                                fixCharArr[i - 1] = charArr[i + 1];
                                fixCharArr[i + 1] = charArr[i - 1];
                            }
                        } else { //ြ   without ေ
                            fixCharArr[i] = charArr[i - 1];
                            fixCharArr[i - 1] = charArr[i];
                        }

                    } else if (charArr[i].equals("\u103b")) {// ျ
                        if ((i + 1) < charArr.length && charArr[i + 1].equals("\u1031")) { // ျ with ေ
                            fixCharArr[i - 1] = charArr[i + 1];
                            fixCharArr[i + 1] = charArr[i];
                            fixCharArr[i] = charArr[i - 1];
                        } else { // ျ without ေ
                            fixCharArr[i] = charArr[i];
                        }
                    } else {
                        fixCharArr[i] = charArr[i];
                    }
                }
                for (int j = 0; j < fixCharArr.length; j++) {
                    stringBuilder.append(fixCharArr[j]);
                }
            }
            return stringBuilder.toString();
        }
    }

