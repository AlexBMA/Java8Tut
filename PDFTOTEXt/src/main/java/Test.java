import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Test {


    public static void main2(String[] args) throws FileNotFoundException {
        System.out.println("go");

        // C:\Users\Emmy\Downloads
        //revistacrestinulazi.ro-Explicații biblice 3 martie.pdf
        String filename ="C:\\Users\\Emmy\\Downloads\\Caiet tineri vers2.1.pdf";
        // PDF-XChange Viewer Document (.pdf)

        File f = new File(filename);
        String parsedText;
        PDFParser parser = null;
        try {
            parser = new PDFParser(new RandomAccessFile(f, "r"));
            parser.parse();
            COSDocument cosDoc = parser.getDocument();
            PDFTextStripper pdfStripper = new PDFTextStripper();
            PDDocument pdDoc = new PDDocument(cosDoc);

           int pageCount = pdDoc.getPages().getCount();

           for(int i=4;i<pageCount;i++){

               PDPage page = pdDoc.getPage(i);

               parsedText = firstTransformationText(pdfStripper, page);

               Pattern pattern = Pattern.compile("[\\d]{2}");
               Matcher matcher = pattern.matcher(parsedText);

               int count = 0;

               //parsedText = secondTransformationText(parsedText, matcher, count);

               pattern = Pattern.compile("[\\d]|[R]");
               matcher = pattern.matcher(parsedText);

               System.out.println(parsedText);
               count = 0;
               //parsedText = transform3(parsedText, matcher, count);
               System.out.println(parsedText);

               PrintWriter pw = new PrintWriter("C:\\Projects\\Java\\Java8Tut\\PDFTOTEXt\\src\\psdRez\\test"+i+".txt");
               pw.print(parsedText);
               pw.close();

           }


           /*
           String[] list =parsedText.split("[\\d]{2}");
           Stream<String> stream = Arrays.stream(list);
           stream.forEach(item -> {System.out.println(item);});
           */


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String transform3(String parsedText, Matcher matcher, int count) {
        while (matcher.find()){
            count++;
            System.out.println(count+ " "+matcher.group());
            parsedText = parsedText.replace(matcher.group(),"\n"+matcher.group());
        }
        parsedText = parsedText.trim();
        return parsedText;
    }

    private static String secondTransformationText(String parsedText, Matcher matcher, int count) {
        while(matcher.find()) {
             count++;
             //System.out.println("Match number "+count);
             //System.out.println("start(): "+matcher.start());
             //System.out.println("String: "+matcher.group());
             //System.out.println(parsedText.charAt(matcher.start())+","+parsedText.charAt(matcher.end()-1));
             //System.out.println("end(): "+matcher.end());
             if(count >1){
                 //System.out.println(matcher.group());
                 parsedText = parsedText.replace(matcher.group(),"\n"+matcher.group());
                 //System.out.println("##:"+parsedText.charAt(matcher.start()-1));
                 //int index = parsedText.indexOf(parsedText.charAt(matcher.start()-1));
                 //System.out.println("%%: "+index);

                 //parsedText = parsedText.replace(parsedText.charAt(index),'\n');
             }
         }

        //System.out.println(parsedText);
        return parsedText;
    }

    private static String firstTransformationText(PDFTextStripper pdfStripper, PDPage page) throws IOException {
        String parsedText;
        PDDocument newDoc =new PDDocument();
        newDoc.addPage(page);

        parsedText = pdfStripper.getText(newDoc);

        parsedText=parsedText.replaceFirst("[\\d]+"," ").trim();
        //parsedText=parsedText.replaceFirst(parsedText.charAt(0)+"","").trim();
        //parsedText = parsedText.replaceAll("[\\s]{3,}"," ");
        return parsedText;
    }


}
