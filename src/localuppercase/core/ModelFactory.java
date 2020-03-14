package localuppercase.core;


import localuppercase.model.TextConverter;
import localuppercase.model.TextConverterManager;

public class ModelFactory {

    private TextConverter textConverter;

    public TextConverter getTextConverter() {
        if(textConverter == null)
            textConverter = new TextConverterManager();
        return textConverter;
    }
}


