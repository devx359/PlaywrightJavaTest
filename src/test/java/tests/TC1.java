package tests;

import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import base.BaseFunction;

public class TC1 extends BaseFunction {


    @Test
    public void testPlaywrightSetup() {
            page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            System.out.println(page.title());        
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).fill("Admin");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).fill("admin123");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Leave")).click();
            // context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("storageState.json")));

    }
        
}