package demo_sg;

import io.cucumber.java.en.Given;

import java.io.File;

public class ReadFileStepDefs {

    @Given("reading file write")
    public void reading_file_write() {

         if (System.getProperty("os.name").contains("Windows")){
             String path="./src/test/resources/files/Demo.png";
             File file=new File(path);
             System.out.println("file.exists() = " + file.exists());
         }else {
             String pathOfProject=System.getProperty("user.dir");
             String pathOfFile="/src/test/resources/files/Demo.png";
             String path=pathOfProject+pathOfFile;
             File file=new File(path);
             System.out.println("file.exists() = " + file.exists());
         }
    }
}
