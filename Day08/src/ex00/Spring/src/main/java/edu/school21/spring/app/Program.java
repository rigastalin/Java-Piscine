package edu.school21.spring.app;

import edu.school21.spring.preprocessor.PreProcessor;
import edu.school21.spring.preprocessor.PreProcessorToUpperImpl;
import edu.school21.spring.printer.Printer;
import edu.school21.spring.printer.PrinterWithPrefixImpl;
import edu.school21.spring.renderer.Renderer;
import edu.school21.spring.renderer.RendererErrImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {
    public static void main(String[] args) {
        System.out.println("======== This is the NORMAL way ========");
        PreProcessor preProcessor = new PreProcessorToUpperImpl();
        Renderer renderer = new RendererErrImpl(preProcessor);
        PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
        printer.setPrefix("Prefix ");
        printer.print("Hello!");
        System.out.println();


        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        System.out.println("======== This is the BEAN way ========");
        Printer prefixPrinter = context.getBean("printerWithPrefix", Printer.class);
        printer.print("Hello!");
    }
}
