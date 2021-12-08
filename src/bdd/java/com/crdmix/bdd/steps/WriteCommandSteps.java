package com.crdmix.bdd.steps;

import com.crdmix.bdd.configuration.FakeConsole;
import com.crdmix.console.render.ProgrammableClock;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class WriteCommandSteps {

    @Autowired
    private FakeConsole captureConsole;
    @Autowired
    private ProgrammableClock programmableClock;
    private ArrayList<String> lastConsoleOutput;

    @When("$1 posts message \"$2\"")
    @Given("$1 has posted message \"$2\"")
    public void whenUserPostsMessage(String userName, String message) {
        String commandLine = userName + " -> " + message;
        captureConsole.incomingLineToStdIn(commandLine);
    }

    @When("$1 reads {his|her} timeline")
    public void readTimeline(String username) {
        captureConsole.incomingLineToStdIn(username);
        this.lastConsoleOutput = new ArrayList<>(captureConsole.getScreen());
    }

    @When("$1 reads the timeline of $")
    public void readSomeOneElsesTimeLine(String user, String userTimeLineToRead) {
        readTimeline(userTimeLineToRead);
    }

    @Then("the last output contains \"$1\"")
    public void checkOutput(String expectedLine) {
        assertThat(this.lastConsoleOutput).contains(expectedLine);
    }

    @Given("$1 $2 have passed")
    public void givenTimeHavePassed(int time, String units) {
        long desiredRelativeOffSet = TimeUnit.MILLISECONDS.convert(time, TimeUnit.valueOf(units.toUpperCase()));
        programmableClock.moreMillisecondsPast(desiredRelativeOffSet+100);
    }

    @Given("$1 follows $2")
    public void givenUserFollowsAnother(String user, String followsUser) {
        String commandLine = user + " follows " + followsUser;
        captureConsole.incomingLineToStdIn(commandLine);
    }

    @When("wall of $1 is read")
    public void whenWallIsRead(String user) {
        String commandLine = user + " wall";
        captureConsole.incomingLineToStdIn(commandLine);
        this.lastConsoleOutput = new ArrayList<>(captureConsole.getScreen());
    }
}
