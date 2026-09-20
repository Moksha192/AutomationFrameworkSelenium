package com.training.util;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public final class ScreenshotUtil {

    private ScreenshotUtil() {}

    public static Path captureElement(WebElement element, String fileName)
            throws IOException {

        Path directory = Path.of("screenshots");
        Files.createDirectories(directory);

        Path destination = directory.resolve(fileName + ".png");

        Files.copy(
                element.getScreenshotAs(OutputType.FILE).toPath(),
                destination,
                StandardCopyOption.REPLACE_EXISTING
        );

        return destination;
    }
}