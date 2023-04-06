package mshantadze.base;

import mshantadze.services.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public abstract class BaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    protected ConfigService configInstance;

    @BeforeSuite
    public void loadProps() {
        configInstance = ConfigService.init();
    }
}
