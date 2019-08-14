package com.javarush.task.task20.task2004;

import java.io.*;

/* 
Читаем и пишем в файл статики
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {

            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            ClassWithStatic classWithStatic = new ClassWithStatic();
            classWithStatic.i = 3;
            classWithStatic.j = 4;
            classWithStatic.save(outputStream);
            outputStream.flush();
            ClassWithStatic loadedObject = new ClassWithStatic();
            loadedObject.staticString = "something";
            loadedObject.i = 6;
            loadedObject.j = 7;
            loadedObject.load(inputStream);
            //check here that classWithStatic object equals to loadedObject object - проверьте тут, что classWithStatic и loadedObject равны
            System.out.println(classWithStatic.equals(loadedObject));

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class ClassWithStatic {
        public static String staticString = "it's test static string";
        public int i;
        public int j;

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            PrintWriter printWriter = new PrintWriter(outputStream);
            String isClassWithStatic=this!=null?"yes":"no";

            if(isClassWithStatic.equals("yes")){

                printWriter.println(isClassWithStatic);
                printWriter.println(this.i);
                printWriter.println(this.j);
                printWriter.println(Solution.ClassWithStatic.staticString);
                /*
                bufferedWriter.write(isClassWithStatic);
                bufferedWriter.newLine();
                bufferedWriter.write(String.valueOf(this.i));
                bufferedWriter.newLine();
                bufferedWriter.write(String.valueOf(this.j));
                bufferedWriter.newLine();
                bufferedWriter.write(ClassWithStatic.staticString);
                */
            }
            else
                //bufferedWriter.write(isClassWithStatic);
                printWriter.println(isClassWithStatic);
            //bufferedWriter.flush();
            //bufferedWriter.close();
            printWriter.close();

        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(inputStream));
            String s=bufferedReader.readLine();
            if(s!=null&&s.equals("yes")){
                this.i=Integer.parseInt(bufferedReader.readLine());
                this.j=Integer.parseInt(bufferedReader.readLine());
                ClassWithStatic.staticString=bufferedReader.readLine();
            }
            bufferedReader.close();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ClassWithStatic that = (ClassWithStatic) o;

            if (i != that.i) return false;
            return j == that.j;

        }

        @Override
        public int hashCode() {
            int result = i;
            result = 31 * result + j;
            return result;
        }
    }
}
