package edu.school21.spring.renderer;

import edu.school21.spring.preprocessor.PreProcessor;

public class RendererStandardImpl implements Renderer {
    private final PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void renderMsg(String str) {
        System.out.println(preProcessor.process(str));
    }
}
