package edu.school21.annotations;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes({"edu.school21.annotations.HtmlForm", "edu.school21.annotations.HtmlInput"})
public class HtmlProcessor extends AbstractProcessor {
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        for (Element userForm : roundEnvironment.getElementsAnnotatedWith(HtmlForm.class)) {
            HtmlForm htmlFormAnnotation = userForm.getAnnotation(HtmlForm.class);
            String str = "<form action = \"" + htmlFormAnnotation.formAction() + "\" method = \"" + htmlFormAnnotation.method() + "\">\n";
            List<? extends Element> userFormElements = userForm.getEnclosedElements();
            for (Element field : roundEnvironment.getElementsAnnotatedWith(HtmlInput.class)) {
                if (!userFormElements.contains(field)) {
                    continue;
                }
                HtmlInput htmlInputAnnotation = field.getAnnotation(HtmlInput.class);
                str += "\t<input type = " + htmlInputAnnotation.type() + "\" name = \"" +
                        htmlInputAnnotation.name() + "\" placeholder = \"" + htmlInputAnnotation.placeholder() + "\">\n";
            }
            str += "\t<input type = \"submit\" value = \"Send\">\n</form>";

            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter("target/classes/" + htmlFormAnnotation.fileName());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                bufferedWriter.write(str);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
}

