package com.company.tests.dragndrop;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.company.base.BaseTest;
import com.company.pages.DragNDropPage;

/** ТК14. Проверка работы drag and drop */
@Epic("Way2Automation")
@Feature("Drag and Drop")
@Story("Проверка работы drag and drop")
@Severity(SeverityLevel.CRITICAL)
public class DragNDropTest extends BaseTest {

    @Test(
            description = "ТК14. Проверка работы drag and drop",
            groups = {"way2automation", "dragndrop"})
    public void testDragAndDrop() {
        DragNDropPage dragNDropPage = createDragNDropPage();

        Allure.step(
                "Выполнение Drag and Drop внутри IFrame",
                () -> {
                    dragNDropPage.switchToIframe().dragAndDrop();
                });

        Allure.step(
                "Проверка текста принимающего элемента",
                () -> {
                    Assert.assertEquals(
                            dragNDropPage.getDroppableText(),
                            "Dropped!",
                            "Текст принимающего элемента не соответствует ожидаемому");
                });

        dragNDropPage.switchFromIframe();
    }
}
