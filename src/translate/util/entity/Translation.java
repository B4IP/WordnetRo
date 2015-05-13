
package translate.util.entity;

public class Translation {
    
    private String originalPhrase;
    private String translatedePhrase;

    public String getOriginalPhrase() {
        return originalPhrase;
    }

    public void setOriginalPhrase(String originalPhrase) {
        this.originalPhrase = originalPhrase;
    }

    public String getTranslatedePhrase() {
        return translatedePhrase;
    }

    public void setTranslatedePhrase(String translatedePhrase) {
        this.translatedePhrase = translatedePhrase;
    }
    
    @Override
    public String toString() {
        return "[" + this.originalPhrase + ", " + this.translatedePhrase + "]";
    }
    
}
