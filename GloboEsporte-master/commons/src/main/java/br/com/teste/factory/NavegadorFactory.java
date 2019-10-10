package br.com.teste.factory;


import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;

public class NavegadorFactory {

    //TODO -> NOMECLATURA DOS PACOTES.
    //TODO -> CRIAR CONSTRUTOR PRIVADO.
    //TODO -> SE A VARIAVEL É SÓ USADA NESSA CLASSE, COLOCA-LA COMO PRIVATE.

    private static WebClient browser;


    private NavegadorFactory() {
    }

    public static WebClient getWebClient(){

        if(browser==null){
            setBrowser();
        }
        return browser;
    }


    public static WebClient setBrowser(){
        browser = new WebClient(BrowserVersion.BEST_SUPPORTED);
        browser.getOptions().setRedirectEnabled(true);
        browser.getOptions().setThrowExceptionOnFailingStatusCode(false);
        browser.getOptions().setThrowExceptionOnScriptError( false );
        browser.getOptions().setPrintContentOnFailingStatusCode(false);
        browser.getOptions().setCssEnabled(false);
        browser.getOptions().setUseInsecureSSL(true);
        browser.getOptions().setJavaScriptEnabled(false);

        return browser;
    }
}
