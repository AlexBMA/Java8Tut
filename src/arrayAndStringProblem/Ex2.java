package arrayAndStringProblem;

public class Ex2 {

    /*
        Write code to reverse C-Style String
        (C-String means that "abcd" is represented as five characters, including the null character)
     */

    public static void main(String[] args){

        String text ="abcd";

        if(text !=null) {
            StringBuilder builder = new StringBuilder();
            int size = text.length();
            for(int index=(size-1);index>=0;index--) {
                builder.append(text.charAt(index));
            }
            System.out.println(builder.toString());
        }else{
            System.out.println("Text is null");
        }
    }
}
