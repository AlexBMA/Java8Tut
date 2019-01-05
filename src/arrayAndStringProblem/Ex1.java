package arrayAndStringProblem;

public class Ex1 {

    /*
        Implement an algorithm to determine if a string has all unique characters
        What if you can not use additional data structures
     */

    public static  void main(String[] args){

        String text ="aAbc";
        uniqueChar(text);

    }

    private static void uniqueChar(String text) {

        text = text.trim();

        if(text != null) {
            int textSize = text.length();
            if(textSize!=0) {
                boolean allUnique = true;
                for(int i=0;i<textSize;i++)
                {
                    for(int j=i+1;j<textSize;j++)
                    {
                        if(text.charAt(i) == text.charAt(j)) allUnique =false;
                    }
                }
                if(allUnique){
                    System.out.println("Text has all characters unique");
                }else {
                    System.out.println("Text has duplicated characters");
                }

            }else{
                System.out.println("Text is length 0");
            }
        }else {
            System.out.println("Text is null");
        }
    }
}
