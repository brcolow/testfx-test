import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

public class ClickApplicationTest extends ApplicationTest {
    @BeforeEach
    void setup() throws Exception {
        ApplicationTest.launch(ClickApplication.class);
    }
    @Override
    public void init() throws Exception {
        FxToolkit.registerStage(Stage::new);
    }

    @Override
    public void start(Stage stage) {
        Button button = new Button("click me!");
        button.setOnAction(actionEvent -> button.setText("clicked!"));
        StackPane sceneRoot = new StackPane(button);
        stage.setScene(new Scene(sceneRoot, 1000, 1000));
        stage.show();
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    public void should_contain_button() throws InterruptedException
    {
        // expect:
        verifyThat(".button", hasText("click me!"));
    }

    @Test public void should_click_on_button() {
        // when:
        clickOn(".button");
        WaitForAsyncUtils.waitForFxEvents();

        // then:
        verifyThat(".button", hasText("clicked!"));
    }
}
