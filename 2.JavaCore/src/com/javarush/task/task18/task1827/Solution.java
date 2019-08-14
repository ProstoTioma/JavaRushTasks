package com.javarush.task.task18.task1827;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

public class Solution {
    public static void main(String[] args) throws Exception {
        //String test = "d:/testbd";
        //System.setIn(new ByteArrayInputStream(test.getBytes()));
        if (args.length != 4) return;
        BufferedReader rcon = new BufferedReader(new InputStreamReader(System.in));
        String fileName = rcon.readLine();
        rcon.close();
        File f = new File(fileName);
        if (!f.isFile()) f.createNewFile();
        if (args[0].equals("-c")) {
            DataBD bd = new DataBD(fileName);
            bd.NewRec(args[1], Double.parseDouble(args[2]), Integer.parseInt(args[3]));
            bd.WriteBD();
        }
    }

    static class DataBD {
        private String fileName;
        private ArrayList<String> data = new ArrayList<>();

        public DataBD(String fileName) throws IOException {
            this.fileName = fileName;
            String bufStr;
            BufferedReader fileIn = new BufferedReader(new FileReader(fileName));
            while ((bufStr = fileIn.readLine()) != null){
                data.add(bufStr);
            }
            fileIn.close();
        }

        public int MaxID(){
            int max = 0;
            for (String s : data) {
                int tmp = Integer.parseInt(s.substring(0, 8).trim());
                if (max < tmp) max = tmp;
            }
            return max;
        }

        public String FormatRec(int ID, String productName, double price, int quantity){
            return String.format(Locale.ENGLISH, "%-8d%-30.30s%-8.2f%-4d", ID, productName, price, quantity);
        }

        public void NewRec(String productName, double price, int quantity) {
            data.add(FormatRec(MaxID() + 1, productName, price, quantity));
        }

        public void WriteBD() throws IOException {
            StringBuilder sb = new StringBuilder(data.size());
            for (String s : data) {
                sb.append(s);
                sb.append("\r\n");
            }
            BufferedWriter fileOut = new BufferedWriter(new FileWriter(fileName));
            fileOut.write(sb.toString());
            fileOut.close();
        }
    }
}