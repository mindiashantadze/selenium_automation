package mshantadze.utils.logging;

import mshantadze.utils.ui.UIElementWrapper;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(UIElementWrapper.class);

    public static void logHTMLElementAttributes(WebElement element) {
        LOGGER.info("\n");

        String domTagName = "Element: " + element.getTagName() + "\n";
        String domId = "Id: " + element.getAttribute("id") + "\n";
        String domClassName = "ClassName: " + element.getDomAttribute("class") + "\n";
        String domNodeText = "Text: " + element.getText() + "\n";

        LOGGER.info(domTagName + domId  + domClassName + domNodeText);
    }
}
