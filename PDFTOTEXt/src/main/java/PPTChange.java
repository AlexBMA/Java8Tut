import org.apache.poi.openxml4j.opc.internal.FileHelper;
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideLayout;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PPTChange {


    public PPTChange() {
    }

    public void run(String folderName) throws IOException {

        //ClassLoader classLoader = getClass().getClassLoader();
        //String name = classLoader.getResource("").getFile();
        Stream<Path> walk = Files.walk(Paths.get("C:\\Projects\\Java\\Java8Tut\\PDFTOTEXt\\src\\main\\resources"));

        List<String> result = walk.filter(Files::isRegularFile)
                .map(x -> x.toString()).collect(Collectors.toList());


        result.forEach(item-> {
            try {
                changePpt(item);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void changePpt(String filename) throws IOException {

        System.out.println(filename);
        File file = new File(filename);
        InputStream stream =new FileInputStream(file);

        XMLSlideShow ppt = new XMLSlideShow(stream);
        stream.close();

        //getting the current page size
        java.awt.Dimension pgsize = ppt.getPageSize();
        int pgw = pgsize.width; //slide width in points
        int pgh = pgsize.height; //slide height in points

        System.out.println("current page size of the PPT is:");
        System.out.println("width :" + pgw);
        System.out.println("height :" + pgh);

        ppt.setPageSize(new java.awt.Dimension(1920,1080));
        //pgsize.setSize(1920,1080);

        XSLFSlide[] slides = ppt.getSlides();
        XSLFSlide slide = slides[0];
        XSLFTextShape body = slide.getPlaceholder(0);
        String text = body.getText();
        System.out.println(text);


        //saving changes
        FileOutputStream out = new FileOutputStream(file);

        //saving the changes to a file
        ppt.write(out);
        System.out.println("slide size changed to given dimentions ");
        out.close();
    }

}
